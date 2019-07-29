package club.lightingsummer.movie.cinema.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description： 放映场次VO
 */
@Data
public class FilmFieldVO implements Serializable {
    // 场次id
    private String fieldId;
    // 开始时间
    private String beginTime;
    // 结束时间
    private String endTime;
    // 语言
    private String language;
    // 放映厅名称
    private String hallName;
    // 价格
    private String price;
}
