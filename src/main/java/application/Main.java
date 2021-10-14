package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	private static Stage stage;
	private static Scene loginScene;
	private static Scene mainScene;
	private static Scene cadastroScene;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		try {
			
			Parent loginView =  FXMLLoader.load(getClass().getResource("/gui/LoginView.fxml"));
			loginScene = new Scene(loginView, 700, 400);
			
			Parent mainView = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			mainScene = new Scene(mainView, 700, 400);
			
			Parent cadastroView = FXMLLoader.load(getClass().getResource("/gui/CadastroView.fxml"));
			cadastroScene = new Scene(cadastroView, 700, 400);			
			
			stage.setScene(mainScene);
			stage.setTitle("Sistema Teste");
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void changeView(String newScreen) {
		switch(newScreen) {
		case "LoginView":
			stage.setScene(loginScene);
			break;
			
		case "MainView":
			stage.setScene(mainScene);
			break;
		case "CadastroView":
			stage.setScene(cadastroScene);
			break;
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		
		launch(args);
	}
}
