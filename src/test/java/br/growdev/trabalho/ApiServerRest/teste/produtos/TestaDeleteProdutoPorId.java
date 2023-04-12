package br.growdev.trabalho.ApiServerRest.teste.produtos;

import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.given;

public class TestaDeleteProdutoPorId extends TesteBase {

    @Test
    public void testaDeleteProdutoPorId() {
        String token  = pegaToken();
        String id = idDoCadastroProduto(token);

        given()
                .header(AUTENTICACAO, token)
        .when()
                .delete(DELETA_USUARIO_PORID_ENDPOINT+ id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

}
