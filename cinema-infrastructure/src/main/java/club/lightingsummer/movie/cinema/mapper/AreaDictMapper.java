package club.lightingsummer.movie.cinema.mapper;

import club.lightingsummer.movie.cinema.po.AreaDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AreaDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(AreaDict record);

    int insertSelective(AreaDict record);

    AreaDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(AreaDict record);

    int updateByPrimaryKey(AreaDict record);

    List<AreaDict> selectAllAreas();
}