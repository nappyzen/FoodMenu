package controller;

import dao.DatabaseHelperDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ReportsController extends VBox {

    @FXML private PieChart pieChart;
    @FXML private Label lblDelivery;

    private ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList();

    public ReportsController() {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/reports.fxml"));

        fxml.setRoot(this);
        fxml.setController(this);
        try {
            fxml.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws Exception {
        valueList.addAll(DatabaseHelperDao.getControlDao().getReportsDao().getAllFoodBasedOnDelivery());
        pieChart.getData().setAll(valueList);

        final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    int foodCount = (int) data.getPieValue();
                    String foodCountLabel = "";
                    if(foodCount < 2)
                        foodCountLabel = "food";
                    else
                        foodCountLabel = "foods";
                    lblDelivery.setText("Delivery mode: " + data.getName() + " -> " + foodCount + " " + foodCountLabel);
                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
    }

}
