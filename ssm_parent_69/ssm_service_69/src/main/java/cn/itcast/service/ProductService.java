package cn.itcast.service;

import cn.itcast.domain.Product;
import cn.itcast.util.PageBean;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 09:04
 * @Description:
 */
public interface ProductService {

    //查询所有的产品列表
    public PageBean<Product> findAllProduct(Integer pageNum, Integer pageSize);

    void saveProduct(Product product);

    Product findProductById(Integer productId);

    void updateProduct(Product product);

    void deleteProductById(Integer productId);
}
