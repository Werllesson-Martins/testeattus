package br.com.testeattus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testeattus.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public List<Pessoa> findAllByOrderByNomeAsc();
}
