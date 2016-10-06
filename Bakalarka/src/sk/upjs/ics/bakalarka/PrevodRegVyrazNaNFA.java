package sk.upjs.ics.bakalarka;

import java.util.Stack;

public class PrevodRegVyrazNaNFA {
	private String vyraz;
	private Automat automat;
	private Stav pociatocnyStav;
	private Stav aktualnyStav;
	private Stav koncovyStav;
	private char znak;
	private Stack<Stav> zaciatkyZatvoriek = new Stack<Stav>();
	private Stack<Stav> konceZatvoriek = new Stack<Stav>();
	private boolean vZatvorke;

	public Automat toAutomat(String vyraz) {
		this.vyraz = vyraz;
		this.automat = new Automat();

		init();

		for (int i = 0; i < vyraz.length() - 1; i++) {
			znak = vyraz.charAt(i);
			char dalsiZnak = vyraz.charAt(i + 1);
			if (znak == '*') {
				continue;
			}
			if (znak == '+') {
				vetvenie();
				continue;
			}
			if (znak == '(') {
				zaciatokZatvorky();
				continue;
			}
			if (znak == ')') {
				if (dalsiZnak != '*') {
					koniecZatvorky();
				} else {
					koniecZatvorkySHviezdickou();
				}
				continue;
			}

			konstrukcia(dalsiZnak);
		}
		
		znak = vyraz.charAt(vyraz.length()-1);
		if (znak != '*' && znak != ')') {
			konkatenacia(znak);			
		} else if (znak == ')') {
			koniecZatvorky();
		}
		aktualnyStav.pridajEpsilonPrechod(koncovyStav);

		automat.vyrobId();
		return automat;
	}

	public void konstrukcia(char dalsiZnak) {
		if (dalsiZnak == '*')
			hviezdicka(znak);
		else
			konkatenacia(znak);
	}

	public void konkatenacia(char znak) {
		Stav s1 = new Stav();
		automat.pridajStav(s1);
		aktualnyStav.pridajPrechod(znak, s1);
		aktualnyStav = s1;
	}

	public void hviezdicka(char znak) {
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		Stav s3 = new Stav();
		automat.pridajStav(s1);
		automat.pridajStav(s2);
		automat.pridajStav(s3);
		aktualnyStav.pridajEpsilonPrechod(s1);
		aktualnyStav.pridajEpsilonPrechod(s3);
		s1.pridajPrechod(znak, s2);
		s2.pridajEpsilonPrechod(s1);
		s2.pridajEpsilonPrechod(s3);
		aktualnyStav = s3;
	}

	public void vetvenie() {
		if (!vZatvorke) {
			aktualnyStav.pridajEpsilonPrechod(koncovyStav);
			Stav s1 = new Stav();
			automat.pridajStav(s1);
			pociatocnyStav.pridajEpsilonPrechod(s1);
			aktualnyStav = s1;
		} else { //if (vZatvorke)
			aktualnyStav.pridajEpsilonPrechod(konceZatvoriek.peek());
			Stav s1 = new Stav();
			automat.pridajStav(s1);
			zaciatkyZatvoriek.peek().pridajEpsilonPrechod(s1);
			aktualnyStav = s1;
		}
	}

	public void zaciatokZatvorky() {
		vZatvorke = true;
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		automat.pridajStav(s1);
		automat.pridajStav(s2);
		aktualnyStav.pridajEpsilonPrechod(s1);
		zaciatkyZatvoriek.push(aktualnyStav);
		konceZatvoriek.push(s2);
		aktualnyStav = s1;
	}
	
	public void koniecZatvorky() {
		aktualnyStav.pridajEpsilonPrechod(konceZatvoriek.peek());
		aktualnyStav = konceZatvoriek.pop();
		
		zaciatkyZatvoriek.pop();
		if (zaciatkyZatvoriek.isEmpty()) {
			vZatvorke = false;
		}
	}
	
	public void koniecZatvorkySHviezdickou() {
		aktualnyStav.pridajEpsilonPrechod(konceZatvoriek.peek());
		Stav s1 = new Stav();
		konceZatvoriek.peek().pridajEpsilonPrechod(s1);
		automat.pridajStav(s1);
		aktualnyStav = s1;
		
		zaciatkyZatvoriek.peek().pridajEpsilonPrechod(konceZatvoriek.peek());
		konceZatvoriek.pop().pridajEpsilonPrechod(zaciatkyZatvoriek.pop());
		if (zaciatkyZatvoriek.isEmpty()) {
			vZatvorke = false;
		}
	}

	public void init() {
		pociatocnyStav = new Stav();
		aktualnyStav = new Stav();
		koncovyStav = new Stav();
		pociatocnyStav.pridajEpsilonPrechod(aktualnyStav);
		automat.pridajStav(pociatocnyStav);
		automat.pridajStav(koncovyStav);
		automat.pridajStav(aktualnyStav);
		automat.setPociatocnyStav(pociatocnyStav);
		automat.pridajKoncovyStav(koncovyStav);
	}
}
