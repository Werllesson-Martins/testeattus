package br.com.testeattus.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.testeattus.model.Endereco;
import br.com.testeattus.model.Pessoa;
import br.com.testeattus.repository.EnderecoRepository;

@Service
public class EnderecoService {

	// Injeção de dependencia do repositorio endereco
	@Autowired
	private EnderecoRepository enderecoRepository;

	// Injeção de dependencia do repositorio pessoa
	@Autowired
	private PessoaService pessoaService;

	// Busca todos os endereços cadastrados no banco de dados
	public List<Endereco> buscarTodos() {
		return enderecoRepository.findAll();
	}

	// Busca o endereço pelo id da pessoa cadastrada
	public List<Endereco> buscarPorPessoa(Long idPessoa) {
		Pessoa pessoa = pessoaService.buscarPessoaPeloId(idPessoa);
		return enderecoRepository.findByPessoa(pessoa);
	}

	// Salva um endereço
	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	// Atualiza o endereço pelo id e objeto endereco
	public Endereco atualizar(Long id, Endereco endereco) {
		Endereco enderecoSalva = buscarPessoaPeloId(id);

		BeanUtils.copyProperties(endereco, enderecoSalva, "id");
		return enderecoRepository.save(enderecoSalva);
	}

	// Atualiza o endereço indicando o mesmo como enderecoPrincipal
	public Endereco indicarEnderecoPrincipal(Long id) {
		Endereco enderecoSalva = buscarPessoaPeloId(id);

		buscarTodos().forEach(e -> {
			if (e.getEnderecoPrincipal() == true && e.getId() != id) {
				e.setEnderecoPrincipal(false);
			}
		});

		enderecoSalva.setEnderecoPrincipal(true);
		return enderecoRepository.save(enderecoSalva);
	}

	// Busca o endereco pelo id do endereco cadastrado no banco de dados
	private Endereco buscarPessoaPeloId(Long id) {
		// TODO Auto-generated method stub
		return enderecoRepository.getOne(id);
	}

}
