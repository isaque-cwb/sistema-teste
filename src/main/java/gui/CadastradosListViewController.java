package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CadastradosListViewController implements Initializable {
	
//	private UserService service;

	@FXML
	private TableView<User> tableViewUser;

	@FXML
	private TableColumn<User, Integer> tableColumnId;

	@FXML
	private TableColumn<User, String> tableColumnNome;
	
	@FXML
	private TableColumn<User, Integer> tableColumnValor;
	
	@FXML
	private TableColumn<User, String> tableColumnDeposito;
	
	@FXML
	private TableColumn<User, Date> tableColumnCelular;
	
	
	@FXML
	private TableColumn<User, User> tableColumnEDIT;

	@FXML
	private TableColumn<User, User> tableColumnREMOVE;
	
	
	@FXML
	private Button buttonSair;
	
	
//	public void setUserService(UserService service) {
//		this.service = service;
//	}
	
	@FXML
	public void onButtonSairAction() {
//		loadView(null, "/gui/MainView.fxml");
	} 
	
	private void loadView(User obj, String absolutName) {
		
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
//			VBox newVBox =  loader.load(); //pra onde vai 
//			
//			Scene mainScene = Main.getMainScene();
//			AnchorPane anchorPane = (AnchorPane) mainScene.getRoot();// de onde estava
//			
//			CadastroViewController controller = loader.getController();
//			controller.setUser(obj);
//			controller.setUsers(new UserService());
//			//controller.loadAssociatedObjects();
//			//controller.subscribeDataChangeListener(this);
//			controller.updateFormData();
//			
//			
//			anchorPane.getChildren().clear();
//			
//			anchorPane.getChildren().addAll(newVBox.getChildren());	
			
//		}
//		catch(IOException e){
//			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);
//			
//		}
//	}
//	
//	private void initializeNodes() {
//		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
//		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
//		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
//		tableColumnDeposito.setCellValueFactory(new PropertyValueFactory<>("deposito"));
//		tableColumnCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
//		
	}



	@Override
	public void initialize(URL uri, ResourceBundle rb) {
//		initializeNodes();
		
	}

}
