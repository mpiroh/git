package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Minimalizacia {
	public static final int MAX_ZNAKOV = 26;
	public static final int POSUN = 97;

	private Map<List<Integer>, List<Stav>> mapa = new HashMap<>();

	public Automat minimize(Automat oldAutomat) {
		Automat newAutomat = new Automat();
		Automat pomocnyAutomat = oldAutomat;
		pomocnyAutomat.vyrobSkupiny();
		
		int pocetSkupin;
		if (pomocnyAutomat.getStavy().size() == pomocnyAutomat.getKoncoveStavy().size()) {
			pocetSkupin = 1;
		} else {
			pocetSkupin = 2;
		}

		for (int i = 1; i <= pocetSkupin; i++) {
			boolean rozdeliloSa = true;
			while (rozdeliloSa) {
				for (Stav stav : pomocnyAutomat.getStavyPodlaSkupiny(i)) {
					List<Integer> skupiny = new ArrayList<>();
					for (List<Stav> prechody : stav.getPrechody()) {
						if (!prechody.isEmpty()) {
							skupiny.add(prechody.get(0).getSkupina());
						}
					}

					if (mapa.get(skupiny) == null) {
						List<Stav> list = new ArrayList<>();
						list.add(stav);
						mapa.put(skupiny, list);
					} else {
						List<Stav> list = mapa.get(skupiny);
						list.add(stav);
						mapa.put(skupiny, list);
					}

					if (mapa.size() == 1) {
						rozdeliloSa = false;
					} else {
						rozdeliloSa = true;
					}

				}

				// ak mapa ma len jeden riadok, znamena ze sa nic nerozdelilo
				// ak mapa ma viac ako jeden riadok, znamena ze treba pridat
				// nove skupiny
				// nech velkost mapy je n, potom novych skupin bude n-1
				int pocetNovychSkupin = mapa.size() - 1;
				for (int j = 0; j < pocetNovychSkupin; j++) {
					// prvym n-1 riadokm v mape nastavim nove skupiny
					List<Stav> stavy = (List<Stav>) mapa.values().toArray()[j];
					for (Stav s : stavy) {
						s.setSkupina(pocetSkupin + 1);
					}
					pocetSkupin++;
				}

				mapa.clear();
			}
		}

		// z pomocneho automatu vytvorime novy automat podla skupin
		// vytvorim tolko stavov kolko mam skupin
		for (int i = 1; i <= pocetSkupin; i++) {
			Stav s = new Stav();
			s.setSkupina(i);
			newAutomat.pridajStav(s);
		}

		// nastavime prechody podla skupin zo stareho automatu
		for (int i = 1; i <= pocetSkupin; i++) {
			Stav oldStav = oldAutomat.getNejakyStavPodlaSkupiny(i);
			Stav newStav = newAutomat.getNejakyStavPodlaSkupiny(i);
			for (int j = 0; j < MAX_ZNAKOV; j++) {
				if (!oldStav.getPrechody()[j].isEmpty()) {
					int cielovaSkupina = oldStav.getPrechody()[j].get(0).getSkupina();
					Stav cielovyStav = newAutomat.getNejakyStavPodlaSkupiny(cielovaSkupina);
					newStav.pridajPrechod((char) (j + POSUN), cielovyStav);
				}
			}
		}

		// nastavime pociatocny stav
		int skupinaPociatocnehoStavu = oldAutomat.getPociatocnyStav().getSkupina();
		newAutomat.setPociatocnyStav(newAutomat.getNejakyStavPodlaSkupiny(skupinaPociatocnehoStavu));

		// nastavime koncove stavy
		List<Stav> koncoveStavy = oldAutomat.getKoncoveStavy();
		for (Stav stav : koncoveStavy) {
			int skupinaKoncovychStavov = stav.getSkupina();
			Stav koncovyStav = newAutomat.getNejakyStavPodlaSkupiny(skupinaKoncovychStavov);
			if (!newAutomat.getKoncoveStavy().contains(koncovyStav)) {
				newAutomat.pridajKoncovyStav(koncovyStav);
			}
		}

		newAutomat.vyrobId();
		return newAutomat;
	}
}
