package application;
	
import org.opencv.core.Core;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

import database.Driver;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try
		{
			FXMLLoader mainWindowLoader = new FXMLLoader(Main.class.getResource("../application/MainWindow.fxml"));
			Pane mainWindowPane = (Pane) mainWindowLoader.load();
			mainWindowPane.setStyle("-fx-background-color: whitesmoke;");
			Scene mainWindowScene = new Scene(mainWindowPane, 800, 600);
			mainWindowScene.getStylesheets().add(getClass().getResource("../application/application.css").toExternalForm());
	        primaryStage.setTitle("Main window");
	        primaryStage.setScene(mainWindowScene);
	        primaryStage.show();
			
			
			
			/*FaceDetectionController faceDetectionController = faceDetectionLoader.getController();
			faceDetectionController.initialize();
			primaryStage.setOnCloseRequest((new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we)
				{
					faceDetectionController.setClosed();
				}
			}));*/
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//initialize database
		new Driver().initialize();
		
		// load the native OpenCV library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		launch(args);
	}
}
