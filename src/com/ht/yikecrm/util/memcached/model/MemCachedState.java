package com.ht.yikecrm.util.memcached.model;

import java.io.Serializable;

import com.ht.yikecrm.model.IModel;

/**
 * memcached属性类
 * 
 * @author 陈元元
 * 
 */
public class MemCachedState implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -393068923890406741L;

	private String id;
	// 分配的内存总大小
	private Long limitMaxbytes;

	// 当前存储占用的字节数
	private Long bytes;

	// 读取总字节数 
	private Long bytesRead;

	// 发送总字节数
	private Long bytesWritten;

	// flush命令请求次数
	private Long cmdFlush;

	// get命令请求次数
	private Long cmdGet;

	// set命令请求次数
	private Long cmdSet;

	// 当前连接数量
	private Long currConnections;

	// 分配的连接结构数
	private Long connectionStructures;

	// 启动以来存储的数据总数
	private Long totalItems;

	// 当前存储的数据总数
	private Long currItems;

	// delete命令命中次数
	private Long deleteHist;

	// delete命令未命中次数
	private Long deleteMisses;

	// get命令命中次数
	private Long getHist;

	// get命令未命中次数
	private Long getMisses;

	// 服务器已运行秒数
	private Long uptime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getLimitMaxbytes() {
		return limitMaxbytes;
	}

	public void setLimitMaxbytes(Long limitMaxbytes) {
		this.limitMaxbytes = limitMaxbytes;
	}

	public Long getBytes() {
		return bytes;
	}

	public void setBytes(Long bytes) {
		this.bytes = bytes;
	}

	public Long getBytesRead() {
		return bytesRead;
	}

	public void setBytesRead(Long bytesRead) {
		this.bytesRead = bytesRead;
	}

	public Long getBytesWritten() {
		return bytesWritten;
	}

	public void setBytesWritten(Long bytesWritten) {
		this.bytesWritten = bytesWritten;
	}

	public Long getCmdFlush() {
		return cmdFlush;
	}

	public void setCmdFlush(Long cmdFlush) {
		this.cmdFlush = cmdFlush;
	}

	public Long getCmdGet() {
		return cmdGet;
	}

	public void setCmdGet(Long cmdGet) {
		this.cmdGet = cmdGet;
	}

	public Long getCmdSet() {
		return cmdSet;
	}

	public void setCmdSet(Long cmdSet) {
		this.cmdSet = cmdSet;
	}

	public Long getCurrConnections() {
		return currConnections;
	}

	public void setCurrConnections(Long currConnections) {
		this.currConnections = currConnections;
	}

	public Long getConnectionStructures() {
		return connectionStructures;
	}

	public void setConnectionStructures(Long connectionStructures) {
		this.connectionStructures = connectionStructures;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Long getCurrItems() {
		return currItems;
	}

	public void setCurrItems(Long currItems) {
		this.currItems = currItems;
	}

	public Long getDeleteHist() {
		return deleteHist;
	}

	public void setDeleteHist(Long deleteHist) {
		this.deleteHist = deleteHist;
	}

	public Long getDeleteMisses() {
		return deleteMisses;
	}

	public void setDeleteMisses(Long deleteMisses) {
		this.deleteMisses = deleteMisses;
	}

	public Long getGetHist() {
		return getHist;
	}

	public void setGetHist(Long getHist) {
		this.getHist = getHist;
	}

	public Long getGetMisses() {
		return getMisses;
	}

	public void setGetMisses(Long getMisses) {
		this.getMisses = getMisses;
	}

	public Long getUptime() {
		return uptime;
	}

	public void setUptime(Long uptime) {
		this.uptime = uptime;
	}

}
