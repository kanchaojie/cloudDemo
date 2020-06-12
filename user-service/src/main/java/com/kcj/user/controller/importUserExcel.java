package com.kcj.user.controller;


import com.kcj.user.pojo.User;
import com.kcj.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
