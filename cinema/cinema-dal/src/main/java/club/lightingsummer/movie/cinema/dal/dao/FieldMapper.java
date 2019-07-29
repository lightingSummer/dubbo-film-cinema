package club.lightingsummer.movie.cinema.dal.dao;

import club.lightingsummer.movie.cinema.api.po.Field;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FieldMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Field record);

    int insertSelective(Field record);

    Field selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);
}