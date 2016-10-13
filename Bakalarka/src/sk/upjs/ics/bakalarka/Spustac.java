package sk.upjs.ics.bakalarka;

import java.util.ArrayList;

public class Spustac {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		//RegularnyVyraz rv = new RegularnyVyraz("a*");
		//Automat automat = rv.toAutomat();
				
		//System.out.println(automat);
		
		//PrevodNFANaDFA prevod = new PrevodNFANaDFA();
		//prevod.setAutomat(automat);
		//prevod.odstranEpsilonPrechody();
		//System.out.println(prevod.getAutomat().toStringBezE());
		
		//System.out.println((char)(1 + POSUN));
		
		//PRIKLAD NA DETERMINIZACIU
		/*Stav s1 = new Stav();
		Stav s2 = new Stav();
		Stav s3 = new Stav();
		s1.pridajPrechod('b', s2);
		s1.pridajPrechod('b', s3);
		s2.pridajPrechod('a', s2);
		s2.pridajPrechod('a', s3);
		s2.pridajPrechod('b', s3);
		s3.pridajPrechod('a', s1);
		
		Automat automat = new Automat();
		automat.setPociatocnyStav(s1);
		automat.pridajKoncovyStav(s1);
		automat.pridajStav(s1);
		automat.pridajStav(s2);
		automat.pridajStav(s3);
		automat.vyrobBitKody();
		
		//System.out.println(automat.toStringBitKody());
		
		System.out.println((automat.determinizuj()).toStringBitKody());	*/	
		
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		Stav s3 = new Stav();
		Stav s4 = new Stav();
		s1.pridajPrechod('a', s3);
		s1.pridajPrechod('b', s3);
		s2.pridajPrechod('b', s1);
		s2.pridajPrechod('b', s4);
		s3.pridajPrechod('b', s4);
		s4.pridajPrechod('a', s2);
		
		Automat automat = new Automat();
		automat.pridajStav(s1);
		automat.pridajStav(s2);
		automat.pridajStav(s3);
		automat.pridajStav(s4);
		automat.setPociatocnyStav(s1);
		
		automat.vyrobBitKody();
		System.out.println(automat.determinizuj().toStringBitKody());
		
	}
}
