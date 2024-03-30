package br.com.testeattus.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.testeattus.model.Endereco;
import br.com.testeattus.model.Pessoa;
import br.com.testeattus.repository.PessoaRepository;

@Service
public class PessoaService {

	// injeçao de dependencia do repositorio de pessoas
	@Autowired
	private PessoaRepository pessoaRepository;

	// injeçao de dependencia do repositorio de enderecos
	@Autowired
	private EnderecoService enderecoService;

	// busca todas as pessoas cadastradas no banco de dados
	public List<Pessoa> buscarTodos() {
		return pessoaRepository.findAll();
	}

	// atualiza uma pessoa pelo id
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloId(id);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return pessoaRepository.save(pessoaSalva);
	}

	// Salva a pessoa e o endereço
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.saveAndFlush(pessoa);

		for (int i = 0; i < pessoa.getEnderecos().size(); i++) {

			Endereco endereco = new Endereco();
			endereco.setCep(pessoa.getEnderecos().get(i).getCep());
			endereco.setCidade(pessoa.getEnderecos().get(i).getCidade());
			endereco.setEnderecoPrincipal(pessoa.getEnderecos().get(i).getEnderecoPrincipal());
			endereco.setEstado(pessoa.getEnderecos().get(i).getEstado());
			endereco.setLogradouro(pessoa.getEnderecos().get(i).getLogradouro());
			endereco.setNumero(pessoa.getEnderecos().get(i).getNumero());
			endereco.setPessoa(pessoaSalva);
			enderecoService.salvar(endereco);
		}

		return pessoaSalva;

	}

	// busca uma pessoa cadastrada pelo id do banco
	public Pessoa buscarPessoaPeloId(Long id) {
		Pessoa pessoaSalva = pessoaRepository.getOne(id);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

}
