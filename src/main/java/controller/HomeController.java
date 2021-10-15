package controller;

import com.jfoenix.controls.JFXButton;
import dao.DatabaseHelperDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Food;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    @FXML
    private JFXButton btnShowDishes, btnDishNotes, btnReports, btnQuit, btnAdd, btnEdit;

    @FXML
    private VBox mainVbox;

    @FXML
    private HBox hbox;

    @FXML
    private ListView<String> listOfDishes;

    @FXML
    private TableView<Food> tableDishes;

    @FXML
    private TableColumn<Food, String> tblColId;

    @FXML
    private TableColumn<Food, String> tblColName, tblColIngredients, tblColPrice,
            tblColDelivery, tblColCategory, tblColNotes;

    public ObservableList<Food> data = FXCollections.observableArrayList();
    public static Food foodDataHolder = new Food();
    public static boolean edit = false;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateData();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void populateData() throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();
        tableDishes.getItems().clear();
        List<Food> foods = DatabaseHelperDao.getControlDao().getFoodDao().getAllFood();
        for(Food f: foods)
            list.addAll(f.getDishName());
        listOfDishes.setItems(list);
        data.clear();
        data.addAll(foods);

        tblColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblColName.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        tblColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblColCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblColNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        tblColIngredients.setCellValueFactory(new PropertyValueFactory<>("ingredients"));
        tblColDelivery.setCellValueFactory(new PropertyValueFactory<>("deliveryMode"));

        listOfDishes.setOnMouseClicked(event -> {
            String name = listOfDishes.getSelectionModel().getSelectedItem();
            tableDishes.setItems(FXCollections.observableArrayList(data.stream().filter(e -> e.getDishName().equals(name)).collect(Collectors.toList())));
        });
    }

    @FXML
    public void add() throws Exception {
        edit = false;
        openOpenEditScene(mainVbox, "add-food");
        populateData();
    }

    @FXML
    public void edit() throws Exception {
        edit = true;
        if(tableDishes.getSelectionModel().getSelectedItem() != null)
            getData();
        else
            showAlert("Warning!", "Choose a record from table.", Alert.AlertType.WARNING);
    }

    public void showAlert(String title, String text, Alert.AlertType type) {
        Alert alert = new Alert (type,text);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }


    private void getData() throws IOException, SQLException {
        Food food = tableDishes.getSelectionModel().getSelectedItem();
        foodDataHolder.setDishName(food.getDishName());
        foodDataHolder.setPrice(food.getPrice());
        foodDataHolder.setCategory(food.getCategory());
        foodDataHolder.setId(food.getId());
        foodDataHolder.setNotes(food.getNotes());
        foodDataHolder.setDeliveryMode(food.getDeliveryMode());
        foodDataHolder.setIngredients(food.getIngredients());

        openOpenEditScene(mainVbox, "add-food");
        populateData();
    }

    public void openOpenEditScene(VBox vbox,String view_name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    void dishNotes() {
        Controllers.getNotes(mainVbox);
    }

    @FXML
    void quit() {
        System.exit(0);
    }

    @FXML
    void reports() {
        Controllers.getReports(mainVbox);
    }

    @FXML
    void showDishes() {
        mainVbox.getChildren().clear();
        mainVbox.getChildren().add(hbox);
    }

}
