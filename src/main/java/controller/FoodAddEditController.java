package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dao.DatabaseHelperDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Food;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodAddEditController implements Initializable {

	@FXML
	private JFXTextField txtDishName, txtPrice, txtCategory;

	@FXML
	private JFXComboBox<String> cmbDelivery;

	@FXML
	private JFXButton btnCancel;

	@FXML
	private Label lblError;

	@FXML
	private JFXTextArea txtAreaIngredients;

	private int foodId = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		makeTextfieldDecimal(txtPrice);
		cmbDelivery.getItems().addAll("Pick-Up","Home Delivery");
		if(HomeController.edit == true) {
			foodId = HomeController.foodDataHolder.getId();
			getData(HomeController.foodDataHolder);
		}
		else
			foodId = 0;
	}
	
	private void getData(Food f) {
		txtDishName.setText(f.getDishName());
		txtPrice.setText(Double.parseDouble(f.getPrice()) + "");
		txtCategory.setText(f.getCategory() + "");
		cmbDelivery.setValue(f.getDeliveryMode());
		txtAreaIngredients.setText(f.getIngredients());
	}

	private boolean checkEmptyCombobox(JFXComboBox<?>... combos ) {
		for (JFXComboBox<?> s : combos)
			if (s.getValue() == null || s.getValue() == "")
				return true;

		return false;
	}

	private boolean checkEmptyText(String... strings) {
		for(String s : strings)
			if(s == null || s.isEmpty())
				return true;

		return false;
	}

	@FXML
	public void save() throws Exception {
		if(checkEmptyCombobox(cmbDelivery) || checkEmptyText(txtDishName.getText(),
				txtCategory.getText(), txtPrice.getText())) {
			lblError.setText("Fill out the fields!*");
			return;
		}

		Food food = new Food();
		food.setId(foodId);
		food.setDishName(txtDishName.getText());
		food.setPrice(txtPrice.getText());
		food.setCategory(txtCategory.getText());
		food.setDeliveryMode(cmbDelivery.getValue());
		food.setIngredients(txtAreaIngredients.getText());
		
		if(foodId == 0)
			DatabaseHelperDao.getControlDao().getFoodDao().insertFood(food);
		else
			DatabaseHelperDao.getControlDao().getFoodDao().updateFood(food);

		closeStage(btnCancel);
	}

	
	@FXML
	private void cancel() {
		closeStage(btnCancel);
	}

	private void closeStage(JFXButton button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}

	private void makeTextfieldDecimal(JFXTextField txt) {
		txt.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d{0,5}([,.]\\d{0,3})?")) {
				txt.setText(oldValue);
			}
		});
	}
}
