package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	public Parent root;

    @Override
    public void start(Stage primaryStage) throws IOException {
    	root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/home.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setScene(scene);
    	primaryStage.setMaximized(true);
    	primaryStage.setTitle("Food Menu");
    	//primaryStage.getIcons().add(new Image("/images/icon.png"));
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}