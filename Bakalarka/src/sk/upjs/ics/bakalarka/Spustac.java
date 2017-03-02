package sk.upjs.ics.bakalarka;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class Spustac {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		RegularnyVyraz rv = new RegularnyVyraz("a(aa)*+a(aa)*b(aaa)*+(aa)*ba(aa)*");
		PrevodRegVyrazNaNFA p = new PrevodRegVyrazNaNFA();
		PrevodNFANaDFA pp = new PrevodNFANaDFA();
		Minimalizacia min = new Minimalizacia();
		
		Automat n = p.toAutomat(rv.getVyraz());
		Automat d = pp.toDFAAutomat(n);
		Automat m = min.minimize(d);
		
		System.out.println(m);
		System.out.println((System.currentTimeMillis() - time) + " ms");
		
		/*long time = System.currentTimeMillis();
		RegExp regExp = new RegExp("a(aa)*+a(aa)*b(aaa)*+(aa)*ba(aa)*");
		System.out.println(regExp.toAutomaton());
		System.out.println((System.currentTimeMillis() - time) + " ms");*/
	}
}
