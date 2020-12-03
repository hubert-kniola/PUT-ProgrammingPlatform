package pl.poznan.put.gol.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProducingNextGenerationTest {

	@Test
	public void extintGenerationsProducesExtintGeneration() {
		Generation cells = new Generation(new ConwaysRules());

		assertTrue(cells.extinct());

		cells.evolve();

		assertTrue(cells.extinct());
	}

	@Test
	public void aBlockRemainsTheSame() {
		Generation cells = new Generation(new ConwaysRules(), new ConwaysCell(0, 0),
				  new ConwaysCell(0, 1), new ConwaysCell(1, 1), new ConwaysCell(1, 0));
		cells.evolve();

		assertFalse(cells.extinct());
		assertEquals(new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(0,
				  1), new ConwaysCell(1, 1), new ConwaysCell(1, 0)), cells);
	}

	@Test
	public void aCellIsBornAndOnlyItRemains() {
		Generation cells = new Generation(new ConwaysRules(), new ConwaysCell(-1, -1),
				  new ConwaysCell(1, 1), new ConwaysCell(-1, 1));

		cells.evolve();

		assertFalse(cells.extinct());
		assertEquals(new Generation(new ConwaysRules(), new ConwaysCell(0, 0)), cells);
	}

	@Test
	public void OnlyOneCellRemainsAndTwoAreBorn() {
		Generation cells = new Generation(new ConwaysRules(), new ConwaysCell(0, 0),
				  new ConwaysCell(-1, -1), new ConwaysCell(1, 1), new ConwaysCell(-1, 1));

		cells.evolve();

		assertFalse(cells.extinct());
		assertEquals(new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(0,
				  1), new ConwaysCell(-1, 0)), cells);
	}
}
