package br.growdev.trabalho.ApiServerRest.teste.produtos;

import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static br.growdev.trabalho.ApiServerRest.Utils.LISTA_PRODUTOS_ENDPOINT;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class TestaListaProdutos extends TesteBase {

    @Test
    public void testaListaProdutos(){

        when()
            .get(LISTA_PRODUTOS_ENDPOINT)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("quantidade", is(notNullValue()))
                .body("produtos.size",is(notNullValue()));

    }
}
