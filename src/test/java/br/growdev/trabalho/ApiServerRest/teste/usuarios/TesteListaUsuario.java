package br.growdev.trabalho.ApiServerRest.teste.usuarios;

import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static br.growdev.trabalho.ApiServerRest.Utils.LISTA_USUARIOS_ENDPOINT;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class TesteListaUsuario extends TesteBase {

    @Test
    public void testeListaUsuariosCadastrados(){
        when()
                .get(LISTA_USUARIOS_ENDPOINT)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("quantidade",is(notNullValue()));
    }
}
