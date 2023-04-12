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

    private static String idDoCadastroProduto(String token) {
        Faker produtoFake = new Faker(new Locale("pt-br"));
        Map<String,String> produto = new HashMap<>();
        produto.put("nome",produtoFake.leagueOfLegends().champion());
        produto.put("preco",produtoFake.number().digit());
        produto.put("descricao",produtoFake.leagueOfLegends().summonerSpell());
        produto.put("quantidade",produtoFake.number().digit());


        String id = given()
                .contentType(ContentType.JSON)
                .header(AUTENTICACAO, token)
                .body(produto)
                .when()
                .post(CADASTRA_PRODUTOS_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");
        return id;
    }
}
