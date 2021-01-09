package club.lightingsummer.movie.cinema.po;

import lombok.Data;

@Data
public class Field {
    private Integer uuid;

    private Integer cinemaId;

    private Integer filmId;

    private String beginTime;

    private String endTime;

    private Integer hallId;

    private String hallName;

    private Integer price;
}