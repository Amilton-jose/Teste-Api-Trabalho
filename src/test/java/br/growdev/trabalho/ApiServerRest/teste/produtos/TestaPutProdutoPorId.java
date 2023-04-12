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
import static org.hamcrest.Matchers.is;

public class TestaPutProdutoPorId extends TesteBase {
    @Test
    public void testaPutProdutoPorId(){
        String token = pegaToken();
        String id = idDoCadastroProduto(token);

        Faker produtoFake = new Faker(new Locale("pt-br"));
        Map<String,String> produto = new HashMap<>();
        produto.put("nome",produtoFake.animal().name());
        produto.put("preco",produtoFake.number().digits(5));
        produto.put("descricao",produtoFake.leagueOfLegends().summonerSpell());
        produto.put("quantidade",produtoFake.number().digits(5));

        given()
                .contentType(ContentType.JSON)
                .header(AUTENTICACAO, token)
                .body(produto)
        .when()
                .put(PUT_PRODUTOS_PORID_ENDPOINT+id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("message", is("Registro alterado com sucesso"));


        deletaProdutoPorId(token, id);
    }




}
