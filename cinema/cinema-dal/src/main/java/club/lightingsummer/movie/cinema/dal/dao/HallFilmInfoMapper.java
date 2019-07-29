package club.lightingsummer.movie.cinema.dal.dao;

import club.lightingsummer.movie.cinema.api.po.HallFilmInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HallFilmInfoMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(HallFilmInfo record);

    int insertSelective(HallFilmInfo record);

    HallFilmInfo selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(HallFilmInfo record);

    int updateByPrimaryKey(HallFilmInfo record);

    List<HallFilmInfo> selectHallFilmInfoByCinemaId(@Param("cinemaId") int cinemaId);
}