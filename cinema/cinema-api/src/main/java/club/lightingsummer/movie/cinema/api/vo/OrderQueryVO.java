package club.lightingsummer.movie.cinema.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/8/2 0002
 * @description： 暴露给订单模块的接口
 */
@Data
public class OrderQueryVO implements Serializable {
    // 影院id
    private Integer cinemaId;
    // 场次id
    private Integer fieldId;
    // 影片id
    private Integer filmId;
    // 价格
    private Integer price;
}
