package cn.itcast.dao;

import cn.itcast.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 08:57
 * @Description: 产品持久接口
 */
public interface ProductDao {

    //查询所有的产品列表
    @Select("select * from ( " +
            "select rownum  r ,p.* from product p where rownum<=#{endIndex} ) t where t.r > #{startIndex}")
    List<Product> findAllProduct(@Param("startIndex")Integer startIndex,
                                 @Param("endIndex")Integer endIndex);

    @Insert("insert into product values(common_sequence.nextval,#{productNum}," +
            "#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    @Select("select * from product where id = #{productId}")
    Product findProductById(Integer productId);

    @Update("update product set productName=#{productName},cityName=#{cityName}," +
            " departureTime=#{departureTime},productPrice=#{productPrice}," +
            "productDesc=#{productDesc},productStatus=#{productStatus} where id = #{id} ")
    void updateProduct(Product product);

    @Delete("delete from product where id = #{productId}")
    void deleteProductById(Integer productId);

    @Select("select count(1) from product")
    Integer findTotalCount();
}
