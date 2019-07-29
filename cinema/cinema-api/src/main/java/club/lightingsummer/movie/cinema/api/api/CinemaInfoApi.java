package club.lightingsummer.movie.cinema.api.api;

import club.lightingsummer.movie.cinema.api.vo.*;

import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
public interface CinemaInfoApi {
    // 根据CinemaQueryVO，查询影院列表
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);
    // 根据影院id，获取影院信息
    CinemaInfoVO getCinemaInfoById(int cinemaId);
    // 获取影院品牌列表
    List<BrandVO> getBrands(int brandId);
}
