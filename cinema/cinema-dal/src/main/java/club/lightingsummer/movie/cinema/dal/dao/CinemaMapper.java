package club.lightingsummer.movie.cinema.dal.dao;

import club.lightingsummer.movie.cinema.api.po.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CinemaMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);
}