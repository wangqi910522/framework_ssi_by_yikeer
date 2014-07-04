package com.ht.yikecrm.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 分页显示数据类
 * @author HongToo
 *
 */
public class DojoPage {
//使用前先初始化总纪录数total
	protected static final Log LOG = LogFactory.getLog(DojoPage.class);
	/**
	 * 纪录总条数
	 */
	private int total;
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	/**
	 * 每页显示数据量
	 */
	private int pageSize = 15;
	/**
	 * 总页数
	 */
	private int pageTotal = 1;
	
	//读取的开始位置以0为开始
	private int start=0;
	//读取的结束位置
	private int end=0;
	
	public int getStart() {
		return start;
	}	

	public int getEnd() {
		return end;
	}
	
	public void offSetRange(int offSet){
		start+=offSet;
		end+=offSet;
		if(start<0)start=0;
		if(end<0)end=0;
	}
	

	/**
	 * 显示范围
	 * @return
	 */
	public void setRange(String range){
		if(range!=null){
			range=range.replace("items=", "");
			String[] ra=range.split("-");
			if(ra.length==2){
				start=Integer.parseInt(ra[0]);
				end=Integer.parseInt(ra[1]);
				pageSize=end-start+1;
				currentPage=start/pageSize+1;
			}else{
				LOG.debug("Request json items range number format error.");
			}
		}else{
			LOG.debug("Request json items range is null.");
		}
	}
	
	public int getPageTotal() {
		int pageTotal = total / pageSize;
		if (total % pageSize == 0) {
			return pageTotal;
		} else {
			return pageTotal + 1;
		}
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getCurrentPage() {
		if(currentPage==0)currentPage=1;
		return pageSize*(currentPage-1);
	}
	
	public int getViweCurrentPage(){
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		if (currentPage > getPageTotal()) {
			this.currentPage = getPageTotal();
		}
		else {
			if(currentPage <= 0){
				currentPage = 1;
			}else{
				this.currentPage = currentPage;
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		pageTotal = total / pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
