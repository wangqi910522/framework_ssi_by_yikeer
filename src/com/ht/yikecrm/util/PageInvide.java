package com.ht.yikecrm.util;

import java.util.Collection;
import java.util.List;

/**
 * 用于分页查询的类
 * 
 * @param itemList
 *            : Collection 所有记录的集合
 * @param page
 *            :int 要查询的页数
 * @param eachPageNum
 *            :int 每页显示的记录数
 * 
 *            使用说明:在页面中使用 {pageInvideInfo.itemBegin}表示第一条记录
 *            ${PageInvideInfo.itemEnd}表示最后一条记录 ${PageInvideInfo.totalPages}为总页数
 *            ${PageInvideInfo.currentPage}为当前页码
 *            ${PageInvideInfo.pageItems}为每页显示的记录的条数
 */
public class PageInvide {

	/**
	 * 总页数
	 */
	private int _totalpages;

	/**
	 * 当前页数 从1开始
	 */
	private int _currentPage;

	/**
	 * 每页记录条数
	 */
	private int _pageSize = 10;

	/**
	 * 总记录条数
	 */
	private int _itemsCount;

	/**
	 * 分页集合
	 */
	private Collection<?> _itemsList;

	/**
	 * 字段名
	 */
	private Object[] beanName;

	
	
	/**
	 * 其他信息(自己需要什么 传什么，自己处理)
	 * @return
	 */
	private String otherInfo;
	
	
	
	
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public Object[] getBeanName() {
		return beanName;
	}

	public void setBeanName(Object[] beanName) {
		this.beanName = beanName;
	}

	/**
	 * 自定义视图展示结构
	 */
	private List<Structure> structureList;

	public List<Structure> getStructureList() {
		return structureList;
	}

	public void setStructureList(List<Structure> structureList) {
		this.structureList = structureList;
	}

	/**
	 * 构造函数
	 * 
	 * @param items
	 * @param itemsCount
	 * @param currentPage
	 */
	public PageInvide(Collection<?> items, int itemsCount, int currentPage) {
		_itemsList = items;
		_itemsCount = itemsCount;
		_currentPage = currentPage;
		_pageSize = 10;
		_totalpages = getTotalPages();
	}

	/**
	 * 构造函数
	 * 
	 * @param items
	 * @param itemsCount
	 * @param page
	 * @param pageSize
	 */
	public PageInvide(Collection<?> items, int itemsCount, int page,
			int pageSize) {
		_itemsList = items;
		_itemsCount = itemsCount;
		_pageSize = pageSize;
		_currentPage = page;
		_pageSize = pageSize;
		_totalpages = getTotalPages();
	}

	/**
	 * 获得数据长度
	 * 
	 * @return
	 */
	public int getItemLength() {
		return getItemEnd() - getItemBegin() + 1;
	}

	/**
	 * 获得总页数
	 * 
	 * @return
	 */
	public int getTotalPages() {
		if (this._totalpages == 0) {
			this._totalpages = 1;
		} else {
			this._totalpages = (_itemsCount % _pageSize) == 0 ? _itemsCount
					/ _pageSize : _itemsCount / _pageSize + 1;
		}
		return this._totalpages;
	}

	/**
	 * 获得总条数
	 * 
	 * @return
	 */
	public int getItemsCount() {
		return _itemsCount;
	}

	/**
	 * 设置当前页数
	 * 
	 * @return
	 */
	public int getCurrentPage() {
		return _currentPage;
	}

	/**
	 * 获得前页第一条数
	 * 
	 * @return
	 */
	public int getItemBegin() {
		return _itemsCount == 0 ? 1 : (_currentPage - 1) * _pageSize + 1;
	}

	/**
	 * 获得当前页最后条数
	 * 
	 * @return
	 */
	public int getItemEnd() {
		if (_itemsCount == 0) {
			return 1;
		} else
			return (_currentPage * _pageSize >= _itemsCount) ? _itemsCount
					: _currentPage * _pageSize;
	}

	/**
	 * 获得当前集合内容
	 * 
	 * @return
	 */
	public List<?> getItemsList() {
		return (List<?>) this._itemsList;
	}

	/**
	 * 
	 * @param items
	 *            内容集合
	 * @param itemsCount
	 *            总记录数
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public static PageInvide getPageInvide(List<?> items, int itemsCount,
			int currentPage) {
		PageInvide page = new PageInvide(items, itemsCount, currentPage);
		return page;
	}

	/**
	 * 
	 * @param items
	 *            内容集合
	 * @param itemsCount
	 *            总记录数
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public static PageInvide getPageInvide(List<?> items, int itemsCount) {
		PageInvide page = new PageInvide(items, itemsCount, 1);
		return page;
	}

	/**
	 * 
	 * @param items
	 *            内容集合
	 * @param itemsCount
	 *            总记录数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示的条数
	 * @return
	 */
	public static PageInvide getPageInvide(List<?> items, int itemsCount,
			int currentPage, int pageSize) {
		PageInvide page = new PageInvide(items, itemsCount, currentPage,
				pageSize);
		return page;
	}

}
