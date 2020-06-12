package com.kcj.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellRangeAddress;

import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {
	public static final String UserMsg = "1、标红字段为必填；\n" +
			"2、性别只可为系统已有的选项；\n" +
			"3、日期格式为:年-月-日 例如2018-2-11；\n" +
			"4、若填写密码，则密码和需同时填写；";


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
}
