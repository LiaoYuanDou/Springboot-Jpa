package com.springboot.study.utils.restultful;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Data
 * @Author: XX
 * @Date: 2018/7/31 13:47
 * @Description: 返回结果数据层类
 */
public class Data implements Serializable {
    
	/**
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 3211432463066142879L;
	private List<?> dataList;
    private int totalCount;
    private int currentPage;
    private int pageSize;

    public Data() {
    }

    public Data(List<?> dataList) {
        this.dataList = dataList;
        this.totalCount = 0;
        this.currentPage = 0;
        this.pageSize = 0;
    }

    public Data(List<?> dataList, int totalCount, int currentPage, int pageSize) {
        this.dataList = dataList;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Data{" +
                "dataList=" + dataList +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }

}
