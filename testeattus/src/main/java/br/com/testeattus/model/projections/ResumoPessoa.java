package br.com.testeattus.model.projections;

//Classe que irá ser usada para retornar os dados da pessoa cadastrada, conversao de pessoa para ResumoPessoa
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumoPessoa {

	private Long id;

	private String nome;

	private LocalDate dataNascimento;

	private List<ResumoEndereco> enderecos = new ArrayList<>();

	public ResumoPessoa() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<ResumoEndereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<ResumoEndereco> enderecos) {
		this.enderecos = enderecos;
	}

}
