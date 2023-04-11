package br.growdev.trabalho.ApiServerRest;

import br.growdev.trabalho.ApiServerRest.dominio.Usuario;
import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class Utils extends TesteBase {
    //------------------ENDPOINTS DO USUARIO--------------------//
    public static final String DELETA_USUARIO_PORID_ENDPOINT = "/usuarios/";
    public static final String BUSCA_USUARIO_PORID_ENDPOINT = "/usuarios/";
    public static final String CADASTRA_USUARIO_ENDPOINT = "/usuarios";
    public static final String LISTA_USUARIOS_ENDPOINT = "/usuarios";

    //------------------ENDPOINTS DO PRODUTO--------------------//
    public static final String LISTA_PRODUTOS_ENDPOINT = "/produtos";
    public static final String CADASTRA_PRODUTOS_ENDPOINT = "/produtos";
    public static final String DELETA_PRODUTOS_ENDPOINT = "/produtos/";
    public static final String BUSCA_PRODUTOS_PORID_ENDPOINT = "/produtos/";
    public static final String PUT_PRODUTOS_PORID_ENDPOINT = "/produtos/";
    public static final String LOGIN_ENDPOINT = "/login";
    public static final String AUTENTICACAO = "authorization";
    public static String token = pegaToken();

    public static String cadastraUsuario() {
        Faker faker = new Faker(new Locale("pt-br"));
        Map<String,String> usuario = new HashMap<>();
        usuario.put("nome",faker.address().firstName());
        usuario.put("email",faker.internet().emailAddress());
        usuario.put("password",faker.internet().password());
        usuario.put("administrador","true");

        return given()
                    .contentType(ContentType.JSON)
                    .body(usuario)
                .when()
                    .post(CADASTRA_USUARIO_ENDPOINT)
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(HttpStatus.SC_CREATED)
                    .body("message", is("Cadastro realizado com sucesso"))
                .extract().path("_id");
    }


    public static String pegaToken() {
        Map<String, String> usuario = new HashMap<>();
        usuario.put("email", "giulia.jaques@yahoo.com");
        usuario.put("password", "3fobtl6mok7");

        return given()
                    .contentType(ContentType.JSON)
                    .body(usuario)
                .when()
                    .post(LOGIN_ENDPOINT)
                .then()
                    .contentType(ContentType.JSON)
                    .extract().path(AUTENTICACAO);
    }

}
