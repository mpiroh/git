package sk.upjs.ics.bakalarka;

public class Spustac {

	public static void main(String[] args) {
		Automat automat = new Automat();
		RegularnyVyraz rv = new RegularnyVyraz("(((a+b)+b)+c+b)e");
		automat = rv.toAutomat();
		
		System.out.println(automat);
	}
}
