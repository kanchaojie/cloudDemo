package com.kcj.user.controller;


import com.kcj.user.pojo.User;
import com.kcj.user.reponseUtil.BaseResponse;
import com.kcj.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("userImport")
public class importUserExcel {
    @Autowired
    private UserService userService;
    /**
     * 下载模板
     * @param user
     * @param request
     * @param response
     */
    @PostMapping(value = "/downloadTemplate")
    public void downloadTemplate(@RequestBody User user, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        userService.downLoadExcel(user, request, response);
    }

    /**
     * 导入校验
     */
    @PostMapping(value = "/verifyExcel")
    public BaseResponse<Object> verifyExcel(@RequestParam(value = "excelFile", required = true) MultipartFile excelFile, HttpServletRequest request,
                                            HttpServletResponse response) {
        return userService.verifyExcel(excelFile, request, response);
    }
    /**
     * 批量导入
     */
    @PostMapping(value = "/importExcel")
    public BaseResponse<Object> importExcel(@RequestParam(value = "excelFile", required = true) MultipartFile excelFile,
                                            @RequestParam(value = "importFileName", required = true) String importFileName,
                                            @RequestParam(value = "importFilePath", required = true) String importFilePath,
                                            @RequestParam(value = "importRemark", required = false) String importRemark, HttpServletRequest request,
                                            HttpServletResponse response) {
        /*ChineseCharToEnUtil cte = new ChineseCharToEnUtil();
        List<Long> ids = new ArrayList<>();
        ids.add(projectId);
        CustomerQueryDto customerQueryDto = new CustomerQueryDto();
        customerQueryDto.setIds(ids);
        customerQueryDto.setQueryType(2);
        BaseResponse<List<AdminWebDto>> queryAdminsByProjectIds = houseService.queryAdminsByProjectIds(customerQueryDto,
                UserUtil.getAccessSource(this.getRequest()), UserUtil.getXAccessToken());
        Map<String, String> uploadData = customerService.uploadErrorExcel(excelFile, projectId, request, response);
        CustomerImportRecord customerImportRecord = new CustomerImportRecord();
        customerImportRecord.setProjectId(projectId);
        customerImportRecord.setImportFileName(importFileName);
        customerImportRecord.setImportFilePath(importFilePath);
        customerImportRecord.setErrorFileName(uploadData == null ? null : uploadData.get("fileName"));
        customerImportRecord.setErrorFilePath(uploadData == null ? null : uploadData.get("fileUrl"));
        customerImportRecord.setImportRemark(importRemark);
        customerImportRecord.setProjectPathName(queryAdminsByProjectIds.getData().get(0).getAdminPath());
        customerImportRecord.setProjectPathCode(
                cte.getAllFirstLetter(queryAdminsByProjectIds.getData().get(0).getAdminPath()).toUpperCase());

        // 导入开始，生成导入记录
        Long recordId = customerImportRecordWebService.beginImportExcel(projectId, customerImportRecord);*/
        BaseResponse<Object> result = null;
        try {
            result = userService.importExcel(excelFile, request, response);
            if (result.getIsSuccess() == null || !result.getCode().equals("0")) {
                return BaseResponse.errorWithData("导入异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.errorWithData("导入异常");
        }
        return BaseResponse.successWithData(result.getData());
    }
}
