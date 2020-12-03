package pl.poznan.put.gol.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOfLife extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gui.fxml"));
		Parent parent = loader.load();
		Controller controller = loader.getController();
		controller.setHostServices(getHostServices());

		primaryStage.setTitle("Conway's Game of Life");
		primaryStage.setScene(new Scene(parent));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
