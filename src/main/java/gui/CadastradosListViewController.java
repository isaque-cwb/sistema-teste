package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import db.DB;
import db.DbException;
import entities.User;
import entities.UserTabela;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CadastradosListViewController implements Initializable {

	@FXML
	private TableView<UserTabela> tabela;

	@FXML
	private TableColumn<UserTabela, Integer> tableColumnId;

	@FXML
	private TableColumn<UserTabela, String> tableColumnNome;

	@FXML
	private TableColumn<UserTabela, Integer> tableColumnValor;

	@FXML
	private TableColumn<UserTabela, Integer> tableColumnDeposito;

	@FXML
	private TableColumn<UserTabela, String> tableColumnCelular;

	@FXML
	private TableColumn<UserTabela, User> tableColumnEDIT;

	@FXML
	private TableColumn<UserTabela, User> tableColumnREMOVE;

	// private ObservableList<User> obsList;

	// ObservableList<UserTabela> list = FXCollections.observableArrayList();

	List<UserTabela> list = new ArrayList<>();

	@FXML
	private Button buttonSair;

	@FXML
	public void onButtonSairAction() {
		loadView(null, "/gui/MainView.fxml");
	}

	private void loadView(User obj, String absolutName) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutName));
			VBox newVBox = loader.load(); // pra onde vai

			Scene mainScene = Main.getMainScene();
			AnchorPane anchorPane = (AnchorPane) mainScene.getRoot();// de onde estava

			anchorPane.getChildren().clear();

			anchorPane.getChildren().addAll(newVBox.getChildren());
		} catch (IOException e) {
			Alerts.showAlert("IOException", "Erro loading View", e.getMessage(), AlertType.ERROR);

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tableColumnDeposito.setCellValueFactory(new PropertyValueFactory<>("deposito"));
		tableColumnCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));

		tabela.setItems(listaDeUsuarios());
		System.out.println(list);

	}

	private ObservableList<UserTabela> listaDeUsuarios() {
		return FXCollections.observableArrayList(updateTableView());
	}

	@SuppressWarnings("null")
	public List<UserTabela> updateTableView() {

		PreparedStatement st = null;
		Connection conn = DB.getConnection();
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM users;");
			rs = st.executeQuery();
			while (rs.next()) {

				UserTabela obj = new UserTabela(rs.getInt("Id"), rs.getString("Nome"), rs.getInt("Valor"),
						rs.getInt("Deposito"), rs.getString("Celular"));

				list.add(obj);

			}
			return list;
			

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

		finally {
			// DB.closeStatement(st);
		}

	}

}
