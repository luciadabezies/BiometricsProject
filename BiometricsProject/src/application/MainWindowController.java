package application;

import database.RegisterUserController;
import faceRecognition.FaceRecognition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindowController {

	@FXML
	private Button registerUserBtn;
	@FXML
	private Button detectUserBtn;

	@FXML
	void registerUser(ActionEvent event) {
		try {
			FXMLLoader registerUserLoader = new FXMLLoader(Main.class.getResource("../database/RegisterUser.fxml"));
			Pane registerUserPane = (Pane) registerUserLoader.load();
			registerUserPane.setStyle("-fx-background-color: whitesmoke;");
			Scene registerUserScene = new Scene(registerUserPane, 800, 600);
			registerUserScene.getStylesheets()
					.add(getClass().getResource("../application/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Register user");
			stage.setScene(registerUserScene);
			stage.show();

			//register User
			new RegisterUserController();
			

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@FXML
	void detectUser(ActionEvent event) {
		try {
			FXMLLoader faceDetectionLoader = new FXMLLoader(
					Main.class.getResource("../faceRecognition/FaceDetection.fxml"));
			Pane faceDetectionPane = (Pane) faceDetectionLoader.load();
			faceDetectionPane.setStyle("-fx-background-color: whitesmoke;");
			Scene faceDetectionScene = new Scene(faceDetectionPane, 800, 600);
			faceDetectionScene.getStylesheets()
					.add(getClass().getResource("../application/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setTitle("Detect user");
			stage.setScene(faceDetectionScene);
			stage.show();

			// face recognition
			new FaceRecognition().recongize();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
