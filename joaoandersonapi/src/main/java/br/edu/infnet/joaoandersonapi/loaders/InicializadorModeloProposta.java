package br.edu.infnet.joaoandersonapi.loaders;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.joaoandersonapi.model.domain.Endereco;
import br.edu.infnet.joaoandersonapi.model.domain.Instituicao;
import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.domain.Requisitante;
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
            
            var endereco = new Endereco(atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], atributos[7]);

            var dadosBasicos = new Instituicao(atributos[0], atributos[1], endereco);
            
            var requisitante = new Requisitante(dadosBasicos, atributos[8], atributos[9], atributos[10], atributos[11]);
            
            var modeloProposta = new ModeloProposta(requisitante);

            modeloPropostaUseCases.cadastrar(modeloProposta);
            
        }

        ler.close();

    }

}
