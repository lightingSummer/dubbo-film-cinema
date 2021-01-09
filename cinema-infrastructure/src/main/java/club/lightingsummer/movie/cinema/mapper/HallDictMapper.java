package club.lightingsummer.movie.cinema.mapper;

import club.lightingsummer.movie.cinema.po.HallDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HallDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(HallDict record);

    int insertSelective(HallDict record);

    HallDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(HallDict record);

    int updateByPrimaryKey(HallDict record);

    List<HallDict> selectAllHalls();
}