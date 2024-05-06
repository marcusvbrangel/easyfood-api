package com.marvin.easyfoodapi.jpa;

import com.marvin.easyfoodapi.EasyFoodApiApplication;
import com.marvin.easyfoodapi.domain.model.Cozinha;
import com.marvin.easyfoodapi.domain.model.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CozinhaMain {

    public static void main(String... args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(EasyFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        // CozinhaManager cozinhaRepository = applicationContext.getBean(CozinhaManager.class);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        // Consultar cozinhas...
        listar(cozinhaRepository);

        // Adicionar cozinha...
        adicionar(cozinhaRepository);

        // Buscar cozinha...
        buscar(cozinhaRepository);

        // Alterar cozinha...
        alterar(cozinhaRepository);

        // Excluir cozinha...
        excluir(cozinhaRepository);

        // Consultar cozinhas...
        listar(cozinhaRepository);

    }

    private static void listar(CozinhaRepository cozinhaRepository) {

        List<Cozinha> cozinhas = cozinhaRepository.listar();

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Listar cozinhas ----- \n");

        for (Cozinha cozinha : cozinhas) {

            System.out.println(cozinha.getId() + " - " + cozinha.getNome());

        }

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void adicionar(CozinhaRepository cozinhaRepository) {

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Argentina");

        Cozinha cozinhaAdicionada = cozinhaRepository.salvar(cozinha);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Adicionar cozinha ----- \n");

        System.out.printf("id: %d - nome: %s \n", cozinhaAdicionada.getId(), cozinhaAdicionada.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void buscar(CozinhaRepository cozinhaRepository) {

        Cozinha cozinha = cozinhaRepository.buscar(2L);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Buscar cozinha ----- \n");

        System.out.printf("id: %d - nome: %s \n", cozinha.getId(), cozinha.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void alterar(CozinhaRepository cozinhaRepository) {

        Cozinha cozinha = cozinhaRepository.buscar(2L);

        cozinha.setNome(cozinha.getNome() + " Alterada");

        Cozinha cozinhaAlterada = cozinhaRepository.salvar(cozinha);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Alterar cozinha ----- \n");

        System.out.printf("id: %d - nome: %s \n", cozinhaAlterada.getId(), cozinhaAlterada.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void excluir(CozinhaRepository cozinhaRepository) {

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cozinhaRepository.excluir(cozinha);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Excluir cozinha ----- \n");

        System.out.printf("id: %d \n", cozinha.getId());

        System.out.println("\n------------------------------------------------------------\n");

    }

}
