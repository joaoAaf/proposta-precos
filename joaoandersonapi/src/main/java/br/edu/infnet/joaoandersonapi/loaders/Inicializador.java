package br.edu.infnet.joaoandersonapi.loaders;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Inicializador implements ApplicationRunner {

    private final InicializadorModeloProposta inicializadorModeloProposta;
    private final InicializadorMaterial inicializadorMaterial;

    public Inicializador(InicializadorModeloProposta inicializadorModeloProposta, InicializadorMaterial inicializadorMaterial) {
        this.inicializadorModeloProposta = inicializadorModeloProposta;
        this.inicializadorMaterial = inicializadorMaterial;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        inicializadorModeloProposta.inicializar();
        inicializadorMaterial.inicializar();
    }

}
