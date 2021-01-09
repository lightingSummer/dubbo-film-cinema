package club.lightingsummer.movie.cinema.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
@Data
public class HallTypeVO implements Serializable {
    private String halltypeId;
    private String halltypeName;
    private boolean isActive;
}
