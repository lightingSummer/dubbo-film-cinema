package club.lightingsummer.movie.cinema.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
@Data
public class HallInfoVO implements Serializable {
    // 影厅id
    private String hallFieldId;
    // 影厅名字
    private String hallName;
    // 价格
    private String price;
    // 座位布局文件
    private String seatFile;
    // 已售座位必须关联订单才能查询
    private String soldSeats;
}
