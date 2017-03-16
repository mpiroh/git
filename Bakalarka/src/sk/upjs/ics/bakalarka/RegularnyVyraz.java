package sk.upjs.ics.bakalarka;

import java.util.HashSet;
import java.util.Set;

public class RegularnyVyraz {
	private String vyraz;

	public RegularnyVyraz(String vyraz) {
		this.vyraz = vyraz;
		odstranBodky();
	}
	
	public Automat toAutomat() {
		PrevodRegVyrazNaNFA prevodRegVyrazNaNFA = new PrevodRegVyrazNaNFA();
		Automat automat = prevodRegVyrazNaNFA.toAutomat(vyraz);
		Automat detAutomat = automat.determinizuj();
		return detAutomat;
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
	
	public Set<Character> getAlphabet() {
		Set<Character> alphabet = new HashSet<>();
		for (int i = 0; i < vyraz.length(); i++) {
			if (vyraz.charAt(i) != 'E') {
				alphabet.add(vyraz.charAt(i));
			}
		}
		return alphabet;
	}
		
	//------------------------------------------------------------------------
	public String getVyraz() {
		return vyraz;
	}
}
