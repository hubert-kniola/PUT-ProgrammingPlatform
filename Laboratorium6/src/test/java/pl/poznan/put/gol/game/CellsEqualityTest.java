package pl.poznan.put.gol.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellsEqualityTest {

	@Test
	public void notEquals() {
		Cells cells1 = new Cells();
		cells1.add(new ConwaysCell(0, 0));
		cells1.add(new ConwaysCell(2, 1));

		Cells cells2 = new Cells();
		cells2.add(new ConwaysCell(0, 0));
		cells2.add(new ConwaysCell(0, 1));

		assertNotEquals(cells1, cells2);
	}

	@Test
	public void sameOrder() {
		Cells cells1 = new Cells();
		cells1.add(new ConwaysCell(0, 0));
		cells1.add(new ConwaysCell(0, 1));

		Cells cells2 = new Cells();
		cells2.add(new ConwaysCell(0, 0));
		cells2.add(new ConwaysCell(0, 1));

		assertEquals(cells1, cells2);
	}

	@Test
	public void differentOrder() {
		Cells cells1 = new Cells();
		cells1.add(new ConwaysCell(0, 1));
		cells1.add(new ConwaysCell(0, 0));

		Cells cells2 = new Cells();
		cells2.add(new ConwaysCell(0, 0));
		cells2.add(new ConwaysCell(0, 1));

		assertEquals(cells1, cells2);
	}
}
