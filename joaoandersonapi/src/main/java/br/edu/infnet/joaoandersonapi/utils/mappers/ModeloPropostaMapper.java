package br.edu.infnet.joaoandersonapi.utils.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaGet;
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaPost;

@Mapper(componentModel = "spring")
public interface ModeloPropostaMapper {
    
    @Mapping(target = "id", ignore = true)
    ModeloProposta toModeloProposta(ModeloPropostaPost modeloPropostaPost);

    ModeloProposta toModeloProposta(ModeloPropostaGet modeloPropostaGet);

    ModeloPropostaGet toModeloPropostaGet(ModeloProposta modeloProposta);

    ModeloPropostaGet toModeloPropostaGet(ModeloPropostaPost modeloPropostaPost, Long id);

    List<ModeloPropostaGet> toModelosPropostaGets(List<ModeloProposta> modelosProposta);

}
