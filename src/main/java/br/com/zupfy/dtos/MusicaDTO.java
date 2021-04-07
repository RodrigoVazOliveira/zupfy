package br.com.zupfy.dtos;

import br.com.zupfy.models.Musica;

import java.time.LocalTime;

public class MusicaDTO {
    private int id;
    private String nomeMusica;
    private LocalTime duracao;
    private String enderecoMusica;
    private BandaDTO banda;
    private SaidaMusicaAlbumDTO album;

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

    public BandaDTO getBanda() {
        return banda;
    }

    public void setBanda(BandaDTO banda) {
        this.banda = banda;
    }

    public SaidaMusicaAlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(SaidaMusicaAlbumDTO album) {
        this.album = album;
    }

    public static MusicaDTO converterMusicaParaMusicaDTO(Musica musica) {
        MusicaDTO musicaDTO = new MusicaDTO();
        musicaDTO.setNomeMusica(musica.getNomeMusica());
        musicaDTO.setId(musica.getId());
        musicaDTO.setDuracao(musica.getDuracao());
        musicaDTO.setEnderecoMusica(musica.getEnderecoMusica());
        musicaDTO.setBanda(BandaDTO.converterBandaParaBandaDTO(musica.getBanda()));
        musicaDTO.setAlbum(SaidaMusicaAlbumDTO.converterModelParaDto(musica));
        return musicaDTO;
    }
}