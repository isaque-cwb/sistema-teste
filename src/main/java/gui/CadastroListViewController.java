package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CadastroListViewController implements Initializable {
	
	
	
	@FXML
	private Button buttonSair;
	
	@FXML
	public void onButtonSairAction() {
		loadView("/gui/MainView1.fxml");
	}
	
	private void loadView(String absolutName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			VBox newVBox =  loader.load(); //pra onde vai 
			
			Scene mainScene = Main.getMainScene();
			AnchorPane anchorPane = (AnchorPane) mainScene.getRoot();// de onde estava
			
			
			anchorPane.getChildren().clear();
			
			anchorPane.getChildren().addAll(newVBox.getChildren());	
			
		}
		catch(IOException e){
			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);
			
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
