package com.marvin.easyfoodapi.jpa;

import com.marvin.easyfoodapi.EasyFoodApiApplication;
import com.marvin.easyfoodapi.domain.model.Restaurante;
import com.marvin.easyfoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class RestauranteMain {

    public static void main(String... args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(EasyFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //RestauranteManager restauranteRepository = applicationContext.getBean(RestauranteManager.class);

        RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

        // Consultar restaurantes...
        listar(restauranteRepository);

        // Adicionar restaurante...
        adicionar(restauranteRepository);

        // Buscar restaurante...
        buscar(restauranteRepository);

        // Alterar restaurante...
        alterar(restauranteRepository);

        // Excluir restaurante...
        excluir(restauranteRepository);

        // Consultar restaurantes...
        listar(restauranteRepository);

    }

    private static void listar(RestauranteRepository restauranteRepository) {

        List<Restaurante> restaurantes = restauranteRepository.listar();

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Listar restaurantes ----- \n");

        for (Restaurante restaurante : restaurantes) {

            System.out.println(restaurante.getId() + " - " + restaurante.getNome() + " - "
                + restaurante.getTaxaFrete());

        }

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void adicionar(RestauranteRepository restauranteRepository) {

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Fumaça");
        restaurante.setTaxaFrete(BigDecimal.valueOf(3.17));

        Restaurante restauranteAdicionado = restauranteRepository.salvar(restaurante);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Adicionar restaurante ----- \n");

        System.out.printf("id: %d - nome: %s - taxa: %f \n",
            restauranteAdicionado.getId(), restauranteAdicionado.getNome(), restauranteAdicionado.getTaxaFrete());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void buscar(RestauranteRepository restauranteRepository) {

        Restaurante restaurante = restauranteRepository.buscar(2L);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Buscar restaurante ----- \n");

        System.out.printf("id: %d - nome: %s - taxa: %f \n",
            restaurante.getId(), restaurante.getNome(), restaurante.getTaxaFrete());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void alterar(RestauranteRepository restauranteRepository) {

        Restaurante restaurante = restauranteRepository.buscar(2L);

        restaurante.setNome(restaurante.getNome() + " Alterado");

        Restaurante restauranteAlterado = restauranteRepository.salvar(restaurante);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Alterar restaurante ----- \n");

        System.out.printf("id: %d - nome: %s \n", restauranteAlterado.getId(), restauranteAlterado.getNome());

        System.out.println("\n------------------------------------------------------------\n");

    }

    private static void excluir(RestauranteRepository restauranteRepository) {

        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);

        restauranteRepository.excluir(restaurante);

        System.out.println("\n------------------------------------------------------------\n");
        System.out.println("----- Excluir restaurante ----- \n");

        System.out.printf("id: %d \n", restaurante.getId());

        System.out.println("\n------------------------------------------------------------\n");

    }

}
