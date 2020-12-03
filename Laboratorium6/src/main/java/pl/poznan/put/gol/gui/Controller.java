package pl.poznan.put.gol.gui;

import pl.poznan.put.gol.game.Cells;
import pl.poznan.put.gol.game.ConwaysCell;
import pl.poznan.put.gol.game.ConwaysRules;
import pl.poznan.put.gol.game.Generation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller implements Initializable {

	private final int DEFAULT_SIZE = 15;
	private final double DEFAULT_PROB = 0.3;

	@FXML
	private FlowPane base;
	@FXML
	private Label countLabel;
	@FXML
	private Slider countSlider;
	@FXML
	private HBox presetBox;
	@FXML
	private Button openButton, saveButton, openPresetBtn;
	@FXML
	private Button runButton, stopButton, randomizeButton, clearButton;
	@FXML
	private HBox rootBox;

	private Generation generation;

	private JavaFXDisplayDriver display;

	private Timeline loop = null;

	private int windowWidth = 750;
	private int cellSizePx = 30;

	private PresetHandler presetHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		presetHandler = new PresetHandler();
		AnchorPane anchor = presetHandler.loadPresets(base);
		presetBox.getChildren().add(anchor);

		createBoard(DEFAULT_SIZE, DEFAULT_PROB);

		attachResizeListener();
	}

	@FXML
	private void onRun(Event evt) {
		toggleButtons(false);

		loop = new Timeline(new KeyFrame(Duration.millis(300), e -> {
			generation.evolve();
			display.displayGeneration(generation, DEFAULT_SIZE, DEFAULT_SIZE);
		}));

		loop.setCycleCount(100);
		loop.play();
	}

	@FXML
	private void onStop(Event evt) {
		toggleButtons(true);
		loop.stop();
	}

	@FXML
	private void onClear(Event evt) {
		createBoard(DEFAULT_SIZE, 0);
	}

	@FXML
	private void onRandomize(Event evt) {
		createBoard(DEFAULT_SIZE, (double) countSlider.getValue() / 100);
	}

	@FXML
	private void onPresetOpen(Event evt) {
		generation = new Generation(new ConwaysRules(), presetHandler.openCurrentPreset(
				  DEFAULT_SIZE));
		createDisplay();
	}

	@FXML
	private void onOpen(Event evt) {
		Cells cells = FileHandler.openFromFile(DEFAULT_SIZE);
		if (cells != null) {
			generation = new Generation(new ConwaysRules(), cells);
			createDisplay();
		}
	}

	@FXML
	private void onSave(Event evt) {
		FileHandler.saveToFile(generation.getAliveCells(), DEFAULT_SIZE);
	}

	@FXML
	private void onSlide(Event evt) {
		countSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			countLabel.setText(newValue.intValue() + "%");
			createBoard(DEFAULT_SIZE, (double) newValue.intValue() / 100);
		});
	}

	@FXML
	private void onAbout(Event evt) {
		// TEXT //
		Text text1 = new Text("Conway's Game of Life\n");
		text1.setFont(Font.font(30));
		Text text2 = new Text(
				  "\nThe Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.\n"
				  + "The game is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties."
		);
		Text text3 = new Text("\n\nRules\n");
		text3.setFont(Font.font(20));
		Text text4 = new Text(
				  "\nThe universe of the Game of Life is a two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:\n"
				  + "\n1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.\n"
				  + "2) Any live cell with two or three live neighbours lives on to the next generation.\n"
				  + "3) Any live cell with more than three live neighbours dies, as if by overcrowding.\n"
				  + "4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.\n\nMore on Wikipedia:\n"
		);

		String url = "http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life";
		Hyperlink link = new Hyperlink(url);
		link.setOnAction(__ -> getHostService().showDocument(url));
		TextFlow tf = new TextFlow(text1, text2, text3, text4, link);
		tf.setPadding(new Insets(10, 10, 10, 10));
		tf.setTextAlignment(TextAlignment.JUSTIFY);
		// END TEXT, START WINDOW //
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(new Stage());
		VBox dialogVbox = new VBox(20);
		dialogVbox.getChildren().add(tf);
		Scene dialogScene = new Scene(dialogVbox, 700, 500);
		dialog.setScene(dialogScene);
		dialog.show();
		// END WINDOW //
	}

	private void toggleButtons(boolean enable) {
		countSlider.setDisable(!enable);
		presetBox.setDisable(!enable);
		openButton.setDisable(!enable);
		openPresetBtn.setDisable(!enable);
		saveButton.setDisable(!enable);
		runButton.setDisable(!enable);
		clearButton.setDisable(!enable);
		randomizeButton.setDisable(!enable);

		stopButton.setDisable(enable);
	}

	private void createBoard(int size, double prob) {
		Cells cells = new Cells();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (Math.random() <= prob) {
					cells.add(new ConwaysCell(i, j));
				}
			}
		}
		generation = new Generation(new ConwaysRules(), cells);
		createDisplay();
	}

	private void createDisplay() {
		display = new JavaFXDisplayDriver(DEFAULT_SIZE, cellSizePx, generation);

		base.getChildren().clear();
		base.getChildren().add(new Group(display.getPane()));
	}

	private void attachResizeListener() {
		rootBox.widthProperty().addListener((observable, oldValue, newValue) -> {
			int newWidth = newValue.intValue();
			if (newWidth > 250 && Math.abs(newWidth - windowWidth) >= 50) {
				windowWidth = newWidth;
				cellSizePx = newWidth / 25;
				createDisplay();
			}
		});
	}

	public void setHostServices(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	private HostServices hostServices;

	public HostServices getHostService() {
		return hostServices;
	}

}
