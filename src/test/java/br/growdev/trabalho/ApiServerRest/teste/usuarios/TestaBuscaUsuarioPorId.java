package br.growdev.trabalho.ApiServerRest.teste.usuarios;

import br.growdev.trabalho.ApiServerRest.dominio.Usuario;
import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class TestaBuscaUsuarioPorId extends TesteBase {
    @Test
    public  void testaBuscaUsuarioPorId(){
        String id = idDoCadastroUsuario();
        Usuario usuario = given()
                .contentType(ContentType.JSON)
                .param("_id",id)
                .get(BUSCA_USUARIO_PORID_ENDPOINT)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getObject("usuarios[0]",Usuario.class);

        assertThat(usuario.getNome(),is(usuario.getNome()));
        assertThat(usuario.getEmail(),is(usuario.getEmail()));
        assertThat(usuario.getPassword(),is(notNullValue()));
        assertThat(usuario.getAdministrador(),is("true"));
    }
}
