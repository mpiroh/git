package sk.upjs.ics.bakalarka.unitTesty;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import sk.upjs.ics.bakalarka.Automat;
import sk.upjs.ics.bakalarka.Stav;

public class AutomatTest {

	@Test
	public void pridajStavTest() {
		Automat automat = new Automat();
		Stav s1 = new Stav();
		Stav s2 = new Stav();
		Stav s3 = new Stav();
		
		automat.pridajStav(s1);
		automat.pridajStav(s2);
		automat.pridajStav(s3);
		
		Assert.assertEquals(automat.getStavy().size(), 3);
	}

}
