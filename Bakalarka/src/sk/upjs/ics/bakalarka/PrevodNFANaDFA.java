package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.Stack;

public class PrevodNFANaDFA {

	private Automat automat;
	private Stack<Stav> zasobnik = new Stack<Stav>();
	public static final int MAX_ZNAKOV = 26;
	public static final int POSUN = 97;

	public Automat toDFAAutomat(Automat automat) {
		this.automat = automat;

		vyrobBitKody();

		Automat novyAutomat = new Automat();
		novyAutomat.pridajStav(automat.getPociatocnyStav());
		novyAutomat.setPociatocnyStav(automat.getPociatocnyStav());
		zasobnik.push(novyAutomat.getPociatocnyStav());

		while (!zasobnik.isEmpty()) {
			Stav aktualnyStav = zasobnik.pop();
			ArrayList<Stav>[] vsetkyPrechody = aktualnyStav.getPrechody();
			for (int i = 0; i < MAX_ZNAKOV; i++) {
				Stav novyStav = new Stav();

				ArrayList<Stav> prechodyNaZnak = vsetkyPrechody[i];
				for (Stav s : prechodyNaZnak) {
					novyStav.setBitKod(novyStav.getBitKod() + s.getBitKod());
				}

				ArrayList<Stav> epsilonPrechody = aktualnyStav.getEpsilonPrechody();
				for (Stav s : epsilonPrechody) {
					novyStav.setBitKod(novyStav.getBitKod() + s.getBitKod());
				}

				aktualnyStav.pridajPrechod((char) (i + POSUN), novyStav);

				boolean uzExistuje = false;
				for (Stav s : novyAutomat.getStavy()) {
					if (s.getBitKod() == novyStav.getBitKod()) {
						uzExistuje = true;
						break;
					}
				}
				if (!uzExistuje) {
					novyAutomat.pridajStav(novyStav);
					zasobnik.push(novyStav);
				}

			}
		}

		novyAutomat.vyrobId();
		return novyAutomat;
	}

	public void vyrobBitKody() {
		long bitKod = 1;
		for (Stav s : automat.getStavy()) {
			s.setBitKod(bitKod);
			bitKod = bitKod * 10;
		}
	}

	public void odstranEpsilonPrechody() {
		Automat novyAutomat = new Automat();
		Stav pociatocnyStav = automat.getPociatocnyStav();
		novyAutomat.pridajStav(pociatocnyStav);
		novyAutomat.setPociatocnyStav(pociatocnyStav);
		
		Stack<Stav> zasobnik = new Stack<Stav>();
		zasobnik.push(pociatocnyStav);
		
		ArrayList<Stav> epsilonPrechody = new ArrayList<Stav>();
		while (!zasobnik.isEmpty()) {
			Stav aktualnyStav = zasobnik.pop();
						
			for(int j = 0; j < MAX_ZNAKOV; j++) {
				epsilonPrechody.add(aktualnyStav);
				for (int i = 0; i < epsilonPrechody.size(); i++) {
					Stav stav = epsilonPrechody.get(i);
					for (Stav s : stav.getEpsilonPrechody()) {
						if (!epsilonPrechody.contains(s)) {
							epsilonPrechody.add(s);
						}
					}
				}
			
				for (Stav s : epsilonPrechody) {
					if (automat.getKoncoveStavy().contains(s)) {
						novyAutomat.pridajKoncovyStav(aktualnyStav);
					}
					ArrayList<Stav> list = new ArrayList<Stav>();
					for(Stav ss : s.getPrechody()[j]) {
						list.add(ss);
						zasobnik.push(ss);
						novyAutomat.pridajStav(ss);
					}
					for (Stav sss: list)
						aktualnyStav.pridajPrechod((char)(j + POSUN), sss);
				}
				epsilonPrechody.clear();
			}
		}
		this.automat = novyAutomat;
	}

	public Automat getAutomat() {
		return automat;
	}

	public void setAutomat(Automat automat) {
		this.automat = automat;
	}
}
