package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui extends Application {
    @Override
    public void start(Stage window) throws Exception {

        // Sliders
        VBox sliderBoxes = new VBox();

        BorderPane savingsPane = new BorderPane();
        savingsPane.setLeft(new Label("Monthly Savings"));
        Slider savingsSlider = new Slider(25,250,25);
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

        // Charts
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis(0, 27500, 2500);
        LineChart<Number, Number> chart = new LineChart(xAxis, yAxis);
        chart.setTitle("Savings calculator");

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
