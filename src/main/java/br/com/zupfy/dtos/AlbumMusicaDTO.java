package br.com.zupfy.dtos;

import br.com.zupfy.models.Album;
import br.com.zupfy.models.Musica;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumMusicaDTO {

    private Integer id;
    private String nome;
    private Integer anoLancamento;
    @JsonIgnoreProperties({"album", "banda"})
    private List<MusicaDTO> musicas;

    public AlbumMusicaDTO() {
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

    public List<MusicaDTO> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaDTO> musicas) {
        this.musicas = musicas;
    }

    public static AlbumMusicaDTO converterAlbumParaAlbumMusicaDTO(Album album) {
        AlbumMusicaDTO albumMusicaDTO = new AlbumMusicaDTO();
        albumMusicaDTO.setId(album.getId());
        albumMusicaDTO.setNome(album.getNome());
        albumMusicaDTO.setAnoLancamento(album.getAnoLancamento());

        List<MusicaDTO> musicasDto = new ArrayList<>();
        album.getMusicas().stream()
                .map(musica ->
                    musicasDto.add(criarMusicaDTO(musica))
        ).collect(Collectors.toList());

        albumMusicaDTO.setMusicas(musicasDto);

        return albumMusicaDTO;
    }

    private static MusicaDTO criarMusicaDTO(Musica obj) {
        MusicaDTO musicaDTO = new MusicaDTO();
        musicaDTO.setId(obj.getId());
        musicaDTO.setNomeMusica(obj.getNomeMusica());
        musicaDTO.setDuracao(obj.getDuracao());
        musicaDTO.setEnderecoMusica(obj.getEnderecoMusica());
        musicaDTO.setBanda(BandaDTO.converterBandaParaBandaDTO(obj.getBanda()));
        return musicaDTO;
    }

    public static Iterable<AlbumMusicaDTO> converterListaAlbumParaListaAlbumMusicaDTO(Iterable<Album> albums) {
        List<AlbumMusicaDTO> albumMusicaDTOs = new ArrayList<>();

        for (Album album : albums) {
            albumMusicaDTOs.add(converterAlbumParaAlbumMusicaDTO(album));
        }

        return albumMusicaDTOs;
    }
}
