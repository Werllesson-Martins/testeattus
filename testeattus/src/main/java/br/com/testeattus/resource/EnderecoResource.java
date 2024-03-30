package br.com.testeattus.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testeattus.assembler.ResumoGenericAssembler;
import br.com.testeattus.model.Endereco;
import br.com.testeattus.model.projections.ResumoEndereco;
import br.com.testeattus.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private ResumoGenericAssembler resumoGenericAssembler;

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

	@GetMapping("/listar")
	public List<ResumoEndereco> listar() {
		return resumoGenericAssembler.toCollectionModel(enderecoService.buscarTodos(), ResumoEndereco.class);
	}

	@GetMapping("/buscarPorPessoa/{idPessoa}")
	public ResumoEndereco buscarPorPessoa(@PathVariable Long idPessoa) {
		return resumoGenericAssembler.toModel(enderecoService.buscarPorPessoa(idPessoa), ResumoEndereco.class);
	}
}
