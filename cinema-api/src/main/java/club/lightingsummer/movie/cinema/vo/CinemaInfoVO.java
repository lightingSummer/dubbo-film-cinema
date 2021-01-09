package club.lightingsummer.movie.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description： 影院信息
 */
@Data
public class CinemaInfoVO implements Serializable {
    // 影院id
    private String cinemaId;
    // 影院图片
    private String imgUrl;
    // 影院名字
    private String cinemaName;
    // 影院地址
    private String cinemaAdress;
    // 影院电话
    private String cinemaPhone;
}
