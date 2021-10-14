package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DB;
import db.DbException;
import entities.UserTabela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

	
	
	private CadastradosListViewController clvc;
	
	List<UserTabela> list = new ArrayList<>();
	
	@FXML
	private MenuItem menuItemCadastro;

	@FXML
	private MenuItem menuItemCadastrados;
	
	@FXML
	private MenuItem menuFim;
	
	@FXML 
	private Button btnFechar;
	
	
	
	@FXML
	public void onMenuItemCadastroAction(ActionEvent event) {
		Main.changeView("CadastroView");
	}
	
	@FXML
	public void onMenuItemCadastradosAction() {
		Main.changeView("CadastradosListView");
		
				
	}
	
	
	@FXML
	private void fechar(){
	    Stage stage = (Stage) btnFechar.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	public void onMenuFimAction(ActionEvent event) {
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Fechando o programa");
		alert.setHeaderText(null);
		alert.setResizable(false);
		alert.setContentText("Saindo... Até breve!");
		Optional<ButtonType> result = alert.showAndWait();
		ButtonType button = result.orElse(ButtonType.CANCEL);


		if (button == ButtonType.OK) {
			fechar();
		}
		
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
				
	}
	
	
	
}
