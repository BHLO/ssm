package cn.itcast.dao;

import cn.itcast.domain.Orders;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 12:01
 * @Description:
 */
public interface OrdersDao {

    //查询所有的订单列表数据展示订单和产品名称
    @Select("select * from orders")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "productId",property = "product",
                    one=@One(select="cn.itcast.dao.ProductDao.findProductById")),
    })
    List<Orders> findAllOrders();
}
