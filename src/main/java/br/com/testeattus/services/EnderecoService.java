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

	public Endereco atualizar(Long id, Endereco endereco) {
		Endereco enderecoSalva = buscarPessoaPeloId(id);

		BeanUtils.copyProperties(endereco, enderecoSalva, "id");
		return enderecoRepository.save(enderecoSalva);
	}

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

	private Endereco buscarPessoaPeloId(Long id) {
		// TODO Auto-generated method stub
		return enderecoRepository.getOne(id);
	}

}
