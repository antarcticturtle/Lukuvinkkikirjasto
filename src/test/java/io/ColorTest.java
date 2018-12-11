package io;

import org.junit.Test;
import static org.junit.Assert.*;


public class ColorTest {
	
	public ColorTest() {
	}

	@Test
	public void cyanTextTest() {
		String text = "text";
		assertEquals("\u001B[36mtext\u001B[0m", Color.cyan(text));
	}
	
	@Test
	public void yellowTextTest() {
		String text = "text";
		assertEquals("\u001B[33mtext\u001B[0m", Color.yellow(text));
	}
}
