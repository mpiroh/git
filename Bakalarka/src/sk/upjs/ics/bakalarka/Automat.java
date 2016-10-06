package sk.upjs.ics.bakalarka;

import java.util.ArrayList;
import java.util.List;

public class Automat {
	private List<Stav> stavy = new ArrayList<Stav>();
	private Stav pociatocnyStav;
	private List<Stav> koncoveStavy = new ArrayList<Stav>();
	
	public void pridajStav(Stav stav) {
		stavy.add(stav);
	}
	
	public void pridajKoncovyStav(Stav stav) {
		koncoveStavy.add(stav);
	}
	
	public void vyrobId() {
		int id = 0;
		for (Stav stav : stavy) {
			stav.setId(id);
			id++;
		}
	}
	
	public Automat determinizuj() {
		PrevodNFANaDFA prevod = new PrevodNFANaDFA();
		return prevod.toDFAAutomat(this);
	}
	
	//------------------------------------------------------------------------
	public Stav getPociatocnyStav() {
		return pociatocnyStav;
	}

	public void setPociatocnyStav(Stav pociatocnyStav) {
		this.pociatocnyStav = pociatocnyStav;
	}

	public List<Stav> getKoncoveStavy() {
		return koncoveStavy;
	}

	public void setKoncoveStavy(List<Stav> koncovyStav) {
		this.koncoveStavy = koncovyStav;
	}
	
	public List<Stav> getStavy() {
		return stavy;
	}

	public void setStavy(List<Stav> stavy) {
		this.stavy = stavy;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Stav stav : stavy) {
			sb.append("Stav " + stav.getId() + ": ");
			if (stav == pociatocnyStav)
				sb.append("(poèiatoèný) ");
			if (stav == koncoveStavy)
				sb.append("(koncový) ");
			sb.append("\n");
			
			sb.append(stav);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public String toStringBezE() {
		StringBuilder sb = new StringBuilder();
		for (Stav stav : stavy) {
			sb.append("Stav " + stav.getId() + ": ");
			if (stav == pociatocnyStav)
				sb.append("(poèiatoèný) ");
			if (koncoveStavy.contains(stav))
				sb.append("(koncový) ");
			sb.append("\n");
			
			sb.append(stav.toStringBezE());
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
