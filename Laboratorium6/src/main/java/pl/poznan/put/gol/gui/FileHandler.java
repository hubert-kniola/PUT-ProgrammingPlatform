package pl.poznan.put.gol.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import pl.poznan.put.gol.game.Cells;
import pl.poznan.put.gol.game.ConwaysCell;

public class FileHandler {

	public static Cells openFromFile(int defaultSize) {
		File file = askForOpenFile();
		if (file == null) {
			return null;
		}

		return loadFromFile(file, defaultSize);
	}

	public static Cells loadFromFile(File file, int defaultSize) {
		String input = "";
		int sz = defaultSize;
		try (Scanner s = new Scanner(file)) {
			while (s.hasNextLine()) {
				String line = s.nextLine().replaceAll("\\s+", "");
				input += line;

				sz = line.length();
			}
		} catch (FileNotFoundException e) {
			// should never happen since we return on null file
			// so if we end up here it's something really bad
			// and so we let it blow up to runtime
			throw new RuntimeException(e);
		}

		Cells cells = new Cells();
		int pos = 0;
		for (int i = 0; i < sz; i++) {
			for (int j = 0; j < sz; j++) {
				if (input.charAt(pos) == '1') {
					cells.add(new ConwaysCell(i, j));
				}
				pos++;
			}
		}

		return cells;
	}

	public static void saveToFile(Cells cells, int defaultSize) {
		File file = askForSaveFile();
		if (file == null) {
			return;
		}

		String output = ""; // string of numbers from board
		boolean[][] g = JavaFXDisplayDriver.toGrid(cells, defaultSize, defaultSize);
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				output += g[i][j] ? 1 : 0;
			}
			if (i != g.length - 1) {
				output += "\n";
			}
		}

		try (FileWriter fileWriter = new FileWriter(file)) {
			fileWriter.write(output);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private static File askForSaveFile() {
		return getFileChooser().showSaveDialog(new Stage());
	}

	private static File askForOpenFile() {
		return getFileChooser().showOpenDialog(new Stage());
	}

	private static FileChooser getFileChooser() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Game of Life Board File");
		fileChooser.getExtensionFilters().
				  add(new ExtensionFilter("GOFB files (*.gofb)", "*.gofb"));

		return fileChooser;
	}
}
