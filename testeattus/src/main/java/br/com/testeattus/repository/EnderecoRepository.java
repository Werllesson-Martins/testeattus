package br.com.testeattus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testeattus.model.Endereco;
import br.com.testeattus.model.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public List<Endereco> findByPessoa(Pessoa pessoa);
}
