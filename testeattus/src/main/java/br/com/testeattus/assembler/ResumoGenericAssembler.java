package br.com.testeattus.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumoGenericAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public <T, D> D toModel(T entity, Class<D> dtoClass) {
		return modelMapper.map(entity, dtoClass);
	}

	public <T, D> List<D> toCollectionModel(List<T> entities, Class<D> dtoClass) {
		return entities.stream().map(analiseFinanceira -> toModel(analiseFinanceira, dtoClass))
				.collect(Collectors.toList());
	}
}
