package br.growdev.trabalho.ApiServerRest.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private int preco;
    private String nome;
    private int quantidade;
    private String descricao;
    private String _id;


}
