package club.lightingsummer.movie.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description： 影院查询入参封装
 */
@Data
public class CinemaQueryVO implements Serializable {
    //影院编号	默认为99，全部
    private int brandId = 99;
    //影厅类型	默认为99，全部
    private int hallType = 99;
    //行政区编号	默认为99，全部
    private int districtId = 99;
    //每页条数	默认为12条
    private int pageSize = 12;
    //当前页数	默认为第1页
    private int nowPage = 1;

}
