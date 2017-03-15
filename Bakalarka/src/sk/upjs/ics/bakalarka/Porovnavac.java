package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.List;

public class Porovnavac {
	public boolean compare(String vyraz1, String vyraz2) {
		RegularnyVyraz rv1 = new RegularnyVyraz(vyraz1);
		Automat n = rv1.toAutomat();
		Automat d = n.determinizuj();
		Automat automat1 = d.minimalizuj();

		RegularnyVyraz rv2 = new RegularnyVyraz(vyraz2);
		n = rv2.toAutomat();
		d = n.determinizuj();
		Automat automat2 = d.minimalizuj();

		if (automat1.getStavy().size() != automat2.getStavy().size()) {
			return false;
		}

		automat1.vymazId();
		automat2.vymazId();

		// zmena id-ciek prveho automatu
		int newId = 0;
		automat1.getPociatocnyStav().setId(newId);
		int i = 0;

		while (true) {
			int temp = newId;
			for (List<Stav> prechody : automat1.getStavPodlaId(i).getPrechody()) {
				for (Stav stav : prechody) {
					if (stav.getId() == -1) {
						stav.setId(++newId);
					}
				}
			}
			if (temp == newId) {
				break;
			}
			i++;
		}
		
		// zmena id-ciek prveho automatu
		newId = 0;
		automat2.getPociatocnyStav().setId(newId);
		i = 0;

		while (true) {
			int temp = newId;
			for (List<Stav> prechody : automat2.getStavPodlaId(i).getPrechody()) {
				for (Stav stav : prechody) {
					if (stav.getId() == -1) {
						stav.setId(++newId);
					}
				}
			}
			if (temp == newId) {
				break;
			}
			i++;
		}
		
		// porovnanie
		int size = automat1.getStavy().size();
		List<Integer> list1 = new ArrayList<>();
		for (int j = 0; j < size; j++) {
			for (List<Stav> prechody : automat1.getStavPodlaId(j).getPrechody()) {
				for (Stav stav : prechody) {
					list1.add(stav.getId());
				}
			}
		}
		List<Integer> list2 = new ArrayList<>();
		for (int j = 0; j < size; j++) {
			for (List<Stav> prechody : automat2.getStavPodlaId(j).getPrechody()) {
				for (Stav stav : prechody) {
					list2.add(stav.getId());
				}
			}
		}
		
		return list1.equals(list2);
	}
}
