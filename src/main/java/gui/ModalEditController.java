package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DB;
import db.DbException;
import entities.User;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.TextFieldFormatter;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalEditController implements Initializable {
	
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
	
	
	//private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private Button buttonAtualizar;
	
//	public void subscribeDataChangeListener(DataChangeListener listener) {
//		dataChangeListeners.add(listener);
//	}
	
	
	@FXML
	private void fomartNumCel() {
		TextFieldFormatter numero = new TextFieldFormatter();
		numero.setMask("(##)#####-####");
		numero.setCaracteresValidos("0123456789");
		numero.setTf(txtCelular);
		numero.formatter();
		
	}
	
		
	
	public void setUser(User entity) {
		this.entity = entity;
	}
	
		
	
	public User getFormData() {
		User obj = new User();

		
		if (txtId.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Nome não pode ser Vazio", AlertType.ERROR);
		}		
		obj.setId(Integer.parseInt(txtId.getText()));
		
		if (txtNome.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Nome não pode ser Vazio", AlertType.ERROR);
		}		
		obj.setNome(txtNome.getText());

		if (txtValor.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Valor não pode ser Vazio", AlertType.ERROR);
			
		}	
		obj.setValor(Double.valueOf(txtValor.getText()));
		
		if (txtDeposito.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Depósito não pode ser Vazio", AlertType.ERROR);
			
		}		
		obj.setDeposito(Double.valueOf(txtDeposito.getText()));
		
		if (txtCelular.getText().trim().equals("")) {
			Alerts.showAlert("Erro ao validar!", null, "Celular não pode ser Vazio", AlertType.ERROR);
		}	
		obj.setCelular(txtCelular.getText());
		

		

		return obj;
		
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null para setar os dados do Usertable na tableView");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
		txtValor.setText(String.valueOf(entity.getValor()));
		txtDeposito.setText(String.valueOf(entity.getDeposito()));
		txtCelular.setText(entity.getCelular());
		  
		
	}
	

	
	@FXML
	public void onButtonAtualizarAction(ActionEvent event) {
				
		PreparedStatement st = null;
		Connection conn = DB.getConnection();
		entity = getFormData();
		User obj = entity;
		try {
			st = conn.prepareStatement(
					"UPDATE users "
					+ "SET Nome = ?, Valor = ?, Deposito = ?, celular = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getValor());
			st.setDouble(3, obj.getDeposito());
			st.setString(4, obj.getCelular());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
			
			
			Alerts.showAlert("Sucesso!", null, "Usuário: " + obj.getNome()+"  Atualizado com sucesso!", AlertType.INFORMATION);
			
			
			
			Stage parentStage = Utils.currentStage(event);
			parentStage.close();
			
			
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
}
