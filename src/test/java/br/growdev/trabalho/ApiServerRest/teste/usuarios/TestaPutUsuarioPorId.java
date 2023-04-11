package br.growdev.trabalho.ApiServerRest.teste.usuarios;

import br.growdev.trabalho.ApiServerRest.dominio.Usuario;
import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.Locale;

import static br.growdev.trabalho.ApiServerRest.Utils.BUSCA_USUARIO_PORID_ENDPOINT;
import static br.growdev.trabalho.ApiServerRest.Utils.cadastraUsuario;
import static io.restassured.RestAssured.given;

public class TestaPutUsuarioPorId extends TesteBase {
    @Test
    public void testaPutUsuarioPorIdValido() {
        String id = cadastraUsuario();
        Faker usuarioFaker = new Faker(new Locale("pt-bt"));
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioFaker.address().firstName());
        usuario.setEmail(usuarioFaker.internet().emailAddress());
        usuario.setPassword("putdousuario");
        usuario.setAdministrador("true");
        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .put(BUSCA_USUARIO_PORID_ENDPOINT + id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void testaPutUsuarioPorIdInvalido() {
        String id = cadastraUsuario();
        Faker usuarioFaker = new Faker(new Locale("pt-bt"));
        Usuario usuario = new Usuario();
        usuario.setNome("Amiltontestput");
        usuario.setEmail("amiltonputtest@put.com");
        usuario.setPassword("putdousuario");
        usuario.setAdministrador("true");
        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .put(BUSCA_USUARIO_PORID_ENDPOINT + id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }
}
