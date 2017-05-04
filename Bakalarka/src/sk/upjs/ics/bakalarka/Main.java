package sk.upjs.ics.bakalarka;

import java.util.List;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class Main {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		RegularnyVyraz rv = new RegularnyVyraz("(aa+b)(a+b)*+ab(ab*a)*");
		PrevodRegVyrazNaNFA p = new PrevodRegVyrazNaNFA();
		PrevodNFANaDFA pp = new PrevodNFANaDFA();
		Minimalizacia min = new Minimalizacia();
		
		Automat n = p.toAutomat(rv.getVyraz());
		Automat d = pp.toDFAAutomat(n);
		Automat m = min.minimize(d);
		
		System.out.println(m);
		System.out.println((System.currentTimeMillis() - time) + " ms");
				
		/*long time = System.currentTimeMillis();
		RegExp regExp = new RegExp("(aa|b)(a|b)*|ab(ab*a)*");
		Automaton a = regExp.toAutomaton();
		System.out.println(a);
		System.out.println(System.currentTimeMillis()-time);*/
		
		/*long time = System.currentTimeMillis();
		Porovnavac p = new Porovnavac();
		System.out.println(p.compare("a(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*",
									 "a(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*(a+b)*"));
		System.out.println("Time: " + (System.currentTimeMillis()-time));*/
		
	}
}
