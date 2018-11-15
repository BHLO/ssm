package cn.itcast.service.impl;

import cn.itcast.dao.OrdersDao;
import cn.itcast.domain.Orders;
import cn.itcast.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 12:01
 * @Description:
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersDao ordersDao;

    public PageInfo<Orders> findAllOrders(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersDao.findAllOrders();
        PageInfo<Orders> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
