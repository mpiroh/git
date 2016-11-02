package sk.upjs.ics.bakalarka;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PrevodNFANaDFA {
	public static final int MAX_ZNAKOV = 26;
	public static final int POSUN = 97;
	private Queue<Stav> rad = new LinkedList<>();
	private Queue<Stav> epsilonRad = new LinkedList<>();
	private List<Character> abeceda;
	private Map<Stav, List<Stav>> epsilonPrechody;
	private List<Stav> anticyklickyZoznam = new ArrayList<>();

	public Automat toDFAAutomat(Automat oldAutomat) {
		this.abeceda = zistiAbecedu(oldAutomat);
		this.epsilonPrechody = nacitajEpsilonPrechody(oldAutomat);
		
		Automat newAutomat = new Automat();
		
		Stav pociatocnyStav = new Stav();
		long pociatocnyBitKod = oldAutomat.getPociatocnyStav().getBitKod();
		
		epsilonRad.add(oldAutomat.getPociatocnyStav());
		while(!epsilonRad.isEmpty()) {
			Stav e = epsilonRad.poll();
			if (!e.getEpsilonPrechody().isEmpty()) {
				for (Stav epsilonStav : e.getEpsilonPrechody()) {
					if (!anticyklickyZoznam.contains(epsilonStav)) {
						pociatocnyBitKod = skontrolujBitKod(pociatocnyBitKod + epsilonStav.getBitKod());
						epsilonRad.add(epsilonStav);
						anticyklickyZoznam.add(epsilonStav);
					}
				}
			}
		}
		anticyklickyZoznam.clear();
		
		pociatocnyStav.setBitKod(pociatocnyBitKod);
		newAutomat.pridajStav(pociatocnyStav);
		newAutomat.setPociatocnyStav(pociatocnyStav);
		
		rad.add(pociatocnyStav);
		
		while(!rad.isEmpty()) {
			Stav stav = rad.poll();
			long bitKod = stav.getBitKod();
			
			for (char znak : abeceda) {
				int poradie = 1;
				long cielovyBitKod = 0;
				
				//zistim cielovy bitkod zatial bez eplsionovych prechodov
				while (bitKod / poradie > 0) {
					long bit = (bitKod % (poradie*10)) / poradie;
					if (bit == 1) {
						for (Stav sss : oldAutomat.getStavPodlaBitKodu(poradie).getPrechody()[((int)znak)-POSUN]) {
							cielovyBitKod = skontrolujBitKod(cielovyBitKod + sss.getBitKod());
							
							if (epsilonPrechody.get(sss) != null) {
								epsilonRad.add(sss);
								while(!epsilonRad.isEmpty()) {
									Stav e = epsilonRad.poll();
									if (epsilonPrechody.get(e) != null) {
										for (Stav epsilonStav : epsilonPrechody.get(e)) {
											if (!anticyklickyZoznam.contains(epsilonStav)) {
												cielovyBitKod = skontrolujBitKod(cielovyBitKod + epsilonStav.getBitKod());
												epsilonRad.add(epsilonStav);
												anticyklickyZoznam.add(epsilonStav);
											}
										}
									}
								}
							}
						}
					} else { //if (bit == 0)
						//
					}
					poradie = poradie * 10;
				}
				
				//pridam bitkody podla epsilonov
				
				
				
				boolean stavUzExistuje = false;
				
				//zistim ci taky stav uz existuje (podla provnania bitkodov)
				//ak existuje, tak pridam prechod
				for (Stav ks : newAutomat.getStavy()) {
					if (ks.getBitKod() == cielovyBitKod) {
						stavUzExistuje = true;
						//stav.pridajPrechod((char)(i + POSUN), ks);
						newAutomat.pridajPrechod(stav, znak, ks);
						break;
					}
				}
				
				//ak neexistuje, vyrobim novy stav, pridam prechod a pridam novy stav do automatu, 
				if (!stavUzExistuje) {
					Stav novyStav = new Stav();
					novyStav.setBitKod(cielovyBitKod);
					stav.pridajPrechod(znak, novyStav);
					newAutomat.pridajStav(novyStav);
					rad.add(novyStav);
				}
				anticyklickyZoznam.clear();
			}
		}
		
		return newAutomat;
	}
	
	public long skontrolujBitKod(Long bitKod) {
		long novyBitKod = bitKod;
		int i = 0;
		while(bitKod > 0) {
			if ((bitKod % 10 != 0) && (bitKod % 10 != 1)) {
				long hh = (bitKod % 10)*((long)(Math.pow(10, i)));
				novyBitKod = novyBitKod - (bitKod % 10)*((long)(Math.pow(10, i))) + (long)(Math.pow(10, i));
			}
			bitKod = bitKod / 10;
			i++;
		}
		return novyBitKod;
	}
	
	private List<Character> zistiAbecedu(Automat automat) {
		Set<Character> mnozinaAbeceda = new HashSet<>();
		List<Character> abeceda = new ArrayList<>();
		List<Stav> stavy = automat.getStavy();
		
		for (Stav s : stavy) {
			for (int i = 0; i < s.getPrechody().length; i++) {
				if (!s.getPrechody()[i].isEmpty()) {
					mnozinaAbeceda.add((char) (i + POSUN));
				}
			}
		}
		abeceda.addAll(mnozinaAbeceda);
		
		return abeceda;
	}
	
	private Map<Stav, List<Stav>> nacitajEpsilonPrechody(Automat automat) {
		Map<Stav, List<Stav>> epsilony = new HashMap<>();
		List<Stav> stavy = automat.getStavy();
		
		for (Stav s : stavy) {
			if (!s.getEpsilonPrechody().isEmpty()) {
				epsilony.put(s, s.getEpsilonPrechody());
			}
		}
		
		return epsilony;
	}
}
