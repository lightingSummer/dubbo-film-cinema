package club.lightingsummer.movie.cinema.dal.dao;

import club.lightingsummer.movie.cinema.api.po.BrandDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BrandDictMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(BrandDict record);

    int insertSelective(BrandDict record);

    BrandDict selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(BrandDict record);

    int updateByPrimaryKey(BrandDict record);
}