package pl.poznan.put.gol.gui;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.poznan.put.gol.game.Cell;
import pl.poznan.put.gol.game.Cells;
import pl.poznan.put.gol.game.ConwaysCell;
import pl.poznan.put.gol.game.DisplayDriver;
import pl.poznan.put.gol.game.Generation;

public class JavaFXDisplayDriver implements DisplayDriver {

	private int sz;
	private TilePane tilePane = new TilePane(5, 5);

	public JavaFXDisplayDriver(int boardSize, int cellSizePx, Generation generation) {
		sz = boardSize;
		tilePane.setPrefRows(boardSize);
		tilePane.setPrefColumns(boardSize);

		boolean[][] g = toGrid(generation.getAliveCells(), boardSize, boardSize);
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				Color c = g[i][j] ? Color.STEELBLUE : Color.WHITE;
				Rectangle r = new Rectangle(cellSizePx, cellSizePx, c);
				tilePane.getChildren().add(r);

				attachListeners(r, generation, i, j);
			}
		}
	}

	public static boolean[][] toGrid(Cells cells, int rows, int columns) {
		boolean[][] grid = new boolean[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cells.contains(new ConwaysCell(i, j))) {
					grid[i][j] = true;
				}
			}
		}
		return grid;
	}

	@Override
	public void displayGeneration(Generation generation, int rows, int columns) {
		boolean[][] g = toGrid(generation.getAliveCells(), rows, columns);
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				Rectangle r = (Rectangle) tilePane.getChildren().get(boardToPaneCoords(i, j));
				r.setFill(g[i][j] ? Color.STEELBLUE : Color.WHITE);
			}
		}
	}

	public TilePane getPane() {
		return tilePane;
	}

	private int boardToPaneCoords(int i, int j) {
		return i * sz + j;
	}

	private void attachListeners(Rectangle r, Generation generation, int row, int column) {
		r.setOnMousePressed(e -> {
			r.setFill(Color.GRAY);
		});

		r.setOnMouseClicked(e -> {
			Cell clicked = new ConwaysCell(row, column);
			if (generation.getAliveCells().contains(clicked)) {
				generation.getAliveCells().remove(clicked);
				r.setFill(Color.WHITE);
			} else {
				generation.getAliveCells().add(clicked);
				r.setFill(Color.STEELBLUE);
			}
		});
	}
}
