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
		
	//------------------------------------------------------------------------
	public String getVyraz() {
		return vyraz;
	}
}
