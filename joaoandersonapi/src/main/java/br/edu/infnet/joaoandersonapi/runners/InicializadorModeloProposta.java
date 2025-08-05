package br.edu.infnet.joaoandersonapi.runners;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.joaoandersonapi.model.dtos.ContatoDto;
import br.edu.infnet.joaoandersonapi.model.dtos.DadosBasicosDto;
import br.edu.infnet.joaoandersonapi.model.dtos.EnderecoDto;
import br.edu.infnet.joaoandersonapi.model.dtos.MaterialDto;
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaPost;
import br.edu.infnet.joaoandersonapi.model.dtos.RequisitanteDto;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;

@Component
public class InicializadorModeloProposta implements ApplicationRunner {

    private final ModeloPropostaUseCases modeloPropostaUseCases;

    public InicializadorModeloProposta(ModeloPropostaUseCases modeloPropostaUseCases) {
        this.modeloPropostaUseCases = modeloPropostaUseCases;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        var arquivo = new FileReader("mock_ModeloProposta.txt");
        var ler = new BufferedReader(arquivo);
        String linha;
        while ((linha = ler.readLine()) != null) {
            var atributos = linha.split(";");
            
            var enderecoDto = new EnderecoDto(atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], atributos[7]);

            var dadosBasicosDto = new DadosBasicosDto(atributos[0], atributos[1], enderecoDto);

            var contatoDto =  new ContatoDto(atributos[8], atributos[9], atributos[10], atributos[11]);
            
            var requisitanteDto = new RequisitanteDto(dadosBasicosDto, contatoDto, atributos[12], atributos[13]);
            
            var modeloPropostaDto = new ModeloPropostaPost(requisitanteDto, new ArrayList<MaterialDto>(), "");

            modeloPropostaUseCases.cadastrar(modeloPropostaDto);
            
        }

        ler.close();

    }

}
