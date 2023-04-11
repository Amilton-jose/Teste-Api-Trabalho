package br.growdev.trabalho.ApiServerRest.teste;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class TesteBase {

    @BeforeClass
    public static void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://serverest.dev";
    }
}
