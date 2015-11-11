package sk.upjs.ics.bakalarka.unitTesty;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import sk.upjs.ics.bakalarka.RegularnyVyraz;

public class RegularnyVyrazTest {

	@Test
	public void odstranBodkyTest() {
		RegularnyVyraz rv = new RegularnyVyraz("a.b.c+g+fi+r*d.e");
		rv.odstranBodky();
		
		Assert.assertEquals(rv.getVyraz(), "abc+g+fi+r*de");
	}

}
