package sk.upjs.ics.bakalarka.unitTesty;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import sk.upjs.ics.bakalarka.Stav;

public class StavTest {

	private static final int POSUN = 97;

	@Test
	public void pridajPrechodTest() {
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		
		s1.pridajPrechod('a', s2);
		s1.pridajPrechod('a', s1);
		
		Assert.assertEquals(s1.getPrechody()[(int)'a' - POSUN].size(), 2);
	}
	
	@Test
	public void pridajEpsilonPrechodTest() {
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		
		s1.pridajEpsilonPrechod(s2);
				
		Assert.assertEquals(s1.getEpsilonPrechody().size(), 1);
	}

}
