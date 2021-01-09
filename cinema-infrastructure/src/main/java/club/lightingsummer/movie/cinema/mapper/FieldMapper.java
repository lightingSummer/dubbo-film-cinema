package club.lightingsummer.movie.cinema.mapper;

import club.lightingsummer.movie.cinema.po.Field;
import club.lightingsummer.movie.cinema.vo.FilmInfoVO;
import club.lightingsummer.movie.cinema.vo.HallInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FieldMapper {
    int deleteByPrimaryKey(Integer uuid);

    int insert(Field record);

    int insertSelective(Field record);

    Field selectByPrimaryKey(Integer uuid);

    int updateByPrimaryKeySelective(Field record);

    int updateByPrimaryKey(Field record);

    List<Field> selectFieldByCinemaIdAndFilmId(@Param("cinemaId") int cinemaId,
                                               @Param("filmId") int filmId);

    HallInfoVO selectFieldInfoByFieldId(@Param("fieldId") int fieldId);

    FilmInfoVO selectFilmInfoByFieldId(@Param("fieldId") int fieldId);
}