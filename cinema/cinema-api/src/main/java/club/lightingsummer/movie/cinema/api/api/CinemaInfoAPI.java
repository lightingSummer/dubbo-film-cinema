package club.lightingsummer.movie.cinema.api.api;

import club.lightingsummer.movie.cinema.api.vo.*;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
public interface CinemaInfoAPI {
    // 根据CinemaQueryVO，查询影院列表
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);
    // 根据影院id，获取影院信息
    CinemaInfoVO getCinemaInfoById(int cinemaId);
    // 获取影院品牌列表
    List<BrandVO> getBrands(int brandId);
    // 获取影院区域列表
    List<AreaVO> getAreas(int areaId);
    // 获取影厅类型列表
    List<HallTypeVO> getHallTypes(int hallType);
    // 根据影院编号获取放映场次信息
    List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId);
    // 根据放映场次id获取放映信息
    HallInfoVO getFilmFieldInfo(int fieldId);
    // 根据电影编号获取对应的电影信息
    FilmInfoVO getFilmInfoByFieldId(int fieldId);
}
