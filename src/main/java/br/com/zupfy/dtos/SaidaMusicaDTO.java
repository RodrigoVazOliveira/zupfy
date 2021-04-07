package br.com.zupfy.dtos;

public class SaidaMusicaDTO {
    private Integer id;
    private String nomeMusica;

    public SaidaMusicaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }
}
