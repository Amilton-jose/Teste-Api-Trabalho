package br.growdev.trabalho.ApiServerRest.teste.produtos;

import br.growdev.trabalho.ApiServerRest.dominio.Produto;
import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import org.apache.http.HttpStatus;
import org.junit.Test;


import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestaBuscaProdutoPorId extends TesteBase {

    @Test
    public void testaBuscaProdutoPorId(){
        String token = pegaToken();
        String id = idDoCadastroProduto(token);

        Produto produto = when()
                .get(BUSCA_PRODUTOS_PORID_ENDPOINT+id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Produto.class);

        assertThat(produto.getPreco(),is(produto.getPreco()));
        assertThat(produto.getNome(),is(produto.getNome()));
        assertThat(produto.getQuantidade(),is(produto.getQuantidade()));
        assertThat(produto.getDescricao(),is(produto.getDescricao()));

        deletaProdutoPorId(token, id);
    }




}
