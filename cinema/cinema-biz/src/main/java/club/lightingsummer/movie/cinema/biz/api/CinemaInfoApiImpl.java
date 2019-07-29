package club.lightingsummer.movie.cinema.biz.api;

import club.lightingsummer.movie.cinema.api.api.CinemaInfoApi;
import club.lightingsummer.movie.cinema.api.po.AreaDict;
import club.lightingsummer.movie.cinema.api.po.BrandDict;
import club.lightingsummer.movie.cinema.api.po.Cinema;
import club.lightingsummer.movie.cinema.api.po.HallDict;
import club.lightingsummer.movie.cinema.api.vo.*;
import club.lightingsummer.movie.cinema.dal.dao.*;
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
    @Autowired
    private BrandDictMapper brandDictMapper;
    @Autowired
    private AreaDictMapper areaDictMapper;
    @Autowired
    private HallDictMapper hallDictMapper;

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

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 根据影院id获取影院信息
     */
    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId) {
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();
        try {
            Cinema cinema = cinemaMapper.selectByPrimaryKey(cinemaId);
            // 复制到VO
            cinemaInfoVO.setImgUrl(cinema.getImgAddress());
            cinemaInfoVO.setCinemaPhone(cinema.getCinemaPhone());
            cinemaInfoVO.setCinemaName(cinema.getCinemaName());
            cinemaInfoVO.setCinemaId(cinema.getUuid() + "");
            cinemaInfoVO.setCinemaAdress(cinema.getCinemaAddress());
        } catch (Exception e) {
            logger.error("根据影院id获取影院信息失败" + e.getMessage());
        }
        return cinemaInfoVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 获取品牌列表
     */
    @Override
    public List<BrandVO> getBrands(int brandId) {
        List<BrandVO> response = new ArrayList<>();
        try {
            // 判断是否点亮全部按钮
            boolean ifLightAllButton = false;
            if (brandId == 99) {
                ifLightAllButton = true;
            } else {
                BrandDict brandIfExits = brandDictMapper.selectByPrimaryKey(brandId);
                if (brandIfExits == null) {
                    ifLightAllButton = true;
                }
            }
            List<BrandDict> brandDicts = brandDictMapper.selectAllBrand();
            // 遍历转换为VO
            for (BrandDict brandDict : brandDicts) {
                BrandVO brandVO = new BrandVO();
                brandVO.setBrandId(brandDict.getUuid() + "");
                brandVO.setBrandName(brandDict.getShowName());
                if (!ifLightAllButton && brandDict.getUuid() == brandId) {
                    brandVO.setActive(true);
                } else if (ifLightAllButton && brandDict.getUuid() == 99) {
                    brandVO.setActive(true);
                }
                response.add(brandVO);
            }
        } catch (Exception e) {
            logger.error("获取影院品牌失败" + e.getMessage());
        }
        return response;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 获取影院区域列表
     */
    @Override
    public List<AreaVO> getAreas(int areaId) {
        List<AreaVO> response = new ArrayList<>();
        try {
            // 判断是否点亮全部按钮
            boolean ifLightAllButton = false;
            if (areaId == 99) {
                ifLightAllButton = true;
            } else {
                AreaDict areaIfExits = areaDictMapper.selectByPrimaryKey(areaId);
                if (areaIfExits == null) {
                    ifLightAllButton = true;
                }
            }
            // 遍历转化为VO
            List<AreaDict> areaDicts = areaDictMapper.selectAllAreas();
            for (AreaDict areaDict : areaDicts) {
                AreaVO areaVO = new AreaVO();
                areaVO.setAreaId(areaDict.getUuid() + "");
                areaVO.setAreaName(areaDict.getShowName());
                if (!ifLightAllButton) {
                    if (areaDict.getUuid() == areaId) {
                        areaVO.setActive(true);
                    }
                } else if (areaDict.getUuid() == 99) {
                    areaVO.setActive(true);
                }
                response.add(areaVO);
            }
        } catch (Exception e) {
            logger.error("查询影院区域列表失败" + e.getMessage());
        }
        return response;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 获取影院类型列表
     */
    @Override
    public List<HallTypeVO> getHallTypes(int hallType) {
        List<HallTypeVO> response = new ArrayList<>();
        try {
            // 判断是否点亮全部按钮
            boolean ifLightAllButton = false;
            if (hallType == 99) {
                ifLightAllButton = true;
            } else {
                HallDict hallIfExits = hallDictMapper.selectByPrimaryKey(hallType);
                if (hallIfExits == null) {
                    ifLightAllButton = true;
                }
            }
            // 遍历转化为VO
            List<HallDict> hallDicts = hallDictMapper.selectAllHalls();
            for (HallDict hallDict : hallDicts) {
                HallTypeVO hallTypeVO = new HallTypeVO();
                hallTypeVO.setHalltypeId(hallDict.getUuid() + "");
                hallTypeVO.setHalltypeName(hallDict.getShowName());
                if (ifLightAllButton) {
                    if (hallDict.getUuid() == 99) {
                        hallTypeVO.setActive(true);
                    }
                } else {
                    if (hallDict.getUuid() == hallType) {
                        hallTypeVO.setActive(true);
                    }
                }
                response.add(hallTypeVO);
            }
        } catch (Exception e) {
            logger.error("获取影院类型列表" + e.getMessage());
        }
        return response;
    }
}
