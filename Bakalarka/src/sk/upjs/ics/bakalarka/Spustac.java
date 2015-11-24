package sk.upjs.ics.bakalarka;

public class Spustac {

	public static void main(String[] args) {
		RegularnyVyraz rv = new RegularnyVyraz("(a+b)*");
		Automat automat = rv.toAutomat();
		
		System.out.println(automat);
	}
}
