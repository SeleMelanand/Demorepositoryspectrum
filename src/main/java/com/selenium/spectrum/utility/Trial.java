package com.selenium.spectrum.utility;

import org.testng.annotations.Test;

public class Trial {
	@Test
	public void trialclass() {
		
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println(os);
	}

}
