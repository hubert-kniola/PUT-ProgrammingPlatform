package pl.poznan.put.gol.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConwaysGameOfLifeTest {

	private ConwaysRules conwayRules;

	@Before
	public void setUp() {
		conwayRules = new ConwaysRules();
	}

	@Test
	public void aExtinctGameRemainsExtinct() {
		Generation g = aExtinctGeneration();
		for (int i = 0; i < 15; i++) {
			g.evolve();
		}

		assertEquals(aExtinctGeneration(), g);
		assertTrue(g.getAliveCells().isEmpty());
	}

	private Generation aExtinctGeneration() {
		return new Generation(new ConwaysRules(), new Cells());
	}

	@Test
	public void aBlockIsStillLife() {
		Generation g = aBlock();
		for (int i = 0; i < 15; i++) {
			g.evolve();
		}

		assertEquals(expectedForBlock(), g);
	}

	@Test
	public void aBeehiveIsStillLife() throws Exception {
		Generation g = aBeehive();
		for (int i = 0; i < 15; i++) {
			g.evolve();
		}

		assertEquals(expectedForBeehive(), g);
	}

	@Test
	public void aLoafIsStillLife() throws Exception {
		Generation g = aLoaf();
		for (int i = 0; i < 15; i++) {
			g.evolve();
		}

		assertEquals(expectedForLoaf(), g);
	}

	@Test
	public void aBoatIsStillLife() throws Exception {
		Generation g = aBoat();
		for (int i = 0; i < 15; i++) {
			g.evolve();
		}

		assertEquals(expectedForBoat(), g);
	}

	@Test
	public void aBlinkerOscillatesWithPeriodTwo() throws Exception {
		Generation g = aBlinker();

		g.evolve();
		assertEquals(expectedForBlinker1(), g);

		g.evolve();
		assertEquals(expectedForBlinker2(), g);
	}

	@Test
	public void aToadOscillatesWithPeriodTwo() throws Exception {
		Generation g = aToad();

		g.evolve();
		assertEquals(expectedForToad1(), g);

		g.evolve();
		assertEquals(expectedForToad2(), g);
	}

	@Test
	public void aGliderMovesDiagonallyWithPeriod4() throws Exception {
		Generation g = aGlider();

		g.evolve();
		assertEquals(expectedForGlider1(), g);

		g.evolve();
		assertEquals(expectedForGlider2(), g);

		g.evolve();
		assertEquals(expectedForGlider3(), g);

		g.evolve();
		assertEquals(expectedForGlider4(), g);
	}

	// http://www.conwaylife.com/wiki/Beehive
	private Generation aBeehive() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 1),
				  new ConwaysCell(0, 2), new ConwaysCell(1, 0), new ConwaysCell(1, 3),
				  new ConwaysCell(2, 1), new ConwaysCell(2, 2));
		return cells;
	}

	private Generation expectedForBeehive() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 1), new ConwaysCell(0, 2),
				  new ConwaysCell(1, 0), new ConwaysCell(1, 3), new ConwaysCell(2, 1),
				  new ConwaysCell(2, 2));
	}

	// http://en.wikipedia.org/wiki/Still_life_%28cellular_automaton%29#Loaves
	private Generation aLoaf() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 1),
				  new ConwaysCell(0, 2), new ConwaysCell(1, 0), new ConwaysCell(1, 3),
				  new ConwaysCell(2, 1), new ConwaysCell(2, 3), new ConwaysCell(3, 2));
		return cells;
	}

	private Generation expectedForLoaf() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 1), new ConwaysCell(0, 2),
				  new ConwaysCell(1, 0), new ConwaysCell(1, 3), new ConwaysCell(2, 1),
				  new ConwaysCell(2, 3), new ConwaysCell(3, 2));
	}

	// http://en.wikipedia.org/wiki/Still_life_%28cellular_automaton%29#Blocks
	private Generation aBlock() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 0),
				  new ConwaysCell(0, 1), new ConwaysCell(1, 1), new ConwaysCell(1, 0));
		return cells;
	}

	private Generation expectedForBlock() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(0, 1),
				  new ConwaysCell(1, 1), new ConwaysCell(1, 0));
	}

	// http://commons.wikimedia.org/wiki/File:Game_of_life_boat.svg
	private Generation aBoat() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 0),
				  new ConwaysCell(0, 1), new ConwaysCell(1, 0), new ConwaysCell(1, 2),
				  new ConwaysCell(2, 1));
		return cells;
	}

	private Generation expectedForBoat() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(0, 1),
				  new ConwaysCell(1, 0), new ConwaysCell(1, 2), new ConwaysCell(2, 1));
	}

	// http://en.wikipedia.org/wiki/File:Game_of_life_blinker.gif
	private Generation aBlinker() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 0),
				  new ConwaysCell(0, 1), new ConwaysCell(0, 2));
		return cells;
	}

	private Generation expectedForBlinker2() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 1), new ConwaysCell(0, 2),
				  new ConwaysCell(0, 0));
	}

	private Generation expectedForBlinker1() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 1), new ConwaysCell(1, 1),
				  new ConwaysCell(-1, 1));
	}

	// http://en.wikipedia.org/wiki/File:Game_of_life_toad.gif
	private Generation aToad() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 1),
				  new ConwaysCell(0, 2), new ConwaysCell(0, 3), new ConwaysCell(1, 0),
				  new ConwaysCell(1, 1), new ConwaysCell(1, 2));
		return cells;
	}

	private Generation expectedForToad2() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 3), new ConwaysCell(1, 0),
				  new ConwaysCell(1, 2), new ConwaysCell(0, 2), new ConwaysCell(1, 1),
				  new ConwaysCell(0, 1));
	}

	private Generation expectedForToad1() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 3), new ConwaysCell(1, 0),
				  new ConwaysCell(0, 0), new ConwaysCell(-1, 2), new ConwaysCell(1, 3),
				  new ConwaysCell(2, 1));
	}

	// http://en.wikipedia.org/wiki/File:Game_of_life_animated_glider.gif
	private Generation aGlider() {
		Generation cells = new Generation(conwayRules, new ConwaysCell(0, 0),
				  new ConwaysCell(0, 1), new ConwaysCell(0, 2), new ConwaysCell(1, 0),
				  new ConwaysCell(2, 1));
		return cells;
	}

	private Generation expectedForGlider4() {
		return new Generation(new ConwaysRules(), new ConwaysCell(-1, 1), new ConwaysCell(-1, 0),
				  new ConwaysCell(0, -1), new ConwaysCell(1, 0), new ConwaysCell(-1, -1));
	}

	private Generation expectedForGlider3() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(-1, 1),
				  new ConwaysCell(-1, 0), new ConwaysCell(1, 1), new ConwaysCell(0, -1));
	}

	private Generation expectedForGlider2() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(1, 0),
				  new ConwaysCell(-1, 1), new ConwaysCell(-1, 0), new ConwaysCell(0, 2));
	}

	private Generation expectedForGlider1() {
		return new Generation(new ConwaysRules(), new ConwaysCell(0, 0), new ConwaysCell(0, 1),
				  new ConwaysCell(1, 0), new ConwaysCell(-1, 1), new ConwaysCell(1, 2));
	}
}
