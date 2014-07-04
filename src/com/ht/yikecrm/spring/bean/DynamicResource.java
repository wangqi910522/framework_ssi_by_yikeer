package com.ht.yikecrm.spring.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.springframework.core.io.Resource;

import com.ht.yikecrm.spring.bean.impl.DataSourceDynamicBean;
import com.ht.yikecrm.util.spring.bean.DynamicDataSourceUtil;

public class DynamicResource implements Resource {
	private List<DataSourceDynamicBean> dataSourceList;  
    
    public DynamicResource(List<DataSourceDynamicBean> dynamicBean){  
        this.dataSourceList = dynamicBean;  
    }  
    /* (non-Javadoc) 
     * @see org.springframework.core.io.InputStreamSource#getInputStream() 
     */  
    public InputStream getInputStream() throws IOException {  
        return new ByteArrayInputStream(DynamicDataSourceUtil.getXml(dataSourceList).getBytes("UTF-8"));  
    }
    
    /**
     * 以下方法不用实现。
     */
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		return null;
	}
	@Override
	public boolean exists() {
		return false;
	}
	@Override
	public String getDescription() {
		return null;
	}
	@Override
	public File getFile() throws IOException {
		return null;
	}
	@Override
	public String getFilename() {
		return null;
	}
	@Override
	public URI getURI() throws IOException {
		return null;
	}
	@Override
	public URL getURL() throws IOException {
		return null;
	}
	@Override
	public boolean isOpen() {
		return false;
	}
	@Override
	public boolean isReadable() {
		return false;
	}
	@Override
	public long lastModified() throws IOException {
		return 0;
	}
	@Override
	public long contentLength() throws IOException {
		return 0;
	}  

    
}
