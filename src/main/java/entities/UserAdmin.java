package entities;

import java.io.Serializable;
import java.util.Objects;

public class UserAdmin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer senha;
	

	public UserAdmin() {
	}


	public UserAdmin(Integer id, String nome, Integer senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
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


	public Integer getSenha() {
		return senha;
	}


	public void setSenha(Integer senha) {
		this.senha = senha;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nome, senha);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAdmin other = (UserAdmin) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha);
	}


	@Override
	public String toString() {
		return "UserAdmin [id=" + id + ", nome=" + nome + ", senha=" + senha + "]";
	}

	

}
