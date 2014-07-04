package com.ht.yikecrm.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.CellType;
import jxl.CellView;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.ht.yikecrm.model.IModel;

public class JxlUtil<T extends IModel> {

	protected static final Log LOG = LogFactory
			.getLog(java.sql.PreparedStatement.class);

	/**
	 * 生成空单元格
	 */
	public static void generateCells(WritableSheet ws, int startRows,
			int startColNums, int rows, int cols) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				try {
					ws.addCell(new Label(startColNums + c, startRows + r, ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @param sheet
	 *            操作对象
	 * @param mergedCell
	 *            所有合并单元格范围
	 * 
	 *            考虑到该方法可能被循环调用多次，而且是复制同一个表格到不同地方，因此本参数记录的原始
	 * 
	 *            被拷贝表格中的单元格，避免在多次循环后，本参数数据量不断增加，导致遍历时间太长。即本参数值需在调用
	 * 
	 *            本方法的循环外就已经获得。
	 * 
	 * 
	 * @param from1Cols
	 *            被复制表格开始列
	 * @param from1Row
	 *            被复制表格开始行
	 * @param to1Col
	 *            被复制表格结束列
	 * @param to1Row
	 *            被复制表格结束行
	 * @param from2Col
	 *            复制到表格开始列
	 * @param from2Row
	 *            复制到表格开始行
	 * @return boolean 是否完整
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static boolean copyCells(WritableSheet sheet, Range[] mergedCell,
			int from1Col, int from1Row, int to1Col, int to1Row, int from2Col,
			int from2Row) throws IOException {

		// 复制表格的高和长
		@SuppressWarnings("unused")
		int tabHigh = to1Row - from1Row + 1;
		try {
			// 制作表格，先合并单元格
			for (int i = 0; i < (to1Row - from1Row + 1); i++) {

				// 选中区域下一行
				sheet.insertRow(from2Row + i);
				sheet
						.setRowView(from2Row + i, sheet.getRowHeight(from1Row
								+ i));
				// 对插入行的列进行处理，即单元格
				for (int j = 0; j < (to1Col - from1Col + 1); j++) {

					CellFormat cf = sheet.getWritableCell(from1Col + j,
							from1Row + i).getCellFormat();

					String content = sheet.getCell(from1Col + j, from1Row + i)
							.getContents();

					if (cf == null) {
						sheet.addCell(new Label(from1Col + j, from2Row + i,
								content));
					} else {
						sheet.addCell(new Label(from1Col + j, from2Row + i,
								content, cf));
					}
				}
			}

			// 合并单元格

			for (int i = 0; i < mergedCell.length; i++) {
				int fromRow = mergedCell[i].getTopLeft().getRow();
				int fromCol = mergedCell[i].getTopLeft().getColumn();

				int toRow = mergedCell[i].getBottomRight().getRow();
				int toCol = mergedCell[i].getBottomRight().getColumn();

				// 如果检测到的合并单元格，在复制表格内，则将对应粘贴表的单元格合并。列数=原列数+表高，列数=原列数
				if (fromRow >= from1Row && fromCol >= from1Col
						&& toRow <= to1Row && toCol <= to1Col) {
					sheet.mergeCells(fromCol, fromRow + from2Row, toCol, toRow
							+ from2Row);
				}
			}

		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("finally")
	public InputStream buildExcel(ExcelTemplateBean<T> templateBean) {

		ByteArrayOutputStream osStream = new ByteArrayOutputStream();
		ByteArrayInputStream iStream = null;
		byte[] ba = null;
		List<T> models = templateBean.getModelList();
		List<Map<String, ICallBack>> structList = templateBean.getStructList();

		try {
			InputStream isStream = new FileInputStream(templateBean
					.getFilePath());
			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("utf-8");
			settings.setWriteAccess(null);
			Workbook tpWb = Workbook.getWorkbook(isStream, settings);
			WritableWorkbook wb = Workbook.createWorkbook(osStream, tpWb,
					settings);
			WritableSheet ws = wb.getSheet(0);
			ws.setName("report" + templateBean.getFileName());

			// 处理报表标题
			WritableCell wcTitle = ws.getWritableCell(templateBean
					.getTitleXColIndex(), templateBean.getTitleYRowIndex());
			WritableCellFeatures wcfTitle = wcTitle.getWritableCellFeatures();
			Label lblTitle = new Label(templateBean.getTitleXColIndex(),
					templateBean.getTitleYRowIndex(), templateBean.getTitle());
			CellFormat cfTitle = wcTitle.getCellFormat();
			if (null != wcfTitle) {
				lblTitle.setCellFeatures(wcfTitle);
			}
			if (null != cfTitle) {
				lblTitle.setCellFormat(cfTitle);
			}
			ws.addCell(lblTitle);

			// 处理报表生成者
			WritableCell wcBuildBy = ws.getWritableCell(templateBean
					.getBuildByXColIndex(), templateBean.getBuildByYRowIndex());
			WritableCellFeatures wcfBuildBy = wcBuildBy
					.getWritableCellFeatures();
			Label lblBuildBy = new Label(templateBean.getBuildByXColIndex(),
					templateBean.getBuildByYRowIndex(), templateBean
							.getBuildBy());
			CellFormat cfBuildBy = wcBuildBy.getCellFormat();
			if (null != wcfBuildBy) {
				lblBuildBy.setCellFeatures(wcfBuildBy);
			}
			if (null != cfBuildBy) {
				lblBuildBy.setCellFormat(cfBuildBy);
			}
			ws.addCell(lblBuildBy);
			// 处理公司
			WritableCell wcCompany = ws.getWritableCell(templateBean
					.getCompanyXColIndex(), templateBean.getCompanyYRowIndex());
			WritableCellFeatures wcfCompany = wcCompany
					.getWritableCellFeatures();
			Label lblCompany = new Label(templateBean.getCompanyXColIndex(),
					templateBean.getCompanyYRowIndex(), templateBean
							.getCompany());
			CellFormat cfCompany = wcCompany.getCellFormat();
			if (null != wcfCompany) {
				lblCompany.setCellFeatures(wcfCompany);
			}
			if (null != cfCompany) {
				lblCompany.setCellFormat(cfCompany);
			}
			ws.addCell(lblCompany);
			// 处理生成时间
			WritableCell wcCreateTime = ws.getWritableCell(templateBean
					.getCreateTimeXColIndex(), templateBean
					.getCreateTimeYRowIndex());
			WritableCellFeatures wcfCreateTime = wcCreateTime
					.getWritableCellFeatures();
			Label lblCreateTime = new Label(templateBean
					.getCreateTimeXColIndex(), templateBean
					.getCreateTimeYRowIndex(), templateBean.getCreateTime());
			CellFormat cfCreateTime = wcCreateTime.getCellFormat();
			if (null != wcfCreateTime) {
				lblCreateTime.setCellFeatures(wcfCreateTime);
			}
			if (null != cfCreateTime) {
				lblCreateTime.setCellFormat(cfCreateTime);
			}
			ws.addCell(lblCreateTime);
			// 处理筛选依据：数据字段
			Map<String, String> dataFieldsMap = templateBean.getDataFields();
			if (null != dataFieldsMap) {
				StringBuffer dataFieldsBuffer = new StringBuffer();
				Set<String> mapSet = dataFieldsMap.keySet();
				for (String keyStr : mapSet) {
					dataFieldsBuffer.append(keyStr).append(
							dataFieldsMap.get(keyStr)).append(",");
				}
				if (dataFieldsBuffer.toString().endsWith(",")) {
					dataFieldsBuffer = new StringBuffer(dataFieldsBuffer
							.substring(0, dataFieldsBuffer.length() - 1));
				}
				WritableCell wcDataFields = ws.getWritableCell(templateBean
						.getDataFieldsXColIndex(), templateBean
						.getDataFieldsYRowIndex());
				WritableCellFeatures wcfDataFields = wcDataFields
						.getWritableCellFeatures();
				Label lblDataFields = new Label(templateBean
						.getDataFieldsXColIndex(), templateBean
						.getDataFieldsYRowIndex(), dataFieldsBuffer.toString());
				CellFormat cfDataFields = wcDataFields.getCellFormat();
				if (null != wcfDataFields) {
					lblDataFields.setCellFeatures(wcfDataFields);
				}
				if (null != cfDataFields) {
					lblDataFields.setCellFormat(cfDataFields);
				}
				ws.addCell(lblDataFields);
			}

			// 其他报表信息及索引
			List<Map<String, List<Object>>> otherInfoList = templateBean
					.getOtherInfoList();
			if (null != otherInfoList) {
				for (Map<String, List<Object>> map : otherInfoList) {
					Set<String> mapSet = map.keySet();
					for (String keyStr : mapSet) {
						List<Object> infoList = map.get(keyStr);
						if (infoList.size() == 4) {
							String formatStr = null == infoList.get(0) ? ""
									: infoList.get(0).toString();
							Object val = infoList.get(1);
							Object xColIndex = infoList.get(2);
							Object yRowIndex = infoList.get(3);
							if (StringUtils.isEmpty(formatStr)
									|| null == xColIndex || null == yRowIndex) {
								break;
							}

							WritableCell wcDataFields = ws.getWritableCell(
									Integer.parseInt(xColIndex.toString()),
									Integer.parseInt(yRowIndex.toString()));
							WritableCellFeatures wcfDataFields = wcDataFields
									.getWritableCellFeatures();
							Label lblDataFields = new Label(Integer
									.parseInt(xColIndex.toString()), Integer
									.parseInt(yRowIndex.toString()), String
									.format(formatStr, null == val ? "-" : val));
							CellFormat cfDataFields = wcDataFields
									.getCellFormat();
							if (null != wcfDataFields) {
								lblDataFields.setCellFeatures(wcfDataFields);
							}
							if (null != cfDataFields) {
								lblDataFields.setCellFormat(cfDataFields);
							}
							ws.addCell(lblDataFields);
						}
					}
				}
			}

			// 循环处理行数据
			if (null != models && null != structList) {
				int startRowNum = templateBean.getStartRowIndex();
				int startColNum = templateBean.getStartColIndex();
				int maxColNum = templateBean.getMaxColIndex();
				for (int i = 0; i < models.size(); i++) {
					T model = models.get(i);
					int rowIndx = startRowNum + i;
					// 复制模板行
					JxlUtil.copyCells(ws, new Range[] {}, startColNum,
							startRowNum - 1, maxColNum, startRowNum - 1,
							startColNum, rowIndx);
					// 写列数据
					for (int j = startColNum; j <= maxColNum; j++) {
						Map<String, ICallBack> propertyMap = structList.get(j);
						Set<String> set = propertyMap.keySet();
						String propertyName = StringUtils.EMPTY;
						for (String key : set) {
							propertyName = key;
							break;
						}
						if (StringUtils.isNotEmpty(propertyName)) {
							ICallBack back = propertyMap.get(propertyName);
							Object propertyObject = callGetter2(model,
									propertyName);
							String propertyVal = StringUtils.EMPTY;
							if (null != back) {
								propertyVal = back.funExec(propertyObject);
							} else {
								propertyVal = null == propertyObject ? "-"
										: propertyObject.toString();
							}
							WritableCell wcxy = ws.getWritableCell(j, rowIndx);
							Label lblxy = new Label(j, rowIndx, propertyVal);
							CellFormat cfxy = wcxy.getCellFormat();
							if (null != cfxy) {
								lblxy.setCellFormat(cfxy);
							}
							ws.addCell(lblxy);
						}
					}
				}
			}
			wb.write();
			wb.close();
			ba = osStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null == ba)
				ba = new byte[] {};
			iStream = new ByteArrayInputStream(ba);
			return iStream;
		}

	}

	
	/**
	 * 新导出模板2013-12
	 * @param templateBean
	 * @return
	 */
	@SuppressWarnings("finally")
	public InputStream buildMapExcel(ExcelTemplateBean<T> templateBean) {
		ByteArrayOutputStream osStream = new ByteArrayOutputStream();
		ByteArrayInputStream iStream = null;
		byte[] ba = null;
		List<Map<String, Object>> maps = templateBean.getMapList();
		List<Map<String, ICallBack>> structList = templateBean.getStructList();
		try {
			InputStream isStream = new FileInputStream(templateBean
					.getFilePath());
			WorkbookSettings settings = new WorkbookSettings();
			settings.setEncoding("utf-8");
			Workbook tpWb = Workbook.getWorkbook(isStream, settings);
			WritableWorkbook wb = Workbook.createWorkbook(osStream, tpWb,
					settings);
			WritableSheet ws0 = wb.getSheet(0);
			WritableSheet ws1 = wb.getSheet(1);
			ws0.setName(templateBean.getFileName().replace("*", "_").replace("\\", "_").replace("/", "_").replace(":", "_").replace("?", "_").replace("\"", "_").replace("<", "_").replace(">", "_").replace("|", "_"));
			ws1.setName("用户信息");
			// 处理报表标题
			WritableCell wcTitle = ws0.getWritableCell(templateBean
					.getTitleXColIndex(), templateBean.getTitleYRowIndex());
			WritableCellFeatures wcfTitle = wcTitle.getWritableCellFeatures();
			Label lblTitle = new Label(templateBean.getTitleXColIndex(),
					templateBean.getTitleYRowIndex(), templateBean.getFileName().replace("*", "_").replace("\\", "_").replace("/", "_").replace(":", "_").replace("?", "_").replace("\"", "_").replace("<", "_").replace(">", "_").replace("|", "_"));
			CellFormat cfTitle = wcTitle.getCellFormat();
			if (null != wcfTitle) {
				lblTitle.setCellFeatures(wcfTitle);
			}
			if (null != cfTitle) {
				lblTitle.setCellFormat(cfTitle);
			}
			ws0.mergeCells(0, 0, templateBean.getMaxColIndex(), 0);
			//合并总计和合计金额
			ws0.mergeCells(0, 3, templateBean.getMaxColIndex(), 3);
			WritableCell totalcell = ws0.getWritableCell(0,3);
			WritableCellFormat totalformat = new WritableCellFormat(totalcell.getCellFormat());
			totalformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			totalcell.setCellFormat(totalformat);
			
			if (templateBean.getOtherInfoList().size()>1) {
				WritableCell moneytotalcell = ws0.getWritableCell(0,4);
				WritableCellFormat moneytotalformat = new WritableCellFormat(moneytotalcell.getCellFormat());
				for (int i = 0; i < templateBean.getMaxColIndex()+1; i++) {
					WritableCellFormat moneytotalformat2 = new WritableCellFormat(moneytotalcell.getCellFormat());
					moneytotalformat2.setBorder(Border.NONE, BorderLineStyle.THIN);
					moneytotalformat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
					ws0.addCell(new Label(i, 4, "",moneytotalformat2));	
				}
				//最后一格加右竖线
				WritableCell endtotalcell = ws0.getWritableCell(templateBean.getMaxColIndex(),4);
				WritableCellFormat endformat = new WritableCellFormat(endtotalcell.getCellFormat());
				endformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
				endtotalcell.setCellFormat(endformat);
			}else {
				ws0.mergeCells(0, 4, templateBean.getMaxColIndex(), 4);
				WritableCell moneytotalcell = ws0.getWritableCell(0,4);
				WritableCellFormat moneytotalformat = new WritableCellFormat(moneytotalcell.getCellFormat());
				moneytotalformat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
				moneytotalformat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
				moneytotalcell.setCellFormat(moneytotalformat);				
			}
			ws0.addCell(lblTitle);			
			// 处理报表生成者
			WritableCell wcBuildBy = ws1.getWritableCell(templateBean
					.getBuildByXColIndex(), templateBean.getBuildByYRowIndex());
			WritableCellFeatures wcfBuildBy = wcBuildBy
					.getWritableCellFeatures();
			Label lblBuildBy = new Label(templateBean.getBuildByXColIndex(),
					templateBean.getBuildByYRowIndex(), templateBean
							.getBuildBy());
			CellFormat cfBuildBy = wcBuildBy.getCellFormat();
			if (null != wcfBuildBy) {
				lblBuildBy.setCellFeatures(wcfBuildBy);
			}
			if (null != cfBuildBy) {
				lblBuildBy.setCellFormat(cfBuildBy);
			}
			ws1.addCell(lblBuildBy);
			// 处理公司
			WritableCell wcCompany = ws1.getWritableCell(templateBean
					.getCompanyXColIndex(), templateBean.getCompanyYRowIndex());
			WritableCellFeatures wcfCompany = wcCompany
					.getWritableCellFeatures();
			Label lblCompany = new Label(templateBean.getCompanyXColIndex(),
					templateBean.getCompanyYRowIndex(), templateBean
							.getCompany());
			CellFormat cfCompany = wcCompany.getCellFormat();
			if (null != wcfCompany) {
				lblCompany.setCellFeatures(wcfCompany);
			}
			if (null != cfCompany) {
				lblCompany.setCellFormat(cfCompany);
			}
			ws1.addCell(lblCompany);
			// 处理生成时间
			WritableCell wcCreateTime = ws1.getWritableCell(templateBean
					.getCreateTimeXColIndex(), templateBean
					.getCreateTimeYRowIndex());
			WritableCellFeatures wcfCreateTime = wcCreateTime
					.getWritableCellFeatures();
			Label lblCreateTime = new Label(templateBean
					.getCreateTimeXColIndex(), templateBean
					.getCreateTimeYRowIndex(), templateBean.getCreateTime());
			CellFormat cfCreateTime = wcCreateTime.getCellFormat();
			if (null != wcfCreateTime) {
				lblCreateTime.setCellFeatures(wcfCreateTime);
			}
			if (null != cfCreateTime) {
				lblCreateTime.setCellFormat(cfCreateTime);
			}
			ws1.addCell(lblCreateTime);
			// 处理筛选依据：数据字段
//			Map<String, String> dataFieldsMap = templateBean.getDataFields();
//			if (null != dataFieldsMap) {
//				StringBuffer dataFieldsBuffer = new StringBuffer();
//				Set<String> mapSet = dataFieldsMap.keySet();
//				for (String keyStr : mapSet) {
//					dataFieldsBuffer.append(keyStr).append(
//							dataFieldsMap.get(keyStr)).append(",");
//				}
//				if (dataFieldsBuffer.toString().endsWith(",")) {
//					dataFieldsBuffer = new StringBuffer(dataFieldsBuffer
//							.substring(0, dataFieldsBuffer.length() - 1));
//				}
//				WritableCell wcDataFields = ws1.getWritableCell(templateBean
//						.getDataFieldsXColIndex(), templateBean
//						.getDataFieldsYRowIndex());
//				WritableCellFeatures wcfDataFields = wcDataFields
//						.getWritableCellFeatures();
//				Label lblDataFields = new Label(templateBean
//						.getDataFieldsXColIndex(), templateBean
//						.getDataFieldsYRowIndex(), dataFieldsBuffer.toString());
//				CellFormat cfDataFields = wcDataFields.getCellFormat();
//				if (null != wcfDataFields) {
//					lblDataFields.setCellFeatures(wcfDataFields);
//				}
//				if (null != cfDataFields) {
//					lblDataFields.setCellFormat(cfDataFields);
//				}
//				ws1.addCell(lblDataFields);
//			}
			// 其他报表信息及索引
			List<Map<String, List<Object>>> otherInfoList = templateBean
					.getOtherInfoList();
			if (null != otherInfoList) {
				for (Map<String, List<Object>> map : otherInfoList) {
					Set<String> mapSet = map.keySet();
					for (String keyStr : mapSet) {
						List<Object> infoList = map.get(keyStr);
						if (infoList.size() == 4) {
							String formatStr = null == infoList.get(0) ? ""
									: infoList.get(0).toString();
							Object val = infoList.get(1);
							Object xColIndex = infoList.get(2);
							Object yRowIndex = infoList.get(3);
							if (StringUtils.isEmpty(formatStr)
									|| null == xColIndex || null == yRowIndex) {
								break;
							}

							WritableCell wcDataFields = ws0.getWritableCell(
									Integer.parseInt(xColIndex.toString()),
									Integer.parseInt(yRowIndex.toString()));
							WritableCellFeatures wcfDataFields = wcDataFields
									.getWritableCellFeatures();
							Label lblDataFields = new Label(Integer
									.parseInt(xColIndex.toString()), Integer
									.parseInt(yRowIndex.toString()), String
									.format(formatStr, null == val ? "-" : val));
							CellFormat cfDataFields = wcDataFields
									.getCellFormat();
							if (null != wcfDataFields) {
								lblDataFields.setCellFeatures(wcfDataFields);
							}
							if (null != cfDataFields) {
								lblDataFields.setCellFormat(cfDataFields);
							}
							ws0.addCell(lblDataFields);
						}
					}
				}
			}
			// 循环处理行数据
			if (null != maps && null != structList) {
				int startRowNum = templateBean.getStartRowIndex();
				int startColNum = templateBean.getStartColIndex();
				int maxColNum = templateBean.getMaxColIndex();
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					int rowIndx = startRowNum + i;
					// 复制模板行
					if (i != 0) {
					  ws0.insertRow(rowIndx);
					  ws0.setRowView(rowIndx, ws0.getRowView(startRowNum));
					}
					// 写列数据
					for (int j = startColNum; j <= maxColNum; j++) {
						Map<String, ICallBack> propertyMap = structList.get(j);
						Set<String> set = propertyMap.keySet();
						String propertyName = StringUtils.EMPTY;
						for (String key : set) {
							propertyName = key;
							break;
						}
						if (StringUtils.isNotEmpty(propertyName)) {
							ICallBack back = propertyMap.get(propertyName);
							Object propertyObject = map.get(propertyName);
							String propertyVal = StringUtils.EMPTY;
							if (null != back) {
								propertyVal = back.funExec(propertyObject);
							} else {
								propertyVal = null == propertyObject ? "-"
										: propertyObject.toString();
							}
							WritableCell wcformat = ws0.getWritableCell(0, 1);
							Label lb = new Label(j, 1, propertyName);
							CellFormat cf = wcformat.getCellFormat();
							if (null != cf) {
								lb.setCellFormat(cf);
							}
							ws0.addCell(lb);
							WritableCell wcxy = ws0.getWritableCell(0, 2);
							Label lblxy = new Label(j, rowIndx, propertyVal);
							CellFormat cfxy = wcxy.getCellFormat();
							if (null != cfxy) {
								lblxy.setCellFormat(cfxy);
							}
							ws0.addCell(lblxy);
						}
						//设置列宽
//						CellView columnWidth = new CellView();
//						columnWidth.setAutosize(true);
//						ws0.setColumnView(j, columnWidth);
					}
				}
			}
			wb.write();
			wb.close();
			ba = osStream.toByteArray();			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null == ba)
				ba = new byte[] {};
			iStream = new ByteArrayInputStream(ba);
			return iStream;
		}		
	}

	/**
	 * 
	 * @param obj
	 * @param propertyName
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private Object callGetter(Object obj, String propertyName)
			throws IntrospectionException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class<?> clazz = obj.getClass();
		Field[] fields = obj.getClass().getDeclaredFields();
		Object rObject = null;
		for (Field field : fields) {
			if (propertyName.equalsIgnoreCase(field.getName())) {
				PropertyDescriptor propertyDesc = new PropertyDescriptor(field
						.getName(), clazz);
				Method getMethod = propertyDesc.getReadMethod();
				if (null != getMethod) {
					rObject = getMethod.invoke(obj);
					break;
				}
			}
		}
		return rObject;
	}

	/**
	 * 
	 * @param target
	 * @param properName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IntrospectionException
	 */
	private Object callGetter2(Object target, String properName)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, IntrospectionException {
		Class<?> c = target.getClass();
		Object object = null;
		BeanInfo beanInfo = Introspector.getBeanInfo(c);
		PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor prop : props) {
			if (properName.equalsIgnoreCase(prop.getName())) {
				Method getter = prop.getReadMethod();
				if (null != getter) {
					object = getter.invoke(target);
					break;
				}
			}
		}
		return object;
	}

	/**
	 * 将List数据保存到Excel中
	 * 
	 * @param list
	 *            记录List
	 * @param field2TitleMap
	 *            JavaBean变量和表格标题
	 * @param filePath
	 *            文件存放路径
	 * @param cls
	 *            JavaBean
	 */
	public static void saveAsToExcel(List<List> lists,
			List<Map> field2TitleMaps, String filePath, List<Class> clss,
			List<String> sheetNames) {
		if (lists == null || lists.size() == 0) {
			throw new IllegalArgumentException("ListRange 未被初始化");
		}
		if (field2TitleMaps == null || field2TitleMaps.size() == 0) {
			throw new IllegalArgumentException("field2TitleMap 未被初始化");
		}
		if (filePath == null) {
			throw new IllegalArgumentException("filePath 未被初始化");
		}
		if (clss == null || clss.size() == 0) {
			throw new IllegalArgumentException("Class 未被初始化");
		}
		// 创建Excel文件
		File file = new File(filePath);
		WritableWorkbook book = null;
		try {
			// 创建表头
			book = Workbook.createWorkbook(file);
			for (int i = 0; i < field2TitleMaps.size(); i++) {
				Map field2TitleMap = field2TitleMaps.get(i);
				Object[] keySortArray = field2TitleMap.keySet().toArray();
				Arrays.sort(keySortArray);
				// javaBean变量名称Map
				Map<Object, String> fieldMap = new HashMap<Object, String>();
				// 字段中文描述Map
				Map<Object, String> titleMap = new HashMap<Object, String>();

				// 初始化字段名称与字段描述信息
				Iterator iter = field2TitleMap.entrySet().iterator();
				String[] valArr;
				Map.Entry entry;
				Object key;
				Object val;
				while (iter.hasNext()) {
					entry = (Map.Entry) iter.next();
					key = entry.getKey();
					val = entry.getValue();

					if (val != null) {
						valArr = (val.toString()).split("\\|");
						if (valArr.length == 2) {
							fieldMap.put(key, valArr[0]);
							titleMap.put(key, valArr[1]);
						} else {
							fieldMap.put(key, val.toString());
							titleMap.put(key, val.toString());
						}
					}
				}
				// 生成名为“第一页”的工作表，参数0表示这是第一页
				WritableSheet sheet1 = book.createSheet(sheetNames.get(i), i);

				int n = 0;
				// 添加标题行
				Label label = null;
				for (Object keyObj : keySortArray) {
					if (titleMap.containsKey(keyObj)) {
						label = new Label(n++, 0, titleMap.get(keyObj)
								.toString());
						sheet1.addCell(label);
					}
				}

				// 生成文件内容
				int cols = 0;
				int rows = 1;
				BeanWrapper bw = null;
				Label label2 = null;
				for (Object obj : lists.get(i)) {
					bw = new BeanWrapperImpl(clss.get(i).cast(obj));
					for (Object keyObj : keySortArray) {
						if (fieldMap.containsKey(keyObj)) {
							try {
								label2 = new Label(cols++, rows, obj2Str(bw
										.getPropertyValue(fieldMap.get(keyObj)
												.toString())));
								sheet1.addCell(label2);
							} catch (org.springframework.beans.NotReadablePropertyException ex) {
								label2 = new Label(cols, rows, "找不到属性为'"
										+ fieldMap.get(keyObj) + "'的方法");
								sheet1.addCell(label2);
							}
						}
					}
					cols = 0;
					rows++;
				}
			}
		} catch (Exception e) {
			LOG.error("Excel文件写入失败", e);
			e.printStackTrace();
		} finally {
			try {
				book.write();
				book.close();
				book = null;
			} catch (Exception e) {
				LOG.error("Excel文件写入失败，可能因为该文件正在被打开中：", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 创建Excel标题行
	 * 
	 * @param file
	 *            文件
	 * @param keySortArray
	 *            字段顺序数组
	 * @param titleMap
	 *            标题Map
	 */
	protected static void createExcelTitleAndContent(File file,
			Object[] keySortArray, Map titleMap, Map fieldMap, List list,
			Class cls, String sheetName, Integer sheetNum) {
		if (file == null) {
			throw new IllegalArgumentException("文件不存在，无法创建Excel表格。");
		}
		if (keySortArray == null) {
			throw new IllegalArgumentException("keySortArray 为null，请初始化后在操作。");
		}
		if (titleMap == null) {
			throw new IllegalArgumentException("titleMap 为 null，无法创建。");
		}

		if (fieldMap == null) {
			throw new IllegalArgumentException("fieldMap 为 null，无法生成。");
		}
		if (list == null) {
			throw new IllegalArgumentException("数据列表 为 null，无法读取数据。");
		}
		if (cls == null) {
			throw new IllegalArgumentException("Class 为 null，无法进行类的映射关系。");
		}

		WritableWorkbook book = null;
		try {
			// 创建表头
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet1 = book.createSheet(sheetName, sheetNum);

			int n = 0;
			// 添加标题行
			Label label = null;
			for (Object keyObj : keySortArray) {
				if (titleMap.containsKey(keyObj)) {
					label = new Label(n++, 0, titleMap.get(keyObj).toString());
					sheet1.addCell(label);
				}
			}

			// 生成文件内容
			int cols = 0;
			int rows = 1;
			BeanWrapper bw = null;
			Label label2 = null;
			for (Object obj : list) {
				bw = new BeanWrapperImpl(cls.cast(obj));
				for (Object keyObj : keySortArray) {
					if (fieldMap.containsKey(keyObj)) {
						try {
							label2 = new Label(cols++, rows, obj2Str(bw
									.getPropertyValue(fieldMap.get(keyObj)
											.toString())));
							sheet1.addCell(label2);
						} catch (org.springframework.beans.NotReadablePropertyException ex) {
							label2 = new Label(cols, rows, "找不到属性为'"
									+ fieldMap.get(keyObj) + "'的方法");
							sheet1.addCell(label2);
						}
					}
				}
				cols = 0;
				rows++;
			}

		} catch (Exception e) {
			LOG.error("Excel文件写入失败", e);
			e.printStackTrace();
		} finally {
			try {
				book.write();
				book.close();
				book = null;
			} catch (Exception e) {
				LOG.error("Excel文件写入失败，可能因为该文件正在被打开中：", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成Excel内容
	 * 
	 * @param file
	 *            文件
	 * @param keySortArray
	 *            字段顺序数组
	 * @param list
	 *            数据列表
	 * @param cls
	 *            指定的Class
	 */
	protected static void createExcelContent(File file, Object[] keySortArray,
			Map fieldMap, List list, Class cls) {
		if (file == null) {
			throw new IllegalArgumentException("文件不存在，无法创建Excel表格。");
		}

		if (keySortArray == null) {
			throw new IllegalArgumentException("keySortArray 为null，请初始化后在操作。");
		}

		if (fieldMap == null) {
			throw new IllegalArgumentException("fieldMap 为 null，无法生成。");
		}
		if (list == null) {
			throw new IllegalArgumentException("数据列表 为 null，无法读取数据。");
		}
		if (cls == null) {
			throw new IllegalArgumentException("Class 为 null，无法进行类的映射关系。");
		}

		WritableWorkbook book = null;
		try {
			// Excel获得文件
			Workbook wb = Workbook.getWorkbook(file);
			// 打开一个文件的副本，并且指定数据写回到原文件
			book = Workbook.createWorkbook(file, wb);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet1 = book.getSheet("sheet1");

			int cols = 0;
			int rows = 1;
			BeanWrapper bw = null;
			Label label = null;
			for (Object obj : list) {
				bw = new BeanWrapperImpl(cls.cast(obj));
				for (Object keyObj : keySortArray) {
					if (fieldMap.containsKey(keyObj)) {
						try {
							label = new Label(cols++, rows, obj2Str(bw
									.getPropertyValue(fieldMap.get(keyObj)
											.toString())));
							sheet1.addCell(label);
						} catch (org.springframework.beans.NotReadablePropertyException ex) {
							label = new Label(cols, rows, "找不到属性为'"
									+ fieldMap.get(keyObj) + "'的方法");
							sheet1.addCell(label);
						}
					}
				}
				cols = 0;
				rows++;
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Excel的Sheet工作簿内容写入失败1："
					+ e.getMessage());
		} catch (RowsExceededException e) {
			throw new IllegalArgumentException("Excel的Sheet工作簿内容写入失败2："
					+ e.getMessage());
		} catch (WriteException e) {
			throw new IllegalArgumentException("Excel的Sheet工作簿内容写入失败3："
					+ e.getMessage());
		} catch (BiffException e) {
			throw new IllegalArgumentException("源Excel文件读取失败4："
					+ e.getMessage());
		} catch (Exception e) {
			LOG.error("Excel文件写入失败", e);
			e.printStackTrace();
		} finally {
			try {
				book.write();
				book.close();
				book = null;
			} catch (Exception e) {
				throw new IllegalArgumentException("Excel文件写入失败，可能因为该文件正在被打开中："
						+ e.getMessage());
			}
		}
	}

	private static String obj2Str(Object o) {
		if (o == null) {
			return "";
		}
		return o.toString();
	}

}
