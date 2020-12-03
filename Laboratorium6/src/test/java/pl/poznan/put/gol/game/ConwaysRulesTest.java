package pl.poznan.put.gol.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConwaysRulesTest {

	private ConwaysRules rules;

	@Before
	public void setUp() {
		rules = new ConwaysRules();
	}

	@Test
	public void diesBecauseOfUnderpopulation() {
		assertFalse(rules.inNextGeneration(true, 1));
	}

	@Test
	public void staysAlive() {
		assertTrue(rules.inNextGeneration(true, 2));
		assertTrue(rules.inNextGeneration(true, 3));
	}

	@Test
	public void diesBecauseOfOverpopulation() {
		assertFalse(rules.inNextGeneration(true, 4));
	}

	@Test
	public void aCellIsBorn() {
		assertTrue(rules.inNextGeneration(false, 3));
	}
}
