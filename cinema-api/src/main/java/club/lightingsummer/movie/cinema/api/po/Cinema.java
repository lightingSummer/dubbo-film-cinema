package club.lightingsummer.movie.cinema.api.po;

import lombok.Data;

@Data
public class Cinema {
    private Integer uuid;

    private String cinemaName;

    private String cinemaPhone;

    private Integer brandId;

    private Integer areaId;

    private String hallIds;

    private String imgAddress;

    private String cinemaAddress;

    private Integer minimumPrice;
}