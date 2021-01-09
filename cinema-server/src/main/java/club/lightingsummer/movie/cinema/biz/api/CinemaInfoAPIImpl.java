package club.lightingsummer.movie.cinema.biz.api;

import club.lightingsummer.movie.cinema.api.CinemaInfoAPI;
import club.lightingsummer.movie.cinema.mapper.*;
import club.lightingsummer.movie.cinema.po.*;
import club.lightingsummer.movie.cinema.vo.*;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：lightingSummer
 * @date ：2019/7/29 0029
 * @description： 影院查询API
 */
@Component
@Service(interfaceClass = CinemaInfoAPI.class, loadbalance = "roundrobin", executes = 10)
public class CinemaInfoAPIImpl implements CinemaInfoAPI {
    private static final Logger logger = LoggerFactory.getLogger(CinemaInfoAPIImpl.class);

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
    @Autowired
    private FieldMapper fieldMapper;

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

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 根据影院编号获取放映场次信息
     */
    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId) {
        List<FilmInfoVO> response = new ArrayList<>();
        try {
            // 获取当日放映电影信息
            List<HallFilmInfo> hallFilmInfos = hallFilmInfoMapper.selectHallFilmInfoByCinemaId(cinemaId);
            // 遍历转化为VO
            for (HallFilmInfo hallFilmInfo : hallFilmInfos) {
                FilmInfoVO filmInfoVO = new FilmInfoVO();
                filmInfoVO.setImgAddress(hallFilmInfo.getImgAddress());
                filmInfoVO.setFilmType(hallFilmInfo.getFilmLanguage());
                filmInfoVO.setFilmName(hallFilmInfo.getFilmName());
                filmInfoVO.setFilmLength(hallFilmInfo.getFilmLength());
                filmInfoVO.setFilmId(hallFilmInfo.getFilmId() + "");
                filmInfoVO.setFilmCats(hallFilmInfo.getFilmCats());
                filmInfoVO.setActors(hallFilmInfo.getActors());
                // 获取场次信息
                List<FilmFieldVO> filmFieldVOS = new ArrayList<>();
                List<Field> fields = fieldMapper.selectFieldByCinemaIdAndFilmId(cinemaId, hallFilmInfo.getFilmId());
                // 场次信息转化为VO
                for (Field field : fields) {
                    FilmFieldVO filmFieldVO = new FilmFieldVO();
                    filmFieldVO.setLanguage(hallFilmInfo.getFilmLanguage());
                    filmFieldVO.setHallName(field.getHallName());
                    filmFieldVO.setFieldId(field.getUuid() + "");
                    filmFieldVO.setEndTime(field.getEndTime());
                    filmFieldVO.setBeginTime(field.getBeginTime());
                    filmFieldVO.setPrice(field.getPrice() + "");
                    filmFieldVOS.add(filmFieldVO);
                }
                filmInfoVO.setFilmFields(filmFieldVOS);
                response.add(filmInfoVO);
            }
        } catch (Exception e) {
            logger.error("获取场次信息" + e.getMessage());
        }
        return response;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 根据放映场次ID获取放映信息
     */
    @Override
    public HallInfoVO getFilmFieldInfo(int fieldId) {
        HallInfoVO hallInfoVO = new HallInfoVO();
        try {
            hallInfoVO = fieldMapper.selectFieldInfoByFieldId(fieldId);
        } catch (Exception e) {
            logger.error("根据放映场次ID获取放映信息失败" + e.getMessage());
        }
        return hallInfoVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/7/29 0029
     * @description: 根据电影编号获取对应的电影信息, VO复用之前的
     */
    @Override
    public FilmInfoVO getFilmInfoByFieldId(int fieldId) {
        FilmInfoVO filmInfoVO = new FilmInfoVO();
        try {
            filmInfoVO = fieldMapper.selectFilmInfoByFieldId(fieldId);
        } catch (Exception e) {
            logger.error("根据电影编号获取对应的电影信息失败" + e.getMessage());
        }
        return filmInfoVO;
    }

    /**
     * @author: lightingSummer
     * @date: 2019/8/2 0002
     * @description: 暴露给订单模块部分电影信息
     */
    @Override
    public OrderQueryVO getOrderNeedFilmInfoByField(int fieldId) {
        try {
            OrderQueryVO orderQueryVO = new OrderQueryVO();
            Field field = fieldMapper.selectByPrimaryKey(fieldId);
            orderQueryVO.setFilmId(field.getFilmId());
            orderQueryVO.setCinemaId(field.getCinemaId());
            orderQueryVO.setPrice(field.getPrice());
            orderQueryVO.setFieldId(field.getUuid());
            return orderQueryVO;
        } catch (Exception e) {
            logger.error("查询电影场次信息失败" + e.getMessage());
            return null;
        }
    }
}
