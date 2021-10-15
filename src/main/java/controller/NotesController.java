package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import dao.DatabaseHelperDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import model.Food;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class NotesController extends VBox {

	@FXML private JFXTextArea txtAreaNotes;
	@FXML private Label dishName;
	@FXML private JFXButton btnEdit, btnAddNote;
	@FXML
	private ListView<String> listOfDishes;
	
	private final static String OUTPUT_NAME = "Notes for dish: ";
	
	public NotesController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/notes.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String dishNameFromList = "";

	@FXML
	public void initialize() throws Exception {
		ObservableList<String> list = FXCollections.observableArrayList();
		List<Food> foods = DatabaseHelperDao.getControlDao().getFoodDao().getAllFood();
		for(Food f: foods)
			list.addAll(f.getDishName());
		listOfDishes.setItems(list);
		listOfDishes.setOnMouseClicked(event -> {
			dishNameFromList = listOfDishes.getSelectionModel().getSelectedItem();
			dishName.setText(OUTPUT_NAME + dishNameFromList);
			List<Food> food = null;
			try {
				food = DatabaseHelperDao.getControlDao().getFoodDao().getAllFood().stream()
						.filter(e-> e.getDishName().equals(dishNameFromList)).collect(Collectors.toList());
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
			if(food.isEmpty())
				txtAreaNotes.setText("");
			else
				txtAreaNotes.setText(food.get(0).getNotes());
		});
	}
	
	@FXML
	private void save() throws IOException, SQLException {
		List<Food> foods = DatabaseHelperDao.getControlDao().getFoodDao().getAllFood()
				.stream().filter(e-> e.getDishName().equals(dishNameFromList)).collect(Collectors.toList());
		if(foods.isEmpty())
			return;
		Food food = foods.stream().findFirst().get();
		food.setNotes(txtAreaNotes.getText());
		DatabaseHelperDao.getControlDao().getNotesDao().updateNote(foods.stream().findFirst().get());
	}

}
