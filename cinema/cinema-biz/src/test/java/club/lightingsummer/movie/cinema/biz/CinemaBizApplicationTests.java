package club.lightingsummer.movie.cinema.biz;

import club.lightingsummer.movie.cinema.api.api.CinemaInfoApi;
import club.lightingsummer.movie.cinema.api.vo.CinemaQueryVO;
import club.lightingsummer.movie.cinema.dal.dao.BrandDictMapper;
import club.lightingsummer.movie.cinema.dal.dao.CinemaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaBizApplicationTests {

    @Autowired
    private CinemaMapper cinemaMapper;

    @Autowired
    private CinemaInfoApi cinemaInfoApi;

    @Autowired
    private BrandDictMapper brandDictMapper;

    @Test
    public void contextLoads() {
        System.out.println(cinemaInfoApi.getCinemaInfoById(2));
    }

    @Test
    public void BrandMapperTest() {
        System.out.println(brandDictMapper.selectByPrimaryKey(30));
    }

    @Test
    public void CinemaInfoApiBrandDictTest() {
        System.out.println(cinemaInfoApi.getBrands(20));
    }

    @Test
    public void CinemaInfoApiFieldInfoTest() {
        System.out.println(cinemaInfoApi.getFilmFieldInfo(1));
    }

    @Test
    public void CinemaInfoApiAreaDictTest() {
        System.out.println(cinemaInfoApi.getAreas(14));
        System.out.println(cinemaInfoApi.getAreas(50));
    }

    @Test
    public void CinemaInfoApiFilmInfoTest() {
        System.out.println(cinemaInfoApi.getFilmInfoByFieldId(5));
        System.out.println(cinemaInfoApi.getFilmInfoByFieldId(7));
    }

    @Test
    public void CinemaInfoApiFieldTest() {
        System.out.println(cinemaInfoApi.getFilmInfoByCinemaId(1));
        System.out.println(cinemaInfoApi.getFilmInfoByCinemaId(3));
    }

    @Test
    public void CinemaInfoApiHallDictTest() {
        System.out.println(cinemaInfoApi.getHallTypes(13));
        System.out.println(cinemaInfoApi.getHallTypes(14));
    }

    @Test
    public void CinemaInfoApiTest() {
        CinemaQueryVO cinemaQueryVO = new CinemaQueryVO();
        cinemaQueryVO.setNowPage(1);
        cinemaQueryVO.setPageSize(5);
        cinemaQueryVO.setHallType(2);
        System.out.println(cinemaInfoApi.getCinemas(cinemaQueryVO));
    }

    @Test
    public void CinemaMapperTest() {
        System.out.println(cinemaMapper.selectCinemaByCondition(99, null, 99));
        System.out.println(cinemaMapper.selectCinemaByCondition(1, "%#5%#", 99));
        System.out.println(cinemaMapper.selectCinemaByCondition(3, "%#9%#", 7));
    }
}
