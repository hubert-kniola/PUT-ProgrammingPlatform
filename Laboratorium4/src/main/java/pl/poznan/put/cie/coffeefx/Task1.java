package pl.poznan.put.cie.coffeefx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Function;

public class Task1 {

    private static final Integer MAX_Y = 50;
    private static final Integer MAX_X = 50;

    public static ScatterChart<Number, Number> createScatter() {
        var x = new NumberAxis(-MAX_X, MAX_X, 10);
        x.setLabel("x");

        var y = new NumberAxis(-MAX_Y, MAX_Y, 10);
        y.setLabel("f(x)");

        return new ScatterChart<>(x, y);
    }

    public static LineChart<Number, Number> createLine() {
        var x = new NumberAxis(-MAX_X, MAX_X, 10);
        x.setLabel("x");

        var y = new NumberAxis(-MAX_Y, MAX_Y, 10);
        y.setLabel("f(x)");

        return new LineChart<>(x, y);
    }

    public static XYChart.Series<Number, Number> createSeries(String name, Function<Double, Double> fun) {
        var series = new XYChart.Series<Number, Number>();
        series.setName(name);

        for (int x = -MAX_X; x < MAX_X; x++) {
            series.getData().add(new XYChart.Data<>(x, fun.apply((double) x)));
        }

        return series;
    }
}
