package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DB;
import db.DbException;
import entities.User;
import entities.UserTabela;
import gui.util.Alerts;
import gui.util.TextFieldFormatter;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

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

	List<UserTabela> list = new ArrayList<>();

	@FXML
	private Button buttonAtualizar;

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
					"INSERT INTO users " + "(Nome, Valor, Deposito, Celular) " + "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getValor());
			st.setDouble(3, obj.getDeposito());
			st.setString(4, obj.getCelular());

			st.executeUpdate();

			Alerts.showAlert("Cadastrado!", null, "Tudo certo! " + obj.getNome() + "  Cadastrado(a) com sucesso!",
					AlertType.INFORMATION);

			updateFormData();
			updateTableView().clear();
			tabela.setItems(listaDeUsuarios());

		} catch (SQLException e) {
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

	@FXML
	private void fomartNumCel() {
		TextFieldFormatter numero = new TextFieldFormatter();
		numero.setMask("(##)#####-####");
		numero.setCaracteresValidos("0123456789");
		numero.setTf(txtCelular);
		numero.formatter();

	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		txtNome.setText("");
		txtValor.setText("");
		txtDeposito.setText("");
		txtCelular.setText("");

	}

	public TableView<UserTabela> getTabela() {
		return tabela;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tableColumnDeposito.setCellValueFactory(new PropertyValueFactory<>("deposito"));
		tableColumnCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));

		tabela.setItems(listaDeUsuarios());

		addButtonEdit();
		addButtonRemove();

	}

	public ObservableList<UserTabela> listaDeUsuarios() {
		return FXCollections.observableArrayList(updateTableView());
	}

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
			DB.closeStatement(st);
		}

	}

	private void createDialogForm(String absoluteName, Stage parenteStage, User obj) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Parent modalEdit = loader.load();
			Scene modalScene = new Scene(modalEdit);

			ModalEditController controller = loader.getController();
			controller.setUser(obj);

			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com Dados do User(a)");
			dialogStage.setScene(modalScene);
			dialogStage.setResizable(false);
			dialogStage.initOwner(parenteStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

			updateTableView().clear();
			tabela.setItems(listaDeUsuarios());

		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	private void addButtonEdit() {
		@SuppressWarnings("rawtypes")
		TableColumn<UserTabela, Void> colBtn = new TableColumn();

		Callback<TableColumn<UserTabela, Void>, TableCell<UserTabela, Void>> cellFactory = new Callback<TableColumn<UserTabela, Void>, TableCell<UserTabela, Void>>() {
			@Override
			public TableCell<UserTabela, Void> call(final TableColumn<UserTabela, Void> param) {
				final TableCell<UserTabela, Void> cell = new TableCell<UserTabela, Void>() {

					private final Button btn = new Button("Editar");

					{
						btn.setOnAction((ActionEvent event) -> {
							UserTabela data = getTableView().getItems().get(getIndex());
							Stage parentStage = Utils.currentStage(event);
							User obj = new User();
							obj.setId(data.getId());
							obj.setNome(data.getNome());
							obj.setDeposito((double) data.getDeposito());
							obj.setValor((double) data.getValor());
							obj.setCelular(data.getCelular());

							createDialogForm("/gui/ModalEdit.fxml", parentStage, obj);

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		tabela.getColumns().add(colBtn);

	}

	@SuppressWarnings("unchecked")
	private void addButtonRemove() {
		@SuppressWarnings("rawtypes")
		TableColumn<UserTabela, Void> colBtn = new TableColumn();

		Callback<TableColumn<UserTabela, Void>, TableCell<UserTabela, Void>> cellFactory = new Callback<TableColumn<UserTabela, Void>, TableCell<UserTabela, Void>>() {
			@Override
			public TableCell<UserTabela, Void> call(final TableColumn<UserTabela, Void> param) {
				final TableCell<UserTabela, Void> cell = new TableCell<UserTabela, Void>() {

					private final Button btn = new Button("Remover");

					{
						btn.setOnAction((ActionEvent event) -> {
							UserTabela data = getTableView().getItems().get(getIndex());

							User obj = new User();
							obj.setId(data.getId());

							Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
							alert.setTitle("Aviso!");
							alert.setHeaderText(null);
							alert.setResizable(false);
							alert.setContentText("Tem certeza que deseja excluir?");

							Optional<ButtonType> result = alert.showAndWait();
							ButtonType button = result.orElse(ButtonType.CANCEL);

							if (button == ButtonType.OK) {
								removeItem(data);

							} else {
								alert.close();
							}

						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		tabela.getColumns().add(colBtn);

	}

	public void removeItem(UserTabela data) {
		PreparedStatement st = null;
		Connection conn = DB.getConnection();

		try {

			st = conn.prepareStatement("DELETE FROM users WHERE Id = ? ");

			st.setInt(1, data.getId());

			st.executeUpdate();

			Alerts.showAlert("Sucesso!", null, "Usu�rio Removido com sucesso!", AlertType.INFORMATION);
			updateTableView().clear();
			tabela.setItems(listaDeUsuarios());

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

}
