package br.edu.infnet.joaoandersonapi.utils.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaDto;

@Mapper(componentModel = "spring")
public interface ModeloPropostaMapper {
    
    @Mapping(target = "id", ignore = true)
    ModeloProposta toModeloProposta(ModeloPropostaDto modeloPropostaDto);

    ModeloPropostaDto toModeloPropostaDto(ModeloProposta modeloProposta);

    List<ModeloPropostaDto> toModelosPropostaDtos(List<ModeloProposta> modelosProposta);

}
