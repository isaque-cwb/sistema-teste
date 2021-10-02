package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import db.DB;
import db.DbException;
import entities.User;
import gui.util.Alerts;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CadastroViewController implements Initializable {
	
	private User entity;
	
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtValor;
	
	@FXML
	private TextField txtDeposito;
	
	@FXML
	private TextField txtCelular;
	
	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Label labelErrorValor;

	@FXML
	private Label labelErrorDeposito;

	@FXML
	private Label labelErrorCelular;
	
	
	

	
	
	@FXML
	private Button buttonIncluir;
	
	@FXML
	private Button buttonSair;
	
	
	public void setUser(User entity) {
		this.entity = entity;
	}
	

	
	
	private User getFormData() {
		User obj = new User();

		

		//obj.setId(Utils.tryParseToInt(txtId.getText()));

		
		obj.setNome(txtNome.getText());

		
		obj.setValor(Double.valueOf(txtValor.getText()));
		

		
		obj.setDeposito(Double.valueOf(txtDeposito.getText()));
		
		
		obj.setCelular(txtCelular.getText());
		

		

		return obj;
		
	}

	
	@FXML
	public void onButtonIncluirAction(ActionEvent event) {
		
		
		PreparedStatement st = null;
		Connection conn = DB.getConnection();
		//ResultSet rs = null;
		entity = getFormData();
		User obj = entity;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO users "
					+ "(Nome, Valor, Deposito, Celular) "
					+ "VALUES "
					+ "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getValor());
			st.setDouble(3, obj.getDeposito());
			st.setString(4, obj.getCelular());
			//st.setInt(5, obj.getId());
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Usu�rio: " + obj.getNome() + " Cadastrado!!!");
			Alerts.showAlert("Cadastrado!", null, "Tudo certo! " + obj.getNome()+"  Cadastrado ou Alterado com sucesso!", AlertType.INFORMATION);
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! Nenhuma Linha Afetada!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
		}
	}
	
	@FXML
	public void onButtonSairAction() {
		loadView("/gui/MainView.fxml");
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
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
		

}
