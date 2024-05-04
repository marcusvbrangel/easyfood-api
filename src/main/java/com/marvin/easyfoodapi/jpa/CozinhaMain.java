package com.marvin.easyfoodapi.jpa;

import com.marvin.easyfoodapi.EasyFoodApiApplication;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CozinhaMain {

    public static void main(String... args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(EasyFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaManager cozinhaManager = applicationContext.getBean(CozinhaManager.class);

        // Consultar cozinhas...
        listar(cozinhaManager);

        // Adicionar cozinha...
        adicionar(cozinhaManager);

        // Buscar cozinha...
        buscar(cozinhaManager);

        // Alterar cozinha...
        alterar(cozinhaManager);

        // Excluir cozinha...
        excluir(cozinhaManager);

        // Consultar cozinhas...
        listar(cozinhaManager);

    }

    private static void listar(CozinhaManager cozinhaManager) {

        List<Cozinha> cozinhas = cozinhaManager.listar();

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Listar cozinhas ----- \n");

        for (Cozinha cozinha : cozinhas) {

            System.out.println(cozinha.getId() + " - " + cozinha.getNome());

        }

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void adicionar(CozinhaManager cozinhaManager) {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Argentina");

        Cozinha cozinhaAdicionada = cozinhaManager.salvar(cozinha);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Adicionar cozinha ----- \n");

        System.out.printf("id: %d - nome: %s \n", cozinhaAdicionada.getId(), cozinhaAdicionada.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void buscar(CozinhaManager cozinhaManager) {

        Cozinha cozinha = cozinhaManager.buscar(2L);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Buscar cozinha ----- \n");

        System.out.printf("id: %d - nome: %s \n", cozinha.getId(), cozinha.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void alterar(CozinhaManager cozinhaManager) {

        Cozinha cozinha = cozinhaManager.buscar(2L);

        cozinha.setNome(cozinha.getNome() + " Alterada");

        Cozinha cozinhaAlterada = cozinhaManager.salvar(cozinha);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Alterar cozinha ----- \n");

        System.out.printf("id: %d - nome: %s \n", cozinhaAlterada.getId(), cozinhaAlterada.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void excluir(CozinhaManager cozinhaManager) {

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cozinhaManager.excluir(cozinha);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Excluir cozinha ----- \n");

        System.out.printf("id: %d \n", cozinha.getId());

        System.out.println("\n------------------------------------------------------------\n");

    }

}
