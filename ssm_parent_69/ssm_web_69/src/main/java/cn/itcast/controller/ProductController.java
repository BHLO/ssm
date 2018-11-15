package cn.itcast.controller;

import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.util.DateUtil;
import cn.itcast.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 09:12
 * @Description:
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //自定义参数绑定
    @InitBinder
    public void initBind(WebDataBinder binder) {
        //参数1指定需要转换的类型
        binder.registerCustomEditor(Date.class, new PropertiesEditor() {
            @Override
            public void setAsText(String dateStr) throws IllegalArgumentException {
                //接受浏览器传递的字符串转换成日期后
                Date date = DateUtil.parseStrToDate(dateStr);
                //字符串转换成日期后 赋值属性
                setValue(date);
            }
        });
    }

    /**
     * 功能描述:查询产品的列表
     *
     * @param: [model]
     * @return: java.lang.String
     * @auther: wyan
     * @date: 2018/11/7 10:10
     */
    @RequestMapping("/findAllProduct")
    public String findAllProduct(Model model,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "3") Integer pageSize) {
        PageBean<Product> pb = productService.findAllProduct(pageNum, pageSize);
        model.addAttribute("pb", pb);
        //后台获取框架存取的用户对象
       SecurityContext context = SecurityContextHolder.getContext();
       User user = (User) context.getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        return "product/productList";

    }

    //接受请求跳转添加页面
    @RequestMapping("/addProductUI")
    public String addProductUI() {

        return "product/productAdd";
    }

    /**
     * 功能描述: 接受产品对象保存到数据库
     *
     * @param: [product]
     * @return: java.lang.String
     * @auther: wyan
     * @date: 2018/11/7 10:16
     */
    @RequestMapping("/addProduct")
    public String addProduct(Product product) {

        //调用servcie存储产品
        productService.saveProduct(product);
        //保存成功后跳转查询列表
        //重定向 redirect: 地址栏变 数据库丢失 两次请求
        //转发   forward:  地址不变 数据不丢   一次请求
        return "redirect:/product/findAllProduct";
    }

    /*
     * 接受产品id
     * 查询产品跳转页面展示
     * */
    @RequestMapping("/updateProductUI")
    public String updateProductUI(Integer productId, Model model) {

        //通过id得到产品对象
        Product product = productService.findProductById(productId);

        model.addAttribute("product", product);

        return "product/productUpdate";
    }

    /**
     * 功能描述:接受传递的产品对象更新数据库数据
     *
     * @param: [product]
     * @return: java.lang.String
     * @auther: wyan
     * @date: 2018/11/7 11:17
     */
    @RequestMapping("/updateProduct")
    public String updateProduct(Product product) {
        //更新产品数据
        productService.updateProduct(product);
        //更新成功跳转查询
        return "redirect:/product/findAllProduct";
    }

    /**
     * 功能描述:根据产品id删除产品的对象
     *
     * @param: [productId]
     * @return: java.lang.String
     * @auther: wyan
     * @date: 2018/11/7 11:44
     */
    @RequestMapping("/deleteProductById")
    public String deleteProductById(Integer productId) {

        productService.deleteProductById(productId);

        //查询列表展示
        return "redirect:/product/findAllProduct";
    }

}
