package sk.upjs.ics.bakalarka;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spustac {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		//DETERMINIZACIA
		/*RegularnyVyraz rv = new RegularnyVyraz("");
		PrevodRegVyrazNaNFA p = new PrevodRegVyrazNaNFA();
		Automat a = p.toAutomat(rv.getVyraz());
		Automat automat = a.determinizuj();
		
		System.out.println(automat.toString());*/
		
		//MINIMALIZACIA
		RegularnyVyraz rv = new RegularnyVyraz("(a+b)*+(c+d)*");
		Automat a = rv.toAutomat().minimalizuj();
		System.out.println(a);
	}
}
