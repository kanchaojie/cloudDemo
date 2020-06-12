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
}
