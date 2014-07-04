package com.ht.yikecrm.util.webConfig;


public class WebConfigUtil {

	private String messageUrl;
	private String yikeerUrl;
	
	private String httpsConnt;

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}

	public String getYikeerUrl() {
		return yikeerUrl;
	}

	public void setYikeerUrl(String yikeerUrl) {
		this.yikeerUrl = yikeerUrl;
	}

	public String getHttpsConnt() {
		return httpsConnt;
	}

	public void setHttpsConnt(String httpsConnt) {
		this.httpsConnt = httpsConnt;
	}
	
	public   boolean judgeHttps() {
		return "true".equals(httpsConnt) ?  true :false;
		
	}

//	private static final String WEBCONFIGNAME ="/webConfig.properties";
//	private static Properties properties ;
//	
//	private static Properties getProperties()throws Exception{
//		properties = null;
//		if(properties == null){
//			InputStream in = WebConfigUtil.class.getResourceAsStream(WEBCONFIGNAME);
//			properties = new Properties();
//			properties.load(in);
//		}
//		return properties;
//	}
//	
//	/**
//	 * 判断当前是否是https 请求
//	 * @return
//	 * @throws Exception
//	 */
//	public static boolean judgeHttps() throws Exception{
//		String blStr = getProperties().getProperty("ishttps");
//		if(!StringUtils.isNullOrEmpty(blStr) && blStr .equals("true")){
//			return true;
//		}else {
//			return false;
//		}
//	}
//	
//	
//	public static String getYikeerUrl() throws Exception{
//		return getProperties().getProperty("global_yikeer_url");
//	}
//	
//	
//	public static void main(String[] args) {
//		try {
//			File directory = new File("");
////			 System.out.println(directory.getCanonicalPath());//获取标准的路径   
////			    System.out.println(directory.getAbsolutePath());//获取绝对路径  
////			    System.out.println(WebConfigUtil.class.getResource("").getPath());
//////			System.out.println(System.getProperty("user.dir"));
//			InputStream in = new FileInputStream(WebConfigUtil.class.getResource("")+WEBCONFIGNAME);
//			System.out.println(WebConfigUtil.class.getResource("")+WEBCONFIGNAME);
//			properties = new Properties();
//			properties.load(in);
//			System.out.println(properties.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
