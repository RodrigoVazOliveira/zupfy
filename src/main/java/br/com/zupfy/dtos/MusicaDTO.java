package br.com.zupfy.dtos;

import br.com.zupfy.models.Banda;

import java.time.LocalTime;

public class MusicaDTO {
    private int id;
    private String nomeMusica;
    private LocalTime duracao;
    private String enderecoMusica;
    private Banda banda;

    public MusicaDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

    public void setDuracao(LocalTime duracao) {
        this.duracao = duracao;
    }

    public String getEnderecoMusica() {
        return enderecoMusica;
    }

    public void setEnderecoMusica(String enderecoMusica) {
        this.enderecoMusica = enderecoMusica;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
}
