package club.lightingsummer.movie.cinema.dal.dao;

import club.lightingsummer.movie.cinema.api.po.AreaDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AreaDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(AreaDict record);

    int insertSelective(AreaDict record);

    AreaDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(AreaDict record);

    int updateByPrimaryKey(AreaDict record);
}