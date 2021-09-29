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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemCadastro;

	@FXML
	private MenuItem menuItemCadastrados;
	
	@FXML
	private MenuItem menuFim;
	
	
	
	@FXML
	public void onMenuItemCadastroAction() {
		loadViewCadastro("/gui/CadastroView.fxml");
	}
	
	@FXML
	public void onMenuItemCadastradosAction() {
		loadViewCadastrados("/gui/CadastroListView.fxml");
	}
	
	@FXML
	public void onMenuFimAction() {
		System.out.println("onMenuFimAction");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
				
	}
	
	private void loadViewCadastro(String absolutName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			//ScrollPane newScrollPane = loader.load();
			//VBox newVBox = loader.load();
			//SplitPane newSplitPane = loader.load();
			AnchorPane newAnchorPane =  loader.load();
			
			Scene mainScene = Main.getMainScene();
			AnchorPane anchorPane = (AnchorPane)(mainScene.getRoot());
						
			anchorPane.getChildren().clear();
			anchorPane.getChildren().addAll(newAnchorPane.getChildren());	
			
		}
		catch(IOException e){
			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);
			
		}
	}

	
	private void loadViewCadastrados(String absolutName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			//ScrollPane newScrollPane = loader.load();
			//VBox newVBox = loader.load();
			//SplitPane newSplitPane = loader.load();
			AnchorPane newAnchorPane =  loader.load();
			
			
			Scene mainScene = Main.getMainScene();
			AnchorPane anchorPane = (AnchorPane)(mainScene.getRoot());
			
			anchorPane.getChildren().clear();
			anchorPane.getChildren().addAll(newAnchorPane.getChildren());	
			
		}
		catch(IOException e){
			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);
			
		}
	}

}
