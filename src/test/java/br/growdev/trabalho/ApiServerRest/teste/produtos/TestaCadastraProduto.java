package br.growdev.trabalho.ApiServerRest.teste.produtos;

import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.given;

public class TestaCadastraProduto extends TesteBase {
    @Test
    public void testaCadastroProdutos(){
        String token = pegaToken();
        Map<String,String> produto = new HashMap<>();
        produto.put("nome","Mouse Lixnu");
        produto.put("preco","1997");
        produto.put("descricao","Gamer RGB");
        produto.put("quantidade","1997");


        String id = given()
                .contentType(ContentType.JSON)
                .header(AUTENTICACAO, token)
                .body(produto)
        .when()
                .post(CADASTRA_PRODUTOS_ENDPOINT)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");

        delaProdutoPorId(token, id);
    }

    private static void delaProdutoPorId(String token, String id) {
        given()
                .header(AUTENTICACAO, token)
        .when()
                .delete(DELETA_PRODUTOS_ENDPOINT+ id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}
