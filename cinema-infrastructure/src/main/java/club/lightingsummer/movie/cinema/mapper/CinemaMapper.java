package club.lightingsummer.movie.cinema.mapper;

import club.lightingsummer.movie.cinema.po.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CinemaMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);

    List<Cinema> selectCinemaByCondition(@Param("brandId") int brandId,
                                         @Param("hallType") String hallType,
                                         @Param("districtId") int districtId);

    int selectCountByCondition(@Param("brandId") int brandId,
                               @Param("hallType") String hallType,
                               @Param("districtId") int districtId);
}