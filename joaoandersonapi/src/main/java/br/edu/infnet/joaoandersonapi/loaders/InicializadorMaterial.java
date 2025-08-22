package br.edu.infnet.joaoandersonapi.loaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.use_cases.MaterialUseCases;

@Component
public class InicializadorMaterial {

    private final MaterialUseCases materialUseCases;

    public InicializadorMaterial(MaterialUseCases materialUseCases) {
        this.materialUseCases = materialUseCases;
    }

    public void inicializar() throws Exception {

        var arquivo = new FileReader("mock_Material.txt");
        var ler = new BufferedReader(arquivo);
        String linha;
        while ((linha = ler.readLine()) != null) {
            var atributos = linha.split(";");

            var material = new Material(atributos[0], atributos[1], new BigDecimal(atributos[2]));

            materialUseCases.cadastrar(material, 3L);

        }

        ler.close();

    }

}
