package com.kcj.user.service;

import com.kcj.study.studyJDK8.BeanUtils;
import com.kcj.user.dto.UserDto;
import com.kcj.user.mapper.UserMapper;
import com.kcj.user.pojo.User;
import com.kcj.utils.CommonUtils;
import com.kcj.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@Service
public class UserService {

    final static String[] titleArr = {"用户名","密码","手机号","创建时间","性别"};

    final static Map<String,Integer> excelColMap = new HashMap() {{
        put("username",0);put("password",1);put("phone",2);put("created",3);put("note",4);
    }};

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
    @Transactional//jdbc引入已经完成事务了，这里加只是告诉spring当前方法需要事务控制
    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void downLoadExcel(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> userList = userMapper.select(user);
        //转化为DTO
        List<UserDto> userDtoList = BeanUtils.mapList(userList, User.class,UserDto.class);
        //性别（暂时放在note字段）
        List<String> dafaultStatusList = new ArrayList<String>();
        dafaultStatusList.add("男");
        dafaultStatusList.add("女");
        String[] dafaultStatusArr = new String[2];
        dafaultStatusList.toArray(dafaultStatusArr);
        //设置工作簿名称和每行默认的高度
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("用户导入模版");// 工作簿
        sheet.setDefaultRowHeight((short) (400));


        // 日期格式
        HSSFCellStyle dateStyle = wb.createCellStyle();
        DataFormat dateFormat = wb.createDataFormat();
        dateStyle.setDataFormat(dateFormat.getFormat("yyyy-MM-dd"));
        // 安装日期列设置为文本
        sheet.setDefaultColumnStyle(excelColMap.get("created"), dateStyle);

        //设置密码、手机号相关列为文本格式
        HSSFCellStyle textStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(excelColMap.get("phone"), textStyle);
        sheet.setDefaultColumnStyle(excelColMap.get("password"), textStyle);

        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet = ExcelUtil.renderExcelTip(sheet,2,10);

        //创建标题说明
        HSSFCellStyle style;
        List<String> list = ExcelUtil.getExcelModelTips();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            if(i==0){
                row.setHeight((short) (500));
                style = createCommonFont(wb, HSSFCellStyle.ALIGN_LEFT, (short) 11, HSSFColor.RED.index,
                        HSSFFont.BOLDWEIGHT_BOLD);
            }else {
                row.setHeight((short) (1400));
                style = createCommonFont(wb, HSSFCellStyle.ALIGN_LEFT, (short) 11, HSSFColor.BLACK.index,
                        HSSFFont.BOLDWEIGHT_BOLD);
            }
            HSSFCell cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(list.get(i));
            // setDataValidation(cell, sheet);
        }

        // 第3行为标题
        HSSFRow rowFirst = sheet.createRow(list.size());
        rowFirst.setHeight((short) (400));
        HSSFCell cell = null;
        Integer[] columnWidth = { 55, 55, 20, 35, 35};
        // 写标题
        for (int i = 0; i < titleArr.length; i++) {
            cell = rowFirst.createCell(i); // 获取第一行的每个单元格
            if (i == excelColMap.get("username")) {//用户名置为红色
                style = createTitleFont(wb, HSSFColor.RED.index);
            } else {
                style = createTitleFont(wb, HSSFColor.BLACK.index);
            }
            sheet.setColumnWidth(i, columnWidth[i] * 256);
            cell.setCellStyle(style);
            cell.setCellValue(titleArr[i]);
            // setDataValidation(cell, sheet);
        }
        // 给excel赋值
        HSSFCellStyle cellStyle = createCommonFont(wb, HSSFCellStyle.ALIGN_CENTER, (short) 11, HSSFColor.BLACK.index,
                HSSFFont.BOLDWEIGHT_NORMAL);
        for (int i = 0; i < userDtoList.size(); i++) {
            rowFirst = sheet.createRow(i + list.size() + 1);
            rowFirst.setHeight((short) (400));
            UserDto userDto = userDtoList.get(i);
            Field[] fields = userDto.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length - 1; j++) {
                try {
                    cell = rowFirst.createCell(j);
                    // 1获取属性名
                    String name = fields[j].getName();
                    //获取属性值的类型
                    Type genericType = fields[j].getGenericType();
                    // 2得到获取属性值的方法
                    Method m = userDto.getClass()
                            .getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                    Object object;
                    object = m.invoke(userDto);
                    if (object != null) {
                        //如果是日期类型要进行转换
                        if("java.util.Date".equals(genericType.getTypeName())){
                            Date created = (Date) object;
                            cell.setCellValue(CommonUtils.covertByDate(created));
                        }else {
                            cell.setCellValue(object.toString());
                        }
                    } else {
                        if (j == excelColMap.get("note")) {
                            sheet.addValidationData(setDataValidation(sheet, dafaultStatusArr, i + list.size() + 1,
                                    i + list.size() + 1, excelColMap.get("note"), excelColMap.get("note")));
                        } else {
                            cell.setCellValue("");
                        }
                    }
                    if(j==excelColMap.get("password")||j==excelColMap.get("phone")
                    ){
                        cell.setCellStyle(textStyle);
                    }else {
                        cell.setCellStyle(cellStyle);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 加上性别下拉框数据
        if (dafaultStatusArr.length < 255) { // 255以内的下拉
            // 255以内的下拉,参数分别是：作用的sheet、下拉内容数组、起始行、终止行、起始列、终止列
            sheet.addValidationData(
                    setDataValidation(sheet, dafaultStatusArr, list.size() + 1 + userDtoList.size(), 50000, excelColMap.get("note"), excelColMap.get("note"))); // 超过255个报错
        } else {
            throw new Exception("客户类型数据过多,无法加载!");
        }

        //输出文件
        OutputStream output;
        String titleName;
        String fileName = "用户导入模板";
        try {
            output = response.getOutputStream();
            response.reset();
            titleName = URLDecoder.decode(fileName, "utf-8");
            titleName = encodeFilename(titleName + ".xls", request);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment; filename=" + titleName);
            wb.write(output);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // 创建通用的字体样式
    private HSSFCellStyle createCommonFont(HSSFWorkbook wb, short alignment, short high, short colour, short bold) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(alignment); // 创建一个居中格式
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints(high);// 字体高度
        fontStyle.setBoldweight(bold);// 设置字体为粗体
        fontStyle.setColor(colour);
        style.setFont(fontStyle);
        style.setWrapText(true);
        style.setLocked(true);
        return style;
    }
    // 创建title字体样式
    private HSSFCellStyle createTitleFont(HSSFWorkbook wb, short colour) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints((short) 12);// 字体高度
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置字体为粗体
        fontStyle.setColor(colour);
        style.setFont(fontStyle);
        return style;
    }

    /**
     *
     * @Title: setDataValidation
     * @Description: 下拉列表元素不多的情况(255以内的下拉)
     */
    private DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol,
                                             int endCol) {

        DataValidationHelper helper = sheet.getDataValidationHelper();
        // 加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        // DVConstraint constraint = new DVConstraint();
        constraint.setExplicitListValues(textList);

        // 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol,
                (short) endCol);

        // 数据有效性对象
        DataValidation data_validation = helper.createValidation(constraint, regions);
        // DataValidation data_validation = new DataValidation(regions,
        // constraint);

        return data_validation;
    }

    /**
     * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0;
     * Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
     * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN;
     * rv:1.7.10) Gecko/20050717 Firefox/1.0.6
     */
    private String encodeFilename(String filename, HttpServletRequest request) {
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
