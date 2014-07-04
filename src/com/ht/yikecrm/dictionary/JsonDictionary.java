package com.ht.yikecrm.dictionary;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 相关字段值
 * @author HongToo
 *
 */
public class JsonDictionary {
	/**
	 * 记录有自定义字段模块的非持久化字段
	 * @return
	 */
//	public static JSONObject getNotPersistent(){
//		String jsonstring="{'CrmSalesLead':{'':''}," +
//				"'CrmCustomer':{'':''}," +
//				"'CrmLinkman':{'':''}," +
//				"'CrmBusiness':{'relateProduct':'relateProduct','quantity':'quantity','costPrice':'costPrice'}," +
//				"'CrmProduct':{'totalQuantity':'totalQuantity','money':'money','relateBusiness':'relateBusiness','isChoose':'isChoose'}," +
//				"'CrmOrder':{'':''}," +
//				"'CrmComplaint':{'':''}" +
//				"}";
//		JSONObject jobject = JSONObject.fromObject(jsonstring);
//		return jobject;
//	}
	/**
	 * 记录有自定义字段模块的非持久化字段
	 * @return
	 */
	public static Map<String,String[]> getNotPersistent(){
		Map<String,String[]> notPersistent = new HashMap<String, String[]>();
		notPersistent.put("CrmSalesLead",new String[0]);
		notPersistent.put("CrmCustomer",new String[0]);
		notPersistent.put("CrmLinkman",new String[0]);
		notPersistent.put("CrmBusiness",new String[]{"relateProduct","quantity","costPrice","actualCostPrice"});
		notPersistent.put("CrmProduct",new String[]{"totalQuantity","money","relateBusiness","isChoose"});
		notPersistent.put("CrmOrder",new String[]{"custID","orderNoStr","ourID"});
		notPersistent.put("CrmComplaint",new String[0]);
		return notPersistent;
	}

	
	public static JSONObject getJobject() {
		String jsonstring = "{'customerIndustry':{'1':'农业','2':'服装','3':'银行','4':'生物技术','5':'化学','6':'通讯','7':'建筑','8':'咨询','9':'教育'," +
										 "'10':'电子','11':'能源','12':'工程','13':'娱乐','14':'环境','15':'金融','16':'食品与饮料','17':'政府'," +
										 "'18':'卫生保健','19':'酒店','20':'保险','21':'机械','22':'制造业','23':'媒体','24':'非盈利事业'," +
										 "'24':'非盈利事业','25':'休闲娱乐','26':'零售','27':'航运','28':'技术','29':'其它'}," +
							"'businessStage':{'1':'初步接触','2':'需求分析','3':'展示引导','4':'方案/报价','5':'协商议价','6':'赢得客户','7':'丢失客户','8':'跟进中'}," +
							"'sourceValue':{'1':'电话来访','2':'客户介绍','3':'独立开发','4':'媒体宣传','5':'促销活动','6':'老客户','7':'代理商'," +
									  "'8':'公开招标','9':'互联网','10':'其它'}," +
							"'titleValue':{'1':'先生','2':'小姐','3':'女士','4':'博士','5':'教授'}," +
							"'stateValue':{'1':'已联系','2':'未处理','3':'合格','4':'不合格','5':'已转换'}," +
							"'customerType':{'1':'优质客户','2':'普通客户','3':'黑名单客户'}," +
							"'orderType':{'0':'进行中','1':'成功','2':'意外终止'}," +
							"'urgent':{'3':'非常紧急','1':'普通','2':'紧急'}," +
							"'dealResult':{'3':'未处理','2':'处理中','1':'处理完成'}," +
							"'spendTime':{'1':'一个小时','2':'两个小时','3':'半个工作日','4':'一个工作日','5':'两个工作日','6':'两个工作日以上'}," +							
							"'warnLevel':{'1':'低','2':'普通','3':'高'},"+
							"'hotinfo':{'1':'是','0':'否'},"+
							"'hotinfo1':{'1':'是','0':'否'},"+
							"'hotinfo2':{'1':'是','0':'否'},"+
							"'hotinfo3':{'1':'是','0':'否'},"+
							"'productType':{'1':'是','0':'否'},"+
							"'productState':{'1':'正常','0':'停售','2':'缺货'},"+
							"'Invoice':{'0':'否','1':'是','-1':'无需发票'},"+
							"'isWarn':{'1':'提醒','0':'不提醒'},"+
							"'isSendSMS':{'1':'是','0':'否'},"+
							"'isEventType':{'1':'否','2':'是'},"+
							"'entityTypeInfo':{'2':'#customer#','3':'#business#','5':'#order#'},"+
							"'linkTypeInfo':{'1':'#linkman#','2':'#salesLead#'},"+
							"'covertState':{'1':'未开始','2':'进行中','3':'已完成','4':'等待','5':'延迟'},"+
							"'complaintTime':{'0':'凌晨 00:00','30':'凌晨 00:30','60':'凌晨 1:00','90':'凌晨 1:30','120':'凌晨 2:00'"+
								",'150':'凌晨 2:30','180':'凌晨 3:00','210':'凌晨 3:30','240':'凌晨 4:00','270':'凌晨 4:30','300':'早上 5:00'"+
								",'330':'早上 5:30','360':'早上 6:00','390':'早上 6:30','420':'早上 7:00','450':'早上 7:30','480':'早上 8:00'"+
								",'510':'早上 8:30','540':'早上 9:00','570':'早上 9:30','600':'早上 10:00','630':'早上 10:30','660':'中午 11:00'"+
								",'690':'中午 11:30','720':'中午 12:00','750':'中午 12:30','780':'下午 13:00','810':'下午 13:30','840':'下午 14:00'"+
								",'870':'下午 14:30','900':'下午 15:00','930':'下午 15:30','960':'下午 16:00','990':'下午 16:30','1020':'傍晚 17:00'"+
								",'1050':'傍晚 17:30','1080':'傍晚 18:00','1110':'傍晚 18:30','1140':'晚上 19:00','1170':'晚上  19:30','1200':'晚上  20:00'"+
								",'1230':'晚上  20:30','1260':'晚上  21:00','1290':'晚上  21:30','1320':'晚上  22:00','1350':'晚上  22:30','1380':'晚上  23:00'"+
								",'1410':'晚上  23:30'},"+
							"'category':{'1':'产品投诉','2':'服务投诉','3':'客户意见','4':'其他','产品投诉':'产品投诉','客户意见':'客户意见'},"+
							"'covertWarnLevel':{'1':'低','2':'普通','3':'高'},"+
							"'categoryText':{'1':'产品投诉','2':'服务投诉','3':'客户意见','4':'其他'},"+
							"'recentContactType':{'1':'任务','2':'事件','3':'短信','4':'邮件'},"+
							"'reportDict':{'c':'#customer#','l':'#linkman#','b':'#business#','s':'#salesLead#','p':'#product#','pr':'#product#','o':'#order#','a':'#activitytask#,#activityevent#','cp':'#complaint#','r':'回款记录'}"+
							"}";
		JSONObject jobject = JSONObject.fromObject(jsonstring);
		return jobject;
	}
	
	public static boolean isExistsOption(String type,String value){
		boolean flag = false;
		Map<String,Map<String,String>> m = getJobject();
		for(Map.Entry<String, Map<String,String>> entry : m.entrySet()){
			String key = entry.getKey();
			if(type.equals(key)){
				for(Map.Entry<String, String> entry2 : entry.getValue().entrySet()){
					if(value.equals(entry2.getValue())){
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return flag;
	}
	
	public static String getKeyByValue(String type,String value){
		String result = null;
		boolean flag = false;
		Map<String,Map<String,String>> m = getJobject();
		for(Map.Entry<String, Map<String,String>> entry : m.entrySet()){
			String key = entry.getKey();
			if(type.equals(key)){
				for(Map.Entry<String, String> entry2 : entry.getValue().entrySet()){
					if(value.equals(entry2.getValue())){
						result = entry2.getKey();
						flag = true;
						break;
					}
				}
			}
			if(flag){
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String str  = "[{'id':'956e6bb11dfc4bd788f242f2575190ab','name':'555555555\\'66666666666\\'','phoneOrEmail':'fsfsdf'}]";
		JSONArray ja = JSONArray.fromObject(str);
	}
	
	
}
