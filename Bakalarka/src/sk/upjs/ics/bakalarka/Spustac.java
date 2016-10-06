package sk.upjs.ics.bakalarka;

public class Spustac {
	public static final int POSUN = 97;
	
	public static void main(String[] args) {
		RegularnyVyraz rv = new RegularnyVyraz("a*");
		Automat automat = rv.toAutomat();
				
		System.out.println(automat);
		
		//PrevodNFANaDFA prevod = new PrevodNFANaDFA();
		//prevod.setAutomat(automat);
		//prevod.odstranEpsilonPrechody();
		//System.out.println(prevod.getAutomat().toStringBezE());
		
		//System.out.println((char)(1 + POSUN));
		
	}
}
