package br.com.testeattus.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeattus.assembler.ResumoGenericAssembler;
import br.com.testeattus.model.Endereco;
import br.com.testeattus.model.Pessoa;
import br.com.testeattus.model.projections.ResumoEndereco;
import br.com.testeattus.model.projections.ResumoPessoa;
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
	private ResumoGenericAssembler resumoGenericAssembler;

	@GetMapping("/listar")
	public List<ResumoPessoa> listar() {
		return resumoGenericAssembler.toCollectionModel(pessoaService.buscarTodos(), ResumoPessoa.class);
	}

	@GetMapping("/{idPessoa}")
	public ResumoPessoa buscarPessoaPeloId(@PathVariable Long idPessoa) {
		return resumoGenericAssembler.toModel(pessoaService.buscarPessoaPeloId(idPessoa), ResumoPessoa.class);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResumoPessoa salvar(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.salvar(pessoa);
		return resumoGenericAssembler.toModel(pessoaSalva, ResumoPessoa.class);
	}

	@PutMapping("/atualizarPessoa/{id}")
	public ResumoPessoa atualizar(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(id, pessoa);

		if (pessoaSalva != null) {
			return resumoGenericAssembler.toModel(pessoaSalva, ResumoPessoa.class);
		}
		return null;
	}

	@PutMapping("/atualizarEndereco/{id}")
	public ResumoEndereco atualizarEndereco(@PathVariable Long id, @RequestBody @Valid Endereco endereco) {
		Endereco enderecoSalvo = enderecoService.atualizar(id, endereco);

		if (enderecoSalvo != null) {
			return resumoGenericAssembler.toModel(enderecoSalvo, ResumoEndereco.class);
		}
		return null;
	}

	@PutMapping("/indicarEnderecoPrincipal/{id}")
	public ResumoEndereco indicarEnderecoPrincipal(@PathVariable Long id) {
		Endereco enderecoSalvo = enderecoService.indicarEnderecoPrincipal(id);

		if (enderecoSalvo != null) {
			return resumoGenericAssembler.toModel(enderecoSalvo, ResumoEndereco.class);
		}
		return null;
	}

}
