package club.lightingsummer.movie.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
@Data
public class CinemaVO implements Serializable {
    // 影院ID
    private String uuid;
    // 影院名字
    private String cinemaName;
    // 影院地址
    private String address;
    // 影院最低价格
    private String minimumPrice;
}
