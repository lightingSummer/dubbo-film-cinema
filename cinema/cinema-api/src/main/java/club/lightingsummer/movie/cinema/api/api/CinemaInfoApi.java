package club.lightingsummer.movie.cinema.api.api;

import club.lightingsummer.movie.cinema.api.vo.CinemaQueryVO;
import club.lightingsummer.movie.cinema.api.vo.CinemaVO;
import club.lightingsummer.movie.cinema.api.vo.Page;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
public interface CinemaInfoApi {
    // 根据CinemaQueryVO，查询影院列表
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);
}
