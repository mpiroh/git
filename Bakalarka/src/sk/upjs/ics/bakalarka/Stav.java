package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.List;

public class Stav {
	public static final int MAX_ZNAKOV = 26;
	public static final int POSUN = 97;

	int id;
	boolean jePociatocny;
	boolean jeKoncovy;
	List<Stav> prechody[] = new ArrayList[MAX_ZNAKOV];
	List<Stav> epsilonPrechody = new ArrayList();

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
	public boolean isJePociatocny() {
		return jePociatocny;
	}

	public void setJePociatocny(boolean jePociatocny) {
		this.jePociatocny = jePociatocny;
	}

	public boolean isJeKoncovy() {
		return jeKoncovy;
	}

	public void setJeKoncovy(boolean jeKoncovy) {
		this.jeKoncovy = jeKoncovy;
	}

	public List<Stav>[] getPrechody() {
		return prechody;
	}

	public void setPrechody(List<Stav>[] prechody) {
		this.prechody = prechody;
	}

	public List<Stav> getEpsilonPrechody() {
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
}
