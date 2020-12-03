package pl.poznan.put.gol.game;

import org.junit.Test;
import pl.poznan.put.gol.game.Cells;
import pl.poznan.put.gol.game.ConwaysCell;
import pl.poznan.put.gol.game.Cell;

import static org.junit.Assert.*;

public class ConwaysCellTest {

    @Test
    public void correctNeighbors() {
        Cell cell = new ConwaysCell(0, 0);

        Cells cells = new Cells(new ConwaysCell(-1, -1),
                new ConwaysCell(-1, 0),
                new ConwaysCell(-1, 1),
                new ConwaysCell(0, -1),
                new ConwaysCell(0, 1),
                new ConwaysCell(1, -1),
                new ConwaysCell(1, 0),
                new ConwaysCell(1, 1));

        assertEquals(cells, cell.neighbors());
    }

    @Test
    public void testEquals() {
        var cell1 = new ConwaysCell(0, 1);
        var cell2 = new ConwaysCell(0, 1);
        var cell3 = new ConwaysCell(1, 1);

        assertEquals(cell1, cell2);
        assertNotEquals(cell2, cell3);
    }
}