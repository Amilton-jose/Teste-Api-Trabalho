package br.growdev.trabalho.ApiServerRest.teste.login;

import br.growdev.trabalho.ApiServerRest.dominio.Login;
import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static br.growdev.trabalho.ApiServerRest.Utils.LOGIN_ENDPOINT;
import static br.growdev.trabalho.ApiServerRest.Utils.retornaLoginValido;
import static io.restassured.RestAssured.given;
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

    


}
