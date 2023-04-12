package br.growdev.trabalho.ApiServerRest;

import br.growdev.trabalho.ApiServerRest.dominio.Login;
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

    public static String idDoCadastroUsuario() {
        Faker faker = new Faker(new Locale("pt-br"));
        Map<String, String> usuario = new HashMap<>();
        usuario.put("nome", faker.address().firstName());
        usuario.put("email", faker.internet().emailAddress());
        usuario.put("password", faker.internet().password());
        usuario.put("administrador", "true");

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
        Login loginValido = retornaLoginValido();
        return given()
                .contentType(ContentType.JSON)
                .body(loginValido)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .body("message", is("Login realizado com sucesso"))
                .extract().path(AUTENTICACAO);
    }

    public static Login retornaLoginValido() {
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
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_CREATED)
                .extract().path("_id");

        return when()
                .get(BUSCA_USUARIO_PORID_ENDPOINT + id)
                .then()
                .extract()
                .as(Login.class);


    }

    public static void deletaProdutoPorId(String token, String id) {
        given()
                .header(AUTENTICACAO, token)
                .when()
                .delete(DELETA_PRODUTOS_ENDPOINT + id)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
    public static String idDoCadastroProduto(String token) {
        Faker produtoFake = new Faker(new Locale("pt-br"));
        Map<String, String> produto = new HashMap<>();
        produto.put("nome", produtoFake.leagueOfLegends().champion());
        produto.put("preco", produtoFake.number().digits(3));
        produto.put("descricao", produtoFake.leagueOfLegends().summonerSpell());
        produto.put("quantidade", produtoFake.number().digits(3));


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
