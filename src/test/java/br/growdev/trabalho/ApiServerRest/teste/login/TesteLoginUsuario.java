package br.growdev.trabalho.ApiServerRest.teste.login;

import br.growdev.trabalho.ApiServerRest.dominio.Login;
import br.growdev.trabalho.ApiServerRest.dominio.Usuario;
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
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;


public class TesteLoginUsuario extends TesteBase {


    @Test
    public void testeLoginValido() {
        Login loginValido = retornaLoginValido();
        given()
                .contentType(ContentType.JSON)
                .body(loginValido)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("message", is("Login realizado com sucesso"));
    }

    @Test
    public void testeLoginInvalido() {
        Map<String,String> loginInvalido = new HashMap<>();
        loginInvalido.put("email","logininvalido@cobol.br.com");
        loginInvalido.put("password","logininvalido");
        given()
                .contentType(ContentType.JSON)
                .body(loginInvalido)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("message", is("Email e/ou senha inválidos"));
    }

    
    private static Login retornaLoginValido() {
        Faker faker = new Faker(new Locale("pt-br"));

        Usuario criaUsuario = new Usuario();
        criaUsuario.setNome(faker.address().firstName());
        criaUsuario.setEmail(faker.internet().emailAddress());
        criaUsuario.setPassword(faker.internet().password());
        criaUsuario.setAdministrador("true");

        String id = given()
                .contentType(ContentType.JSON)
                .body(criaUsuario)
                .when()
                .post(CADASTRA_USUARIO_ENDPOINT)
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");

        return  when()
                .get(BUSCA_USUARIO_PORID_ENDPOINT+id)
                .then()
                .log().all()
                .extract()
                .as(Login.class);


    }

}
