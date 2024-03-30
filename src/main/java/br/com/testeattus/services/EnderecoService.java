package br.com.testeattus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.testeattus.model.Endereco;
import br.com.testeattus.model.Pessoa;
import br.com.testeattus.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaService pessoaService;

	public List<Endereco> buscarTodos() {
		return enderecoRepository.findAll();
	}

	public List<Endereco> buscarPorPessoa(Long idPessoa) {
		Pessoa pessoa = pessoaService.buscarPessoaPeloId(idPessoa);
		return enderecoRepository.findByPessoa(pessoa);
	}

	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

}
