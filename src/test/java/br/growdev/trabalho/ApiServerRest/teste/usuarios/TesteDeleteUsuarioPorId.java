package br.growdev.trabalho.ApiServerRest.teste.usuarios;

import br.growdev.trabalho.ApiServerRest.teste.TesteBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static br.growdev.trabalho.ApiServerRest.Utils.*;
import static io.restassured.RestAssured.given;

public class TesteDeleteUsuarioPorId extends TesteBase {
    @Test
    public  void deletaUsuarioPorIdInvalido(){
        String id = "q7Qre16BHSc7faxw";

        given().contentType(ContentType.JSON)
                .when()
                .delete(DELETA_USUARIO_PORID_ENDPOINT + id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public  void deletaUsuarioPorIdValido(){
        String id = idDoCadastroUsuario();
        given()
                .contentType(ContentType.JSON)
        .when()
                .delete(DELETA_USUARIO_PORID_ENDPOINT+id)
        .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }
}
