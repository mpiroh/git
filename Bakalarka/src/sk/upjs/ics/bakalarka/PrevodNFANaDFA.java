package sk.upjs.ics.bakalarka;

import java.util.LinkedList;
import java.util.Queue;

public class PrevodNFANaDFA {
	public static final int MAX_ZNAKOV = 26;
	public static final int POSUN = 97;
	private Queue<Stav> rad = new LinkedList<Stav>();

	public Automat toDFAAutomat(Automat oldAutomat) {
		Automat newAutomat = new Automat();
		Stav pociatocnyStav = oldAutomat.getPociatocnyStav();
		newAutomat.pridajStav(pociatocnyStav);
		newAutomat.setPociatocnyStav(pociatocnyStav);
		
		rad.add(pociatocnyStav);
		
		while(!rad.isEmpty()) {
			Stav stav = rad.poll();
			long bitKod = stav.getBitKod();
			
			for (int i = 0; i < MAX_ZNAKOV; i++) {
				//zistim do akeho stavu mam pridat prechod
				int poradie = 1;
				long cielovyBitKod = 0;
				
				while (bitKod / poradie > 0) {
					long bit = (bitKod % (poradie*10)) / poradie;
					if (bit == 1) {
						for (Stav sss : oldAutomat.getStavPodlaBitKodu(poradie).getPrechody()[i]) {
							cielovyBitKod = skontrolujBitKod(cielovyBitKod + sss.getBitKod());
							
						}
					} else { //if (bit == 0)
						//
					}
					poradie = poradie * 10;
				}
				
				boolean stavUzExistuje = false;
				
				//zistim ci taky stav uz existuje (podla provnania bitkodov)
				//ak existuje, tak pridam prechod
				for (Stav ks : newAutomat.getStavy()) {
					if (ks.getBitKod() == cielovyBitKod) {
						stavUzExistuje = true;
						//stav.pridajPrechod((char)(i + POSUN), ks);
						newAutomat.pridajPrechod(stav, (char)(i + POSUN), ks);
						break;
					}
				}
				
				//ak neexistuje, vyrobim novy stav, pridam prechod a pridam novy stav do automatu, 
				if (!stavUzExistuje) {
					Stav novyStav = new Stav();
					novyStav.setBitKod(cielovyBitKod);
					stav.pridajPrechod((char)(i + POSUN), novyStav);
					newAutomat.pridajStav(novyStav);
					rad.add(novyStav);
				}
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
}
