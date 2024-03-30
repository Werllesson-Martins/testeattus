package br.com.testeattus.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeattus.model.Pessoa;
import br.com.testeattus.repository.PessoaRepository;
import br.com.testeattus.services.EnderecoService;
import br.com.testeattus.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaService.buscarTodos();
	}

	@GetMapping("/buscarPessoaPeloId/{idPessoa}")
	public Pessoa buscarPessoaPeloId(@PathVariable Long idPessoa) {
		return pessoaService.buscarPessoaPeloId(idPessoa);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa salvar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.salvar(pessoa);
		return pessoaSalva;
	}

//	@PutMapping("/{id}/ativo")
//	public void atualizarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco,
//			@RequestBody Boolean principal) {
//		pessoaService.atualizarEnderecoPrincipal(idPessoa, idEndereco, principal);
//	}
}
