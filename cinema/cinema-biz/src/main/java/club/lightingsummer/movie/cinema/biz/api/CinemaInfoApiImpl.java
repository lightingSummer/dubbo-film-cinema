package club.lightingsummer.movie.cinema.biz.api;

import club.lightingsummer.movie.cinema.api.api.CinemaInfoApi;
import club.lightingsummer.movie.cinema.api.po.Cinema;
import club.lightingsummer.movie.cinema.api.vo.CinemaQueryVO;
import club.lightingsummer.movie.cinema.api.vo.CinemaVO;
import club.lightingsummer.movie.cinema.api.vo.Page;
import club.lightingsummer.movie.cinema.dal.dao.CinemaMapper;
import club.lightingsummer.movie.cinema.dal.dao.HallFilmInfoMapper;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description： 影院查询API
 */
@Component
public class CinemaInfoApiImpl implements CinemaInfoApi {
    private static final Logger logger = LoggerFactory.getLogger(CinemaInfoApiImpl.class);

    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private HallFilmInfoMapper hallFilmInfoMapper;

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 查询影院接口
     */
    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO) {
        Page<CinemaVO> page = new Page<>(cinemaQueryVO.getNowPage());
        try {
            String hallId = null;
            if (cinemaQueryVO.getHallType() != 99) {
                hallId = "%#" + cinemaQueryVO.getHallType() + "%#";
            }
            // 分页查询
            PageHelper.startPage(cinemaQueryVO.getNowPage(), cinemaQueryVO.getPageSize());
            List<Cinema> cinemas = cinemaMapper.selectCinemaByCondition(cinemaQueryVO.getBrandId(),
                    hallId, cinemaQueryVO.getDistrictId());
            // count查询
            int conditionCount = cinemaMapper.selectCountByCondition(cinemaQueryVO.getBrandId(),
                    hallId, cinemaQueryVO.getDistrictId());
            // 遍历写入VO
            List<CinemaVO> cinemaVOS = new ArrayList<>();
            for (Cinema cinema : cinemas) {
                CinemaVO cinemaVO = new CinemaVO();
                cinemaVO.setAddress(cinema.getCinemaAddress());
                cinemaVO.setCinemaName(cinema.getCinemaName());
                cinemaVO.setMinimumPrice(cinema.getMinimumPrice() + "");
                cinemaVO.setUuid(cinema.getUuid() + "");
                cinemaVOS.add(cinemaVO);
            }
            page.setTotalPage((int) Math.ceil(conditionCount / (1.0 * cinemaQueryVO.getPageSize())));
            page.setData(cinemaVOS);
        } catch (Exception e) {
            logger.error("查询影院失败" + e.getMessage());
        }
        return page;
    }
}
