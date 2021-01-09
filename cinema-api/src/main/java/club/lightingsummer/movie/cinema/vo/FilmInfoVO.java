package club.lightingsummer.movie.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description： 影院当日放映信息
 */
@Data
public class FilmInfoVO implements Serializable {
    // 电影ID
    private String filmId;
    // 电影名字
    private String filmName;
    // 电影时长
    private String filmLength;
    // 电影语言
    private String filmType;
    // 电影类型
    private String filmCats;
    // 演员
    private String actors;
    // 封面
    private String imgAddress;
    // 场次信息
    private List<FilmFieldVO> filmFields;
}
