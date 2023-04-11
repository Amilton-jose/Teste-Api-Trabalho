package br.growdev.trabalho.ApiServerRest.teste.produtos;

import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestaPutProdutoPorId extends TesteBase {
    @Test
    public void testaPutProdutoPorId(){
        String token = pegaToken();
        String id = idCadastroProduto(token);

        Map<String,String> novoProduto = new HashMap<>();
        novoProduto.put("nome","Mouse Put novo");
        novoProduto.put("preco","123456");
        novoProduto.put("descricao","Gamer Put novo");
        novoProduto.put("quantidade","3006");

        given()
                .contentType(ContentType.JSON)
                .header(AUTENTICACAO, token)
                .body(novoProduto)
        .when()
                .put(PUT_PRODUTOS_PORID_ENDPOINT+id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("message", is("Registro alterado com sucesso"));


        deletaProdutoPorId(token, id);
    }

    private static void deletaProdutoPorId(String token, String id) {
        given()
                .header(AUTENTICACAO, token)
                .when()
                .delete(DELETA_PRODUTOS_ENDPOINT+ id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    private static String idCadastroProduto(String token) {
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
                .post("/produtos")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");
        return id;
    }
}
