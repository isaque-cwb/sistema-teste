package entities;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Double valor;
	private Double deposito;
	private String celular;
	
	public User() {}

	public User(Integer id, String nome, Double valor, Double deposito, String celular) {
		
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.deposito = deposito;
		this.celular = celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getDeposito() {
		return deposito;
	}

	public void setDeposito(Double deposito) {
		this.deposito = deposito;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", valor=" + valor + ", deposito=" + deposito + ", celular="
				+ celular + "]";
	}

		
	
}
