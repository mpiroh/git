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
	
	/*public void vyrobBitKody() {
		long bitKod = 1;
		for (Stav stav : stavy) {
			stav.setBitKod(bitKod);
			bitKod = bitKod * 10;
		}
	}*/
	public void vyrobBitKody() {
		long bitKod = 1;
		for (Stav stav : stavy) {
			stav.setBitKod(bitKod);
			bitKod = bitKod * 10;
		}
	}
	
	public void pridajPrechod(Stav s1, char znak, Stav s2) {
		for (Stav stav : stavy) {
			if (stav == s1) {
				stav.pridajPrechod(znak, s2);
				return;
			}
		}
	}
	
	/*public Stav getStavPodlaBitKodu(long bitKod) {
		for (Stav stav : stavy) {
			if (stav.getBitKod() == bitKod) {
				return stav;
			}
		}
		return null;
	}*/
	public Stav getStavPodlaBitKodu(long bitKod) {
		for (Stav stav : stavy) {
			if (stav.getBitKod() == bitKod) {
				return stav;
			}
		}
		return null;
	}
	
	public List<Stav> getStavyPodlaSkupiny(int skupina) {
		List<Stav> stavyPodlaSkupiny = new ArrayList<>();
		for (Stav stav : stavy) {
			if (stav.getSkupina() == skupina) {
				stavyPodlaSkupiny.add(stav);
			}
		}
		return stavyPodlaSkupiny;
	}
	
	public void vyrobSkupiny() {
		for (Stav stav : stavy) {
			if (koncoveStavy.contains(stav)) {
				stav.setSkupina(1);
			} else {
				stav.setSkupina(2);
			}
		}
	}
	
	public Stav getNejakyStavPodlaSkupiny(int skupina) {
		for (Stav stav : stavy) {
			if (stav.getSkupina() == skupina) {
				return stav;
			}
		}
		return null;
	}
	
	public Automat determinizuj() {
		PrevodNFANaDFA prevod = new PrevodNFANaDFA();
		return prevod.toDFAAutomat(this);
	}
	
	public Automat minimalizuj() {
		Minimalizacia minimalizacia = new Minimalizacia();
		return minimalizacia.minimize(this);
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
			if (koncoveStavy.contains(stav))
				sb.append("(koncový) ");
			sb.append("\n");
			
			sb.append(stav);
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public String toStringBitKody() {
		StringBuilder sb = new StringBuilder();
		for (Stav stav : stavy) {
			sb.append("Stav " + stav.getBitKod() + ": ");
			if (stav == pociatocnyStav)
				sb.append("(poèiatoèný) ");
			if (stav == koncoveStavy)
				sb.append("(koncový) ");
			sb.append("\n");
			
			sb.append(stav.toStringBitKody());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/*public String toStringBezE() {
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
	}*/
	
}
