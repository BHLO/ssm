package cn.itcast.service;

import cn.itcast.domain.Orders;
import com.github.pagehelper.PageInfo;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 12:01
 * @Description:
 */
public interface OrderService {

    public PageInfo<Orders> findAllOrders(Integer pageNum, Integer pageSize);
}
