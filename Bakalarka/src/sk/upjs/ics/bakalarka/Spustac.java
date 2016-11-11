package sk.upjs.ics.bakalarka;

import java.math.BigInteger;

public class Spustac {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		RegularnyVyraz rv = new RegularnyVyraz("");
		PrevodRegVyrazNaNFA p = new PrevodRegVyrazNaNFA();
		Automat a = p.toAutomat(rv.getVyraz());
		Automat automat = a.determinizuj();
		
		System.out.println(a.toString());
	}
}
