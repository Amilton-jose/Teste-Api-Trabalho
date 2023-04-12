package br.growdev.trabalho.ApiServerRest.dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto {

    private int preco;
    private String nome;
    private int quantidade;
    private String descricao;
    private String _id;

    public Produto() {
    }

    public Produto(int preco, String nome, int quantidade, String descricao, String _id) {
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this._id = _id;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "preco=" + preco +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
