package cn.itcast.controller;

import cn.itcast.domain.Orders;
import cn.itcast.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 12:02
 * @Description:
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAllOrder")
    public String findAllOrders(Model model,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "3") Integer pageSize) {
        int i=1/0;
        PageInfo<Orders> pageInfo = orderService.findAllOrders(pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "order/orderList";
    }
}
