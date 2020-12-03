package pl.poznan.put.gol.game;

import org.junit.Test;
import static org.junit.Assert.*;

public class GenerationTest {

	@Test
	public void testCountAliveNeighbors() {
		Generation generation = new Generation(null,
				  new ConwaysCell(0, 0), new ConwaysCell(1, 0),
				  new ConwaysCell(0, 1), new ConwaysCell(2, 2));
		assertEquals(2, generation.countAliveNeighbors(new ConwaysCell(0, 0)));
		assertEquals(2, generation.countAliveNeighbors(new ConwaysCell(0, 1)));
		assertEquals(2, generation.countAliveNeighbors(new ConwaysCell(1, 0)));
		assertEquals(0, generation.countAliveNeighbors(new ConwaysCell(2, 2)));
		assertEquals(1, generation.countAliveNeighbors(new ConwaysCell(3, 3)));
		assertEquals(4, generation.countAliveNeighbors(new ConwaysCell(1, 1)));
	}

	@Test
	public void testIsAlive() {
		Generation generation = new Generation(null,
				  new ConwaysCell(0, 0), new ConwaysCell(1, 0));
		assertTrue(generation.isAlive(new ConwaysCell(0, 0)));
		assertTrue(generation.isAlive(new ConwaysCell(1, 0)));
		assertFalse(generation.isAlive(new ConwaysCell(0, 1)));
		assertFalse(generation.isAlive(new ConwaysCell(1, 1)));
	}

	@Test
	public void testExtinct() {
		Generation generation = new Generation(null);
		assertTrue(generation.extinct());
	}

}
