package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import db.DB;
import db.DbException;
import entities.User;
import gui.util.Alerts;
import gui.util.TextFieldFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	

	
	
	public User getFormData() {
		User obj = new User();

		
		if (txtNome.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Nome n�o pode ser Vazio", AlertType.ERROR);
		}		
		obj.setNome(txtNome.getText());

		if (txtValor.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Valor n�o pode ser Vazio", AlertType.ERROR);
			
		}	
		obj.setValor(Double.valueOf(txtValor.getText()));
		
		if (txtDeposito.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Dep�sito n�o pode ser Vazio", AlertType.ERROR);
			
		}		
		obj.setDeposito(Double.valueOf(txtDeposito.getText()));
		
		if (txtCelular.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Celular n�o pode ser Vazio", AlertType.ERROR);
		}	
		obj.setCelular(txtCelular.getText());
		

		return obj;
		
	}

	
	@FXML
	public void onButtonIncluirAction(ActionEvent event) {
		
		
		PreparedStatement st = null;
		Connection conn = DB.getConnection();
		
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
			
			
			st.executeUpdate();
			
			
			Alerts.showAlert("Cadastrado!", null, "Tudo certo! " + obj.getNome()+"  Cadastrado(a) com sucesso!", AlertType.INFORMATION);

			
			
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
		Main.changeView("MainView");
	}
	
	

	

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	
	@FXML
	private void fomartNumCel() {
		TextFieldFormatter numero = new TextFieldFormatter();
		numero.setMask("(##)#####-####");
		numero.setCaracteresValidos("0123456789");
		numero.setTf(txtCelular);
		numero.formatter();
		
	}
	
	
}
