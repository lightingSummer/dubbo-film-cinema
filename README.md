# dubbo-movie-cinema
film-cinema模块
影院模块<br>

## 用到的技术及实现主要功能<br>
* 使用dubbo提供cinema信息各种查询服务，使用zookeeper做注册中心，用于服务注册及调用<br>
* dubbo特性使用：设置缓存模式lru,设置accepts最大10,超出直接返回错误，设置最大并发数量10
* 使用springboot作为后端主要框架，aop配置日志<br>
* 使用mysql5.7作为数据库存储，mybatis做查询，mybatis-generator生成xml映射，pagehelper做分页<br>

## api列表<br>

* 影院信息API，用于获取影院信息接口<br>
```java
public interface CinemaInfoAPI {
    // 根据CinemaQueryVO，查询影院列表
    Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO);
    // 根据影院id，获取影院信息
    CinemaInfoVO getCinemaInfoById(int cinemaId);
    // 获取影院品牌列表
    List<BrandVO> getBrands(int brandId);
    // 获取影院区域列表
    List<AreaVO> getAreas(int areaId);
    // 获取影厅类型列表
    List<HallTypeVO> getHallTypes(int hallType);
    // 根据影院编号获取放映场次信息
    List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId);
    // 根据放映场次id获取放映信息
    HallInfoVO getFilmFieldInfo(int fieldId);
    // 根据电影编号获取对应的电影信息
    FilmInfoVO getFilmInfoByFieldId(int fieldId);
}
```
## 数据表ddl<br>

```sql
CREATE TABLE `tb_area_dict` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';
```
```sql
CREATE TABLE `tb_brand_dict` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='品牌信息表';
```
```sql
CREATE TABLE `tb_cinema` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_name` varchar(50) DEFAULT NULL COMMENT '影院名称',
  `cinema_phone` varchar(50) DEFAULT NULL COMMENT '影院电话',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌编号',
  `area_id` int(11) DEFAULT NULL COMMENT '地域编号',
  `hall_ids` varchar(200) DEFAULT NULL COMMENT '包含的影厅类型,以#作为分割',
  `img_address` varchar(500) DEFAULT NULL COMMENT '影院图片地址',
  `cinema_address` varchar(200) DEFAULT NULL COMMENT '影院地址',
  `minimum_price` int(11) DEFAULT '0' COMMENT '最低票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影院信息表';
```
```sql
CREATE TABLE `tb_field` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `begin_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `hall_id` int(11) DEFAULT NULL COMMENT '放映厅类型编号',
  `hall_name` varchar(200) DEFAULT NULL COMMENT '放映厅名称',
  `price` int(11) DEFAULT NULL COMMENT '票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='放映场次表';
```
```sql
CREATE TABLE `tb_hall_dict` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `seat_address` varchar(200) DEFAULT NULL COMMENT '座位文件存放地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';
```
```sql
CREATE TABLE `tb_hall_film_info` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `film_name` varchar(50) DEFAULT NULL COMMENT '电影名称',
  `film_length` varchar(50) DEFAULT NULL COMMENT '电影时长',
  `film_cats` varchar(200) DEFAULT NULL COMMENT '电影类型',
  `film_language` varchar(50) DEFAULT NULL COMMENT '电影语言',
  `actors` varchar(200) DEFAULT NULL COMMENT '演员列表',
  `img_address` varchar(500) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影厅电影信息表';
```
