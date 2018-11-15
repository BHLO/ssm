package cn.itcast.util;

import java.util.List;

public class PageBean<T> {
    //每页条数
    private Integer pageSize;
    //当前页
    private Integer pageNum;
    //总页数
    private Integer totalPage;
    //总条数
    private Integer totalCount;
    //分页的数据集合
    private List<T> list;

    @Override
    public String toString() {
        return "PageBean{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageBean(Integer pageSize, Integer pageNum, Integer totalPage, Integer totalCount, List<T> list) {

        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.list = list;
    }

    public PageBean() {

    }
}
