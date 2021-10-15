package controller;

import javafx.scene.layout.VBox;

public class Controllers {

	private static NotesController notesController;
	private static ReportsController reportsController;

	private Controllers() {}

	public static void getNotes(VBox box) {
		notesController = new NotesController();
		config(box, notesController);
	}

	public static void getReports(VBox box) {
		reportsController = new ReportsController();
		config(box, reportsController);
	}
	
	public static void config(VBox box, VBox content) {
		box.getChildren().clear();
		box.getChildren().add(content);
	}


}
