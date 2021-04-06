package br.com.zupfy.dtos;

import br.com.zupfy.models.Album;
import br.com.zupfy.models.Banda;
import br.com.zupfy.models.Musica;

import java.time.LocalTime;

public class CadastrarMusicaDTO {

    private String nomeMusica;
    private LocalTime duracao;
    private String enderecoMusica;
    private int idBanda;
    private Album album;

    public CadastrarMusicaDTO() {
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getEnderecoMusica() {
        return enderecoMusica;
    }

    public void setEnderecoMusica(String enderecoMusica) {
        this.enderecoMusica = enderecoMusica;
    }

    public int getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    public Musica converterCadastrarMusicaDTOParaMusica() {
        Musica musica = new Musica();
        musica.setNomeMusica(this.nomeMusica);
        musica.setDuracao(this.duracao);
        musica.setEnderecoMusica(this.enderecoMusica);
        Banda banda = new Banda();
        banda.setId(this.idBanda);
        musica.setBanda(banda);
        musica.setAlbum(this.album);

        return musica;
    }
}
