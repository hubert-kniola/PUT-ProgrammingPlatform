package pl.poznan.put.cie.coffeefx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import pl.poznan.put.cie.coffee.CoffeeModel.Coffee;
import pl.poznan.put.cie.coffee.CoffeeModel.CoffeeDao;
import pl.poznan.put.cie.coffeefx.Model.CoffeeFX;

import java.util.List;

public class FXMLDocumentController{

    private final static String CHART1 = "y = 0";
    private final static String CHART2 = "y = -x^2";
    private final static String CHART3 = "y= x^2 - x + 3";

    private final static String SCATTER = "Scatter chart";
    private final static String LINE = "Line chart";

    @FXML
    private TextField area1, area2, area3;

    @FXML
    private BorderPane mainPane;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private ScatterChart<Number, Number> scatterChart = Task1.createScatter();

    @FXML
    private LineChart<Number, Number> lineChart = Task1.createLine();

    private static int chart = 1;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void handleChartButton(ActionEvent event) throws Exception {
        combo.setItems(FXCollections.observableArrayList(CHART1, CHART2, CHART3));
        lineChart.setVisible(false);
        scatterChart.setTitle("Scatter Chart");
        var root = new VBox(scatterChart, lineChart);
        mainPane.setCenter(root);
    }

    public void handleComboButton(ActionEvent event) {
        Object selectedItem = combo.getSelectionModel().getSelectedItem();
        if (CHART1.equals(selectedItem)) {
            scatterChart.getData().addAll(Task1.createSeries(CHART1, x -> 0D));
            lineChart.getData().addAll(Task1.createSeries(CHART1, x -> 0D));
        } else if (CHART2.equals(selectedItem)) {
            scatterChart.getData().addAll(Task1.createSeries(CHART2, x -> -1 * x * x));
            lineChart.getData().addAll(Task1.createSeries(CHART2, x -> -1 * x * x));
        }
        else if (CHART3.equals(selectedItem)){
            scatterChart.getData().addAll(Task1.createSeries(CHART3, x -> x * x - x + 3));
            lineChart.getData().addAll(Task1.createSeries(CHART3, x -> x * x - x + 3));
        }
    }

    public void handleChangeButton(ActionEvent event)
    {
        switch (chart) {
            case 1:
                lineChart.setVisible(true);
                scatterChart.setVisible(false);
                lineChart.setTitle("Line Chart");
                break;

            case 2:
                lineChart.setVisible(false);
                scatterChart.setVisible(true);
                scatterChart.setTitle("Scatter Chart");
                break;

            default:
                scatterChart.getData().clear();
                lineChart.getData().clear();
                chart = 0;
        }
        chart++;
    }

    public void handleClearButton(ActionEvent event) {
        scatterChart.getData().clear();
        scatterChart.setTitle("");
        lineChart.getData().clear();
        lineChart.setTitle("");
    }

    @FXML
    public void handleDrawButton(ActionEvent event)
    {
        scatterChart.setTitle("Scatter Chart");
        try {
            var a = Double.parseDouble(area1.getText());
            var b = Double.parseDouble(area2.getText());
            var c = Double.parseDouble(area3.getText());

            scatterChart.getData().addAll(Task1
                        .createSeries("y = " + a + "x^2 + " + b + "x + " + c, x -> (a * x * x) + (b * x) + c));

            if(mainPane.getCenter().isDisabled() || !mainPane.getCenter().isVisible()) {
                var root = new VBox(scatterChart);
                mainPane.setCenter(root);
            }
        }
        catch(NumberFormatException e)
        {
            scatterChart.setTitle("Invalid argument!");
            lineChart.setTitle("Invalid argument!");
        }
    }

    @FXML
    public void handleCoffeeButton(ActionEvent event) throws Exception{
        TableView table = new TableView();
        table.setEditable(true);

        var dao = new CoffeeDao();
        ObservableList<CoffeeFX> data = FXCollections.observableArrayList();
        List<Coffee> coffeeList = dao.getAll();
        for(var element : coffeeList)
            System.out.println(element);
        for(var element : coffeeList)
        {
            data.add(new CoffeeFX(element.getName(),
                    element.getSupplierId(),
                    element.getPrice(),
                    element.getSales(),
                    element.getTotal()
                    ));
        }

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(130);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<CoffeeFX,String>("name"));

        TableColumn sup_idCol = new TableColumn("Supplier ID");
        sup_idCol.setMinWidth(130);
        sup_idCol.setCellValueFactory(
                new PropertyValueFactory<CoffeeFX,Integer>("supplierId"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(130);
        priceCol.setCellValueFactory(
                new PropertyValueFactory<CoffeeFX,String>("price"));

        TableColumn salesCol = new TableColumn("Sales");
        salesCol.setMinWidth(130);
        salesCol.setCellValueFactory(
                new PropertyValueFactory<CoffeeFX,Integer>("sales"));

        TableColumn totalCol = new TableColumn("Total");
        totalCol.setMinWidth(130);
        totalCol.setCellValueFactory(
                new PropertyValueFactory<CoffeeFX,Integer>("total"));


        table.setItems(data);
        table.getColumns().addAll(nameCol, sup_idCol, priceCol, salesCol, totalCol);

        var root = new VBox(table);
        mainPane.setCenter(root);
    }
}
