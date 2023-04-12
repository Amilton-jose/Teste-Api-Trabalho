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

public class TestaCadastraProduto extends TesteBase {
    @Test
    public void testaCadastroProdutos(){
        String token = pegaToken();
        Faker produtoFake = new Faker(new Locale("pt-br"));
        Map<String,String> produto = new HashMap<>();
        produto.put("nome",produtoFake.leagueOfLegends().champion());
        produto.put("preco",produtoFake.number().digits(3));
        produto.put("descricao",produtoFake.leagueOfLegends().summonerSpell());
        produto.put("quantidade",produtoFake.number().digits(3));


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


}
