package br.growdev.trabalho.ApiServerRest.teste.usuarios;

import br.growdev.trabalho.ApiServerRest.dominio.Usuario;
import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.Locale;

import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TestaCadastraUsuario extends TesteBase {

    @Test()
    public void testeCadastraUsuarioDadosValido() {
        Faker faker = new Faker(new Locale("pt-br"));

        Usuario criaUsuario = new Usuario();
        criaUsuario.setNome(faker.address().firstName());
        criaUsuario.setEmail(faker.internet().emailAddress());
        criaUsuario.setPassword(faker.internet().password());
        criaUsuario.setAdministrador("true");

        given()
                .contentType(ContentType.JSON)
                .body(criaUsuario)
        .when()
                .post(CADASTRA_USUARIO_ENDPOINT)
        .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", is("Cadastro realizado com sucesso"));
    }


    @Test()
    public void testeCadastraUsuarioDadosInvalidos() {
        Usuario usuario = new Usuario("Amilton","amilton@gmail.com","1234","true");
                given()
                    .contentType(ContentType.JSON)
                    .body(usuario)
                .when()
                    .post(CADASTRA_USUARIO_ENDPOINT)
                .then()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("message", is("Este email já está sendo usado"));

    }
}
