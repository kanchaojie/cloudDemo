package com.kcj.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
	private static HSSFWorkbook workBook;

	public static final String UserMsg = "1、标红字段为必填；\n" +
			"2、性别只可为系统已有的选项；\n" +
			"3、日期格式为:年-月-日 例如2018-2-11；\n" +
			"4、若填写密码，则密码和手机号需同时填写；";


	/**
	 * 获取excel说明list
	 * @return
	 */
	public static List<String> getExcelModelTips() {
		List<String> list = new ArrayList<>();
		list.add("模板填写须知：");
		list.add(ExcelUtil.UserMsg);
		return list;
	}

	/**
	 * 渲染excel提示说明
	 * @param sheet
	 * @return
	 */
	public static HSSFSheet renderExcelTip(HSSFSheet sheet, int mergeRows, int mergeCols) {
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		for(int i = 0; i < mergeRows; i++) {
			sheet.addMergedRegion(new CellRangeAddress(i, i, 0, mergeCols));
		}
		return sheet;
	}


	/**
	 * 导出excel通用工具类
	 * @param headers excel表头行
	 * @param dataset	数据list
	 * @param request
	 * @param response
	 */
	public static void writeExcel(String[] headers, List<T> dataset, HttpServletRequest request,
								  HttpServletResponse response) {// 创建HSSFWorkbook对象(excel的文档对象)
		// 建立新的sheet对象（excel的表单）
		workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("sheet1");
		sheet.setDefaultRowHeightInPoints(15);
		sheet.setDefaultColumnWidth(20);
		HSSFRow row = sheet.createRow(0);
		// 产生表格标题行
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 产生表格内容行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;// 0已经占用，所以+1
			row = sheet.createRow(index);
			Object obj = it.next();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
				Class<? extends Object> tCls = obj.getClass();// 泛型为Object以及所有Object的子类
				Method getMethod = null;
				Object value = null;
				try {
					getMethod = tCls.getDeclaredMethod(getMethodName, new Class[] {});
					value = getMethod.invoke(obj, new Object[] {});
					if (value != null) {
						cell.setCellValue(value.toString());// 为当前列赋值
					} else {
						cell.setCellValue("");// 为空写入空字符串
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}
		OutputStream output;
		String titleName;
		String fileName = "导出详情";
		try {
			output = response.getOutputStream();
			response.reset();
			titleName = URLDecoder.decode(fileName, "utf-8");
			titleName = encodeFilename(titleName + ".xls", request);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment; filename=" + titleName);
			workBook.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0;
	 * Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
	 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN;
	 * rv:1.7.10) Gecko/20050717 Firefox/1.0.6
	 */
	public static String encodeFilename(String filename, HttpServletRequest request) {
		String agent = request.getHeader("USER-AGENT");
		try {
			String newFileName = URLEncoder.encode(filename, "UTF-8");
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
				// return MimeUtility.encodeText(filename, "UTF-8", "B");
				newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
				return newFileName;
			}
			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}
}
