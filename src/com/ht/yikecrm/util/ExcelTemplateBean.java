package com.ht.yikecrm.util;

import java.util.List;
import java.util.Map;

import com.ht.yikecrm.model.IModel;

/**
 * execl模板文件bean
 * 
 * @author Administrator
 * 
 */
public class ExcelTemplateBean<T extends IModel> {
	/**
	 * 模板文件绝对路径
	 */
	private String filePath;
	/**
	 * 生成报表文件名称
	 */
	private String fileName;

	/**
	 * 报表标题
	 */
	private String title;
	/**
	 * 报表标题列索引
	 */
	private int titleXColIndex;
	/**
	 * 报表标题行索引
	 */
	private int titleYRowIndex;
	/**
	 * 报表生成者
	 */
	private String buildBy;
	/**
	 * 生成者列索引
	 */
	private int buildByXColIndex;
	/**
	 * 生成者行索引
	 */
	private int buildByYRowIndex;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 公司列索引
	 */
	private int companyXColIndex;
	/**
	 * 公司行索引
	 */
	private int companyYRowIndex;
	/**
	 * 生成时间
	 */
	private String createTime;
	/**
	 * 生成时间列索引
	 */
	private int createTimeXColIndex;
	/**
	 * 生成时间行索引
	 */
	private int createTimeYRowIndex;
	/**
	 * 筛选依据：数据所有者
	 */
	private String strOwner;
	/**
	 * 筛选依据：所有者列索引
	 */
	private int strOwnerXColIndex;
	/**
	 * 筛选依据：所有者行索引
	 */
	private int strOwnerYRowIndex;

	/**
	 * 筛选依据：数据字段 {key:val}...
	 */
	private Map<String, String> dataFields;

	/**
	 * 筛选依据：数据字段列索引
	 */
	private int dataFieldsXColIndex;
	/**
	 * 筛选依据：数据字段行索引
	 */
	private int dataFieldsYRowIndex;

	/**
	 * 其他报表信息及索引
	 * 
	 * [{text:[textFormatStr,val,xIndex,yIndex]}] ,textFormatStr="总计 ( %1$s个记录) "
	 */
	private List<Map<String, List<Object>>> otherInfoList;

	/**
	 * 起始行索引,（startRowIndex-1）为模板行索引
	 */
	private int startRowIndex = 16;

	/**
	 * 起始列索引
	 */
	private int startColIndex = 0;

	/**
	 * 最大列索引
	 */
	private int maxColIndex;

	/**
	 * 自定义数据集
	 */
	private List<Map<String, Object>> mapList;
	
	/**
	 * 数据集
	 */
	private List<T> modelList;

	/**
	 * 报表列表数据结构 [{field:val}...]
	 */
	private List<Map<String, ICallBack>> structList;
	/**
	 * 模板文件绝对路径
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 模板文件绝对路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 生成报表文件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 生成报表文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 报表标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 报表标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 报表标题列索引
	 */
	public int getTitleXColIndex() {
		return titleXColIndex;
	}
	/**
	 * 报表标题列索引
	 */
	public void setTitleXColIndex(int titleXColIndex) {
		this.titleXColIndex = titleXColIndex;
	}
	/**
	 * 报表标题列索引
	 */
	public int getTitleYRowIndex() {
		return titleYRowIndex;
	}
	/**
	 * 报表标题列索引
	 */
	public void setTitleYRowIndex(int titleYRowIndex) {
		this.titleYRowIndex = titleYRowIndex;
	}
	/**
	 * 报表生成者
	 */
	public String getBuildBy() {
		return buildBy;
	}
	/**
	 * 报表生成者
	 */
	public void setBuildBy(String buildBy) {
		this.buildBy = buildBy;
	}

	public int getBuildByXColIndex() {
		return buildByXColIndex;
	}

	public void setBuildByXColIndex(int buildByXColIndex) {
		this.buildByXColIndex = buildByXColIndex;
	}

	public int getBuildByYRowIndex() {
		return buildByYRowIndex;
	}

	public void setBuildByYRowIndex(int buildByYRowIndex) {
		this.buildByYRowIndex = buildByYRowIndex;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCompanyXColIndex() {
		return companyXColIndex;
	}

	public void setCompanyXColIndex(int companyXColIndex) {
		this.companyXColIndex = companyXColIndex;
	}

	public int getCompanyYRowIndex() {
		return companyYRowIndex;
	}

	public void setCompanyYRowIndex(int companyYRowIndex) {
		this.companyYRowIndex = companyYRowIndex;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCreateTimeXColIndex() {
		return createTimeXColIndex;
	}

	public void setCreateTimeXColIndex(int createTimeXColIndex) {
		this.createTimeXColIndex = createTimeXColIndex;
	}

	public int getCreateTimeYRowIndex() {
		return createTimeYRowIndex;
	}

	public void setCreateTimeYRowIndex(int createTimeYRowIndex) {
		this.createTimeYRowIndex = createTimeYRowIndex;
	}

	public String getStrOwner() {
		return strOwner;
	}

	public void setStrOwner(String strOwner) {
		this.strOwner = strOwner;
	}

	public int getStrOwnerXColIndex() {
		return strOwnerXColIndex;
	}

	public void setStrOwnerXColIndex(int strOwnerXColIndex) {
		this.strOwnerXColIndex = strOwnerXColIndex;
	}

	public int getStrOwnerYRowIndex() {
		return strOwnerYRowIndex;
	}

	public void setStrOwnerYRowIndex(int strOwnerYRowIndex) {
		this.strOwnerYRowIndex = strOwnerYRowIndex;
	}

	public List<Map<String, List<Object>>> getOtherInfoList() {
		return otherInfoList;
	}

	public void setOtherInfoList(List<Map<String, List<Object>>> otherInfoList) {
		this.otherInfoList = otherInfoList;
	}

	public int getStartRowIndex() {
		return startRowIndex;
	}

	public void setStartRowIndex(int startRowIndex) {
		this.startRowIndex = startRowIndex;
	}

	public int getStartColIndex() {
		return startColIndex;
	}

	public void setStartColIndex(int startColIndex) {
		this.startColIndex = startColIndex;
	}

	public int getMaxColIndex() {
		return maxColIndex;
	}

	public void setMaxColIndex(int maxColIndex) {
		this.maxColIndex = maxColIndex;
	}

	public List<Map<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}

	public List<T> getModelList() {
		return modelList;
	}

	public void setModelList(List<T> modelList) {
		this.modelList = modelList;
	}

	public List<Map<String, ICallBack>> getStructList() {
		return structList;
	}

	public void setStructList(List<Map<String, ICallBack>> structList) {
		this.structList = structList;
	}

	public Map<String, String> getDataFields() {
		return dataFields;
	}

	public void setDataFields(Map<String, String> dataFields) {
		this.dataFields = dataFields;
	}

	public int getDataFieldsXColIndex() {
		return dataFieldsXColIndex;
	}

	public int getDataFieldsYRowIndex() {
		return dataFieldsYRowIndex;
	}

	public void setDataFieldsXColIndex(int dataFieldsXColIndex) {
		this.dataFieldsXColIndex = dataFieldsXColIndex;
	}

	public void setDataFieldsYRowIndex(int dataFieldsYRowIndex) {
		this.dataFieldsYRowIndex = dataFieldsYRowIndex;
	}
}
