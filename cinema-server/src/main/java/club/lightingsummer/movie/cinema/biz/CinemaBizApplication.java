package club.lightingsummer.movie.cinema.biz;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "club.lightingsummer.movie.cinema")
@MapperScan("club.lightingsummer.movie.cinema.dal.dao")
@EnableDubboConfiguration
public class CinemaBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaBizApplication.class, args);
    }

}
