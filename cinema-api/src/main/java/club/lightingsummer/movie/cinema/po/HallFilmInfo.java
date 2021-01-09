package club.lightingsummer.movie.cinema.po;

import lombok.Data;

@Data
public class HallFilmInfo {
    private Integer uuid;

    private Integer filmId;

    private String filmName;

    private String filmLength;

    private String filmCats;

    private String filmLanguage;

    private String actors;

    private String imgAddress;
}