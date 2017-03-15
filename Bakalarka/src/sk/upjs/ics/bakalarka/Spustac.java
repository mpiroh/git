package sk.upjs.ics.bakalarka;

import java.util.List;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class Spustac {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		/*long time = System.currentTimeMillis();
		RegularnyVyraz rv = new RegularnyVyraz("a(b*ab*aa)*b*");
		PrevodRegVyrazNaNFA p = new PrevodRegVyrazNaNFA();
		PrevodNFANaDFA pp = new PrevodNFANaDFA();
		Minimalizacia min = new Minimalizacia();
		
		Automat n = p.toAutomat(rv.getVyraz());
		Automat d = pp.toDFAAutomat(n);
		Automat m = min.minimize(d);
		
		System.out.println(m);
		System.out.println((System.currentTimeMillis() - time) + " ms");*/
		
		/*long time = System.currentTimeMillis();
		RegExp regExp = new RegExp("a(aa)*+a(aa)*b(aaa)*+(aa)*ba(aa)*");
		System.out.println(regExp.toAutomaton());
		System.out.println((System.currentTimeMillis() - time) + " ms");*/
		
		long time = System.currentTimeMillis();
		Porovnavac p = new Porovnavac();
		System.out.println(p.compare("((aa+bb)+(ab+ba)(aa+bb)*(ab+ba))*",
									 "((aa+bb)*(ab+ba)(aa+bb)*(ab+ba))*(aa+bb)*"));
		System.out.println("Time: " + (System.currentTimeMillis()-time));
		
		/*RegExp r = new RegExp("a(b*ab*aa)*");
		RegExp rr = new RegExp("a(b+ab*aa)*");
		Automaton a = new Automaton();
		System.out.println("prvy " + r.toAutomaton());
		System.out.println("druhy " + rr.toAutomaton());*/
		
	}
}
