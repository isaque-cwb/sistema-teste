package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import db.DB;
import db.DbException;
import entities.User;
import gui.util.Alerts;
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
			
			System.out.println("Usuário: " + obj.getNome() + " Cadastrado!!!");
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
	
	public void onButtonSairAction() {
		System.out.println("Apertou botão sair então saindo!!!");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
		

}
