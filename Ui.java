package calculator;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Ui extends Application {
    @Override
    public void start(Stage window) throws Exception {

        // Sliders
        VBox sliderBoxes = new VBox();

        BorderPane savingsPane = new BorderPane();
        savingsPane.setLeft(new Label("Monthly Savings"));
        Slider savingsSlider = new Slider(25,250,25);
        double savingsValue = savingsSlider.getValue();

//        savingsSlider.valueProperty().addListener((a)->);
        savingsSlider.setShowTickMarks(true);
        savingsSlider.setShowTickLabels(true);
        savingsPane.setCenter(savingsSlider);

        BorderPane interestPane = new BorderPane();
        interestPane.setLeft(new Label("Yearly Interest rate"));
        Slider interestSlider = new Slider(0,10,0);
        interestSlider.setShowTickMarks(true);
        interestSlider.setShowTickLabels(true);
        interestSlider.setMajorTickUnit(1);
        interestPane.setCenter(interestSlider);

        sliderBoxes.getChildren().addAll(savingsPane, interestPane);

        // Axis
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis(0, 27500, 2500);

        // Data
        Map<String, Map<Integer, Double>> data = new HashMap<>();
        data.putIfAbsent("savings", new HashMap<>());
        data.putIfAbsent("interest", new HashMap<>());

        for (int i=0; i<=xAxis.getUpperBound(); i++) {
            data.get("savings").putIfAbsent(i, (double) i*savingsSlider.getValue());
            data.get("interest").putIfAbsent(i, (double) i*600*(1+0.05));
        }
        savingsSlider.valueProperty().addListener((observable, oldValue, newValue)->{
            for (int i=0; i<=xAxis.getUpperBound(); i++) {
                data.get("savings").put(i, (double) i*newValue);
            }
        });

        // Chart
        LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
        chart.setTitle("Savings calculator");
        for (String key: data.keySet()) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(key);
            ObservableList<XYChart.Data<Number, Number >> list_series = series.getData();
            for (Map.Entry<Integer, Double>  pair: data.get(key).entrySet()) {
                list_series.add(new XYChart.Data(pair.getKey(), pair.getValue()));
            }
            chart.getData().addAll(series);

        }

        // Main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(sliderBoxes);
        mainPane.setCenter(chart);
        Scene scene = new Scene(mainPane);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(Ui.class);
    }
}
