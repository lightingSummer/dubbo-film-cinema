package club.lightingsummer.movie.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
@Data
public class AreaVO implements Serializable {
    private String areaId;
    private String areaName;
    private boolean isActive;
}
