package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import db.DbException;
import entities.UserAdmin;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginViewController implements Initializable {

	private UserAdmin entity;

	@FXML
	private TextField txtUsuario;

	@FXML
	private TextField txtSenha;

	@FXML
	private Label txtUsuarioErro;

	@FXML
	private Label txtSenhaErro;

	@FXML
	private Button buttonValidar;

	public void setUserAdmin(UserAdmin entity) {
		this.entity = entity;
	}

	private UserAdmin getFormData() {
		UserAdmin obj = new UserAdmin();

		try {

			if (txtUsuario.getText() == null || txtUsuario.getText().trim().equals("")) {
				Alerts.showAlert("Erro ao validar!", null, "Usuário não pode ser Vazio", AlertType.ERROR);
			}
			obj.setNome(txtUsuario.getText());

			if (txtSenha.getText() == null || txtSenha.getText().trim().equals("")) {
				Alerts.showAlert("Erro ao validar!", null, "Senha não pode ser Vazio", AlertType.ERROR);
				obj.setSenha(0);
			}
			obj.setSenha(Integer.parseInt(txtSenha.getText()));
			
		} catch (NumberFormatException e) {
			throw new DbException(e.getMessage());
		}

		return obj;
	}

	@FXML
	public void onButtonValidarAction() {
		
		String adm = "Admin";
		Integer senha = 12345;
				
		entity = getFormData();
		UserAdmin obj = entity;
		
		if(obj.getNome().equals(adm) && obj.getSenha().equals(senha)) {
			
			Main.changeView("MainView");
			//loadView("/gui/MainView.fxml");
		}else {
			Alerts.showAlert("ERRO", null, "Usário ou senha inválida", AlertType.ERROR);
		}		
	
	}
	
//	private void loadView(String absolutName) {
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
//			AnchorPane newAnchorPane =  loader.load(); 
//			
//			Scene mainScene = Main.getMainScene();
//			AnchorPane anchorPane = (AnchorPane) mainScene.getRoot();
//			
//			
//			anchorPane.getChildren().clear();
//			
//			anchorPane.getChildren().addAll(newAnchorPane.getChildren());	
//			
//		}
//		catch(IOException e){
//			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);
//			
//		}
//	}
	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

}
