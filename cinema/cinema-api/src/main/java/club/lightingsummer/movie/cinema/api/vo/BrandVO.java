package club.lightingsummer.movie.cinema.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
@Data
public class BrandVO implements Serializable {
    // 影院品牌id
    private String brandId;
    // 影院品牌名字
    private String brandName;
    // 是否被选中
    private boolean isActive;
}
