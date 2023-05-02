package es.deusto.supermarket.client;


import static org.junit.Assert.assertEquals;

import javax.swing.JProgressBar;

import org.junit.Test;
import org.junit.experimental.categories.Category;


import categories.PerformanceTest;



@Category(PerformanceTest.class)
public class VentanaLoadingTest {
	@Test
	public void valor100Test() {
		JProgressBar bar = new JProgressBar();
		bar.setValue(100);
		assertEquals(100, bar.getValue());
	}

}