package entities;
import java.io.Serializable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTabela implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	
	private final SimpleBooleanProperty selected;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleIntegerProperty valor;
    private final SimpleIntegerProperty deposito;
    private final SimpleStringProperty celular;
    

    
    public UserTabela(Integer id, String nome, Integer valor, Integer deposito, String celular) {
        this.selected = new SimpleBooleanProperty(false);
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.valor = new SimpleIntegerProperty(valor);
        this.deposito = new SimpleIntegerProperty(deposito);
        this.celular = new SimpleStringProperty(celular);
    }
    
    

	public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
    
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public int getValor() {
        return valor.get();
    }

    public SimpleIntegerProperty valorProperty() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor.set(valor);
    }
    
    public int getDeposito() {
        return deposito.get();
    }

    public SimpleIntegerProperty depositoProperty() {
        return deposito;
    }

    public void setDeposito(int deposito) {
        this.deposito.set(deposito);
    }


    public String getCelular() {
        return celular.get();
    }

    public SimpleStringProperty celularProperty() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular.set(celular);
    }
}