package com.hdu.cms.common.HibernateUtilExtentions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageBean<T> implements Serializable {
	/**
	 * @date 2016年5月24日-下午7:41:38
	 * @author JetWang
	 */
	private static final long serialVersionUID = 1L;
	public final static int DEFAULT_PAGE_SIZE = 24;
	private int pageSize = DEFAULT_PAGE_SIZE;// 每页记录数

	private List<T> rows;// 已分页好的结果集

	private int total;// 总结果数

	private int totalPage = 0;// 总页数
	private int page = 0;// 当前页
	private boolean isFirstPage;// 是否为第一页
	private boolean isLastPage;// 是否为最后一页
	private boolean hasPreviousPage;// 是否有前一页
	private boolean hasNextPage;// 是否有下一页
	public PageBean() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}

	/**
	 * 计算当前页开始记录
	 * 
	 * @param pageSize 每页记录数
	 * @param currentPage 当前第几页
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize, final int currentPage) {
		int page = currentPage;
		if (currentPage == 0) {
			page = 1;
		}
		final int offset = pageSize * (page - 1);
		return offset;
	}
	public PageBean(int total, int page, int pageSize, List<T> rows) {
		this.total = total;
		this.pageSize = pageSize;
		this.page = page;
		this.totalPage = total % pageSize == 0?total / pageSize:total / pageSize + 1;
		
		if (this.page > this.totalPage) {
			this.page = this.totalPage;
		}
		this.rows = rows;
		init();
	}
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasPreviousPage();
		this.hasNextPage = isHasNextPage();
	}
	public boolean isFirstPage() {
		return page == 1; // 如是当前页是第1页
	}
	
	public boolean isLastPage() {
		return page == totalPage; // 如果当前页是最后一页
	}
	
	public boolean isHasPreviousPage() {
		return page != 1; // 只要当前页不是第1页
	}
	
	public boolean isHasNextPage() {
		return page != totalPage; // 只要当前页不是最后1页
	}
	public List<T> getRows() {
		return rows;
	}
	
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getPage() {
		if (this.totalPage < this.page) {
			this.page = this.totalPage;
		}
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
