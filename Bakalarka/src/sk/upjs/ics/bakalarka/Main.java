package sk.upjs.ics.bakalarka;

import java.util.List;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class Main {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		/*long time = System.currentTimeMillis();
		RegularnyVyraz rv = new RegularnyVyraz("(aa+b)(a+b)*+ab(ab*a)*");
		PrevodRegVyrazNaNFA p = new PrevodRegVyrazNaNFA();
		PrevodNFANaDFA pp = new PrevodNFANaDFA();
		Minimalizacia min = new Minimalizacia();
		
		Automat n = p.toAutomat(rv.getVyraz());
		Automat d = pp.toDFAAutomat(n);
		Automat m = min.minimize(d);
		
		System.out.println(m);
		System.out.println((System.currentTimeMillis() - time) + " ms");*/
				
		/*long time = System.currentTimeMillis();
		RegExp regExp = new RegExp("(aa+b)(a+b)*+ab(ab*a)*");
		Automaton a = regExp.toAutomaton();
		a.minimize();
		System.out.println(a);*/
		
		/*long time = System.currentTimeMillis();
		Porovnavac p = new Porovnavac();
		System.out.println(p.compare("a(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*",
									 "a(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*"));
		System.out.println("Time: " + (System.currentTimeMillis()-time));*/
		
		Automat a = new Automat();
		Stav s0 = new Stav();
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		Stav s3 = new Stav();
		Stav s4 = new Stav();
		Stav s5 = new Stav();
		Stav s6 = new Stav();
		Stav s7 = new Stav();
		Stav s8 = new Stav();
		Stav s9 = new Stav();
		Stav s10 = new Stav();
		Stav s11 = new Stav();
		Stav s12 = new Stav();
		Stav s13 = new Stav();
		Stav s14 = new Stav();
		Stav s15 = new Stav();
		a.pridajStav(s0);
		a.pridajStav(s1);
		a.pridajStav(s2);
		a.pridajStav(s3);
		a.pridajStav(s4);
		a.pridajStav(s5);
		a.pridajStav(s6);
		a.pridajStav(s7);
		a.pridajStav(s8);
		a.pridajStav(s9);
		a.pridajStav(s10);
		a.pridajStav(s11);
		a.pridajStav(s12);
		a.pridajStav(s13);
		a.pridajStav(s14);
		a.pridajStav(s15);
		s0.pridajPrechod('a', s9);
		s0.pridajPrechod('b', s15);
		s1.pridajPrechod('a', s3);
		s1.pridajPrechod('b', s10);
		s2.pridajPrechod('a', s2);
		s2.pridajPrechod('b', s11);
		s3.pridajPrechod('a', s2);
		s3.pridajPrechod('b', s1);
		s4.pridajPrechod('a', s13);
		s4.pridajPrechod('b', s15);
		s5.pridajPrechod('a', s10);
		s6.pridajPrechod('a', s14);
		s6.pridajPrechod('b', s1);
		s7.pridajPrechod('a', s12);
		s8.pridajPrechod('a', s4);
		s9.pridajPrechod('a', s14);
		s9.pridajPrechod('b', s11);
		s10.pridajPrechod('a', s5);
		s10.pridajPrechod('b', s10);
		s11.pridajPrechod('a', s0);
		s11.pridajPrechod('b', s10);
		s12.pridajPrechod('a', s13);
		s13.pridajPrechod('a', s13);
		s13.pridajPrechod('b', s8);
		s14.pridajPrechod('a', s9);
		s14.pridajPrechod('b', s8);
		s15.pridajPrechod('a', s6);
		a.setPociatocnyStav(s7);
		a.pridajKoncovyStav(s15);
		a.pridajKoncovyStav(s14);
		a.pridajKoncovyStav(s0);
		a.pridajKoncovyStav(s15);
		a.pridajKoncovyStav(s1);
		a.pridajKoncovyStav(s5);
		a.pridajKoncovyStav(s3);
		a.pridajKoncovyStav(s2);
		Automat d = a.determinizuj();
		Automat m = d.minimalizuj();
		System.out.println(m);
	}
}
