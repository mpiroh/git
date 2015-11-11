package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.List;

public class RegularnyVyraz {
	private String vyraz;

	public RegularnyVyraz(String vyraz) {
		this.vyraz = vyraz;
		odstranBodky();
	}

	public Automat toAutomat() {
		Automat automat = new Automat();

		Stav pociatocnyStav = new Stav();
		Stav druhyStav = new Stav();
		Stav koncovyStav = new Stav();
		pociatocnyStav.pridajEpsilonPrechod(druhyStav);
		automat.pridajStav(pociatocnyStav);
		automat.pridajStav(druhyStav);
		automat.pridajStav(koncovyStav);
		automat.setPociatocnyStav(pociatocnyStav);
		automat.setKoncovyStav(koncovyStav);
		Stav poslednyStav = druhyStav;
		
		for (int i = 0; i < vyraz.length() - 1; i++) {
			char znak = vyraz.charAt(i);
			if (znak == '*') {
				continue;
			}
			
			if (znak == '+') {
				poslednyStav.pridajEpsilonPrechod(koncovyStav);
				Stav s1 = new Stav();
				automat.pridajStav(s1);
				pociatocnyStav.pridajEpsilonPrechod(s1);
				poslednyStav = s1;
				
				continue;
			}
			
			if (vyraz.charAt(i+1) != '*') {
				Stav s1 = new Stav();
				automat.pridajStav(s1);
				poslednyStav.pridajPrechod(znak, s1);
				poslednyStav = s1;
			} else { //if (vyraz.charAt(i) == '*')
				Stav s1 = new Stav();
				Stav s2 = new Stav();
				Stav s3 = new Stav();
				automat.pridajStav(s1);
				automat.pridajStav(s2);
				automat.pridajStav(s3);
				poslednyStav.pridajEpsilonPrechod(s1);
				poslednyStav.pridajEpsilonPrechod(s3);
				s1.pridajPrechod(znak, s2);
				s2.pridajEpsilonPrechod(s1);
				s2.pridajEpsilonPrechod(s3);
				poslednyStav = s3;
			}
		}
		
		char znak = vyraz.charAt(vyraz.length()-1);
		if (znak != '*') {
			Stav s1 = new Stav();
			automat.pridajStav(s1);
			poslednyStav.pridajPrechod(znak, s1);
			s1.pridajEpsilonPrechod(koncovyStav);
		} else { // if (znak == '*')
			poslednyStav.pridajEpsilonPrechod(koncovyStav);
		}

		automat.vyrobId();
		return automat;
	}

	public void odstranBodky() {
		StringBuilder novyVyraz = new StringBuilder();
		for (int i = 0; i < vyraz.length(); i++) {
			char znak = vyraz.charAt(i);
			if (znak != '.')
				novyVyraz.append(znak);
		}
		this.vyraz = novyVyraz.toString();
	}
		
	//------------------------------------------------------------------------
	public String getVyraz() {
		return vyraz;
	}
}
