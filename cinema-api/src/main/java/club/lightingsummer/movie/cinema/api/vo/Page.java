package club.lightingsummer.movie.cinema.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author     ：lightingSummer
 * @date       ：2019/7/29 0029
 * @description：
 */
@Data
public class Page<T> implements Serializable {
    private Integer nowPage;
    private Integer totalPage;
    private List<T> data;

    private Page() {
    }

    public Page(int nowPage) {
        this.nowPage = nowPage;
        this.totalPage = 0;
    }
}
