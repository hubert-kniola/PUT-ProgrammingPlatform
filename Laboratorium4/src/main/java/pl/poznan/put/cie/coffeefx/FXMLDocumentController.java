package pl.poznan.put.cie.coffeefx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.util.Callback;
import pl.poznan.put.cie.coffee.CoffeeModel.Coffee;
import pl.poznan.put.cie.coffee.CoffeeModel.CoffeeDao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDocumentController{

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button button1, button2, button3, button4;

    @FXML
    private TextArea area1, area2, area3;

    @FXML
    private Label label;

    @FXML
    private BorderPane mainPane;

    @FXML
    private ComboBox combo;


    @FXML
    public void initialize(URL url, ResourceBundle rb)
    {

    }


    private static final Integer MAX_Y = 50;
    private static final Integer MAX_X = 50;

    private int chart = 1;

    @FXML
    public void Task1(ActionEvent event) throws Exception {
        var scatter = Task1Controller.createChart();
        button1 = new Button() {{
            setText("Change chart");
            button1.setOnAction(event -> {
                switch (chart) {
                    case 1:
                        scatter.getData().addAll(Task1Controller.createSeries("y = 0", x -> 0D));

                        break;

                    case 2:
                        scatter.getData().addAll(Task1Controller.createSeries("y = -x^2", x -> -1 * x * x));

                        break;

                    case 3:
                        scatter.getData().addAll(Task1Controller.createSeries("y= x^2 - x + 3", x -> x * x - x + 3));

                        break;

                    default:
                        scatter.getData().clear();
                        chart = 0;
                }

                chart++;
            });
        }};
        /*button1 = new Button ("Send");
        combo.setEditable(true);

        combo = new ComboBox();
        combo.getItems().addAll(
                "f(x) = 0",
                "f(x) = -x^2",
                "f(x) = x^2 - x + 3");

        combo.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override public ListCell<String> call(ListView<String> param) {
                        final ListCell<String> cell = new ListCell<String>() {
                            {
                                super.setPrefWidth(100);
                            }
                            @Override public void updateItem(String item,
                                                             boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    if (item.contains("f(x) = 0")) {
                                        scatter.getData().addAll(Task1Controller.createSeries("y = 0", x -> 0D));
                                    }
                                    else if (item.contains("f(x) = -x^2")){
                                        scatter.getData().addAll(Task1Controller.createSeries("y = -x^2", x -> -1 * x * x));
                                    }
                                    else if (item.contains("f(x) = x^2 - x + 3")){
                                        scatter.getData().addAll(Task1Controller.createSeries("y= x^2 - x + 3", x -> x * x - x + 3));
                                    }
                                    else {
                                        scatter.getData().addAll(Task1Controller.createSeries("y = 0", x -> 0D));
                                    }
                                }
                                else {
                                    scatter.getData().addAll(Task1Controller.createSeries("y = 0", x -> 0D));
                                }
                            }
                        };
                        return cell;
                    }
                });

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (combo.getValue() != null && combo.getValue().toString() == "f(x) = 0")
                {
                    scatter.getData().addAll(Task1Controller.createSeries("y = 0", x -> 0D));
                } else if (combo.getValue() != null && combo.getValue().toString() == "f(x) = 0"){
                    scatter.getData().addAll(Task1Controller.createSeries("y= x^2 - x + 3", x -> x * x - x + 3));
                    }
                else if (combo.getValue() != null && combo.getValue().toString() == "f(x) = 0"){
                    scatter.getData().addAll(Task1Controller.createSeries("y= x^2 - x + 3", x -> x * x - x + 3));
                }
                else {
                 scatter.getData().addAll(Task1Controller.createSeries("y = 0", x -> 0D));
                }
            }});*/


        var root = new VBox(scatter);
        mainPane.setCenter(root);
    }

    @FXML
    public void Task2()
    {
        var scatter = Task1Controller.createChart();
        button3 = new Button() {{
            button3.setOnAction(event -> {scatter.getData().addAll(Task1Controller
                    .createSeries("y = Ax^2 + Bx + C",
                            x -> (Double.parseDouble(area1.getText()) * x * x)
                                    + (Double.parseDouble(area2.getText()) * x)
                                    + Double.parseDouble(area3.getText())));});}};
        var root = new VBox(scatter);
        mainPane.setCenter(root);
    }


    @FXML
    public void Task3(ActionEvent event) throws Exception{
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
