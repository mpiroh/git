package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.List;

public class Stav {
	public static final int MAX_ZNAKOV = 26;
	public static final int POSUN = 97;

	private int id;
	private long bitKod;
	private int skupina;
	
	private ArrayList<Stav> prechody[] = new ArrayList[MAX_ZNAKOV];
	private ArrayList<Stav> epsilonPrechody = new ArrayList();

	public Stav() {
		for (int i = 0; i < prechody.length; i++) {
			prechody[i] = new ArrayList();
		}
	}

	public void pridajPrechod(char znak, Stav stav) {
		prechody[(int) znak - POSUN].add(stav);
	}

	public void pridajEpsilonPrechod(Stav stav) {
		epsilonPrechody.add(stav);
	}

	//------------------------------------------------------------------------

	public ArrayList<Stav>[] getPrechody() {
		return prechody;
	}

	public void setPrechody(ArrayList<Stav>[] prechody) {
		this.prechody = prechody;
	}

	public ArrayList<Stav> getEpsilonPrechody() {
		return epsilonPrechody;
	}

	public void setEpsilonPrechody(ArrayList<Stav> epsilonPrechody) {
		this.epsilonPrechody = epsilonPrechody;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long getBitKod() {
		return bitKod;
	}

	public void setBitKod(long bitKod) {
		this.bitKod = bitKod;
	}

	public int getSkupina() {
		return skupina;
	}

	public void setSkupina(int skupina) {
		this.skupina = skupina;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (char i = 0; i < prechody.length; i++) {
			if (prechody[i].isEmpty())
				continue;
			char znak = (char) (i + POSUN);
			for (Stav stav : prechody[i]) {
				sb.append(znak + " -> ");
				sb.append(stav.getId());
				sb.append("\n");
			}
		}
		for (Stav stav : epsilonPrechody) {
			sb.append("E" + " -> ");
			sb.append(stav.getId());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public String toStringBezE() {
		StringBuilder sb = new StringBuilder();

		for (char i = 0; i < prechody.length; i++) {
			if (prechody[i].isEmpty())
				continue;
			char znak = (char) (i + POSUN);
			for (Stav stav : prechody[i]) {
				sb.append(znak + " -> ");
				sb.append(stav.getId());
				sb.append("\n");
			}
		}		
		return sb.toString();
	}
	
	public String toStringBitKody() {
		StringBuilder sb = new StringBuilder();

		for (char i = 0; i < prechody.length; i++) {
			if (prechody[i].isEmpty())
				continue;
			char znak = (char) (i + POSUN);
			for (Stav stav : prechody[i]) {
				sb.append(znak + " -> ");
				sb.append(stav.getBitKod());
				sb.append("\n");
			}
		}
		for (Stav stav : epsilonPrechody) {
			sb.append("E" + " -> ");
			sb.append(stav.getBitKod());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
