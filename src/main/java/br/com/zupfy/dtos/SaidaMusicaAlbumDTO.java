package br.com.zupfy.dtos;

import br.com.zupfy.models.Musica;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaidaMusicaAlbumDTO {
    private Integer id;
    private String nome;
    private Integer anoLancamento;
    private List<SaidaMusicaDTO> musicas;

    public SaidaMusicaAlbumDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public List<SaidaMusicaDTO> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<SaidaMusicaDTO> musicas) {
        this.musicas = musicas;
    }

    public static SaidaMusicaAlbumDTO converterModelParaDto(Musica musica) {
        SaidaMusicaAlbumDTO saidaMusicaAlbumDTO = new SaidaMusicaAlbumDTO();
        saidaMusicaAlbumDTO.setId(musica.getAlbum().getId());
        saidaMusicaAlbumDTO.setNome(musica.getAlbum().getNome());
        saidaMusicaAlbumDTO.setAnoLancamento(musica.getAlbum().getAnoLancamento());
        List<SaidaMusicaDTO> saidaMusicadto = new ArrayList<>();
        musica.getAlbum().getMusicas().stream()
                .map(
                        obj -> saidaMusicadto.add(converterModelMusicaParaSaidaMusicaDto(obj))
                ).collect(Collectors.toList());
        saidaMusicaAlbumDTO.setMusicas(saidaMusicadto);

        return saidaMusicaAlbumDTO;
    }

    public static SaidaMusicaDTO converterModelMusicaParaSaidaMusicaDto(Musica musica) {
        SaidaMusicaDTO saidaMusicaDTO = new SaidaMusicaDTO();
        saidaMusicaDTO.setId(musica.getId());
        saidaMusicaDTO.setNomeMusica(musica.getNomeMusica());
        return saidaMusicaDTO;
    }
}
