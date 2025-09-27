package br.com.apisemaperreio.proposta_precos.loaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.apisemaperreio.proposta_precos.model.dto.endereco.EnderecoRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.fornecedor.FornecedorRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.instituicao.InstituicaoRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.material.MaterialPrecoRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.material.MaterialRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCadastroRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.requisitante.RequisitanteRequest;
import br.com.apisemaperreio.proposta_precos.model.use_cases.GerenciadorPropostaUseCases;

@Component
public class Inicializador implements ApplicationRunner {

    private final GerenciadorPropostaUseCases gerenciadorPropostaUseCases;

    public Inicializador(GerenciadorPropostaUseCases gerenciadorPropostaUseCases) {
        this.gerenciadorPropostaUseCases = gerenciadorPropostaUseCases;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        var arquivo = new FileReader("./src/main/resources/mocks/mock_PropostaModelo.txt");
        var ler = new BufferedReader(arquivo);
        String linha;
        
        linha = ler.readLine();

        var atributos = linha.split(";");
            
        var endereco = new EnderecoRequest(atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], atributos[7]);
        var dadosBasicos = new InstituicaoRequest(atributos[0], atributos[1], endereco);   
        var requisitante = new RequisitanteRequest(dadosBasicos, atributos[8], atributos[9], atributos[10], atributos[11]);
           
        arquivo = new FileReader("./src/main/resources/mocks/mock_MaterialModelo.txt");
        ler = new BufferedReader(arquivo);

        List<MaterialRequest> materiais = new ArrayList<>();

        while ((linha = ler.readLine()) != null) {
            atributos = linha.split(";");

            var material = new MaterialRequest(atributos[0], atributos[1], new BigDecimal(atributos[2]));

            materiais.add(material);
        }

        var modeloProposta = new PropostaModeloRequest(requisitante, materiais, null);

        var token = gerenciadorPropostaUseCases.gerarToken(modeloProposta);

        arquivo = new FileReader("./src/main/resources/mocks/mock_Proposta.txt");
        ler = new BufferedReader(arquivo);

        linha = ler.readLine();

        atributos = linha.split(";");
            
        endereco = new EnderecoRequest(atributos[2], atributos[3], atributos[4], atributos[5], atributos[6], atributos[7]);
        dadosBasicos = new InstituicaoRequest(atributos[0], atributos[1], endereco);   
        var fornecedor = new FornecedorRequest(dadosBasicos, atributos[8], atributos[9], atributos[10]);

        arquivo = new FileReader("./src/main/resources/mocks/mock_Material.txt");
        ler = new BufferedReader(arquivo);

        List<MaterialPrecoRequest> precosMateriais = new ArrayList<>();

        while ((linha = ler.readLine()) != null) {
            atributos = linha.split(";");

            var preco = new MaterialPrecoRequest(Long.parseLong(atributos[0]), new BigDecimal(atributos[1]));

            precosMateriais.add(preco);
        }

        var proposta = new PropostaCadastroRequest(fornecedor, precosMateriais, BigDecimal.ZERO, null);
        
        gerenciadorPropostaUseCases.cadastrarProposta(token, proposta);

        ler.close();

    }

}
