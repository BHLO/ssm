package cn.itcast.service.impl;

import cn.itcast.dao.ProductDao;
import cn.itcast.domain.Product;
import cn.itcast.service.ProductService;
import cn.itcast.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/7 09:05
 * @Description: 产品业务类的实现
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public PageBean<Product> findAllProduct(Integer pageNum, Integer pageSize) {
        PageBean<Product> pb = new PageBean<>();
        pb.setPageNum(pageNum);
        pb.setPageSize(pageSize);
        //调用dao得到分页使用的总记录数
        Integer totalCount = productDao.findTotalCount();
        pb.setTotalCount(totalCount);
        //计算总页数
        Integer totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);
        //查询分页结果集合
        Integer startIndex = (pageNum - 1) * pageSize;
        Integer endIndex = pageNum * pageSize;
        List<Product> list = productDao.findAllProduct(startIndex, endIndex);
        pb.setList(list);
        System.out.println(list);
        return pb;
    }

    //保存产品
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public Product findProductById(Integer productId) {

        return productDao.findProductById(productId);
    }

    @Override
    public void updateProduct(Product product) {

        productDao.updateProduct(product);
    }

    @Override
    public void deleteProductById(Integer productId) {

        productDao.deleteProductById(productId);
    }
}
