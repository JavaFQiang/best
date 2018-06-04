package com.best.operate.controller;

import com.best.common.HttpResult;
import com.best.common.ResultData;
import com.best.operate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fanqiang on 2018-06-02.
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "login")
    public HttpResult login(String username, String pwd){
    return HttpResult.makeSuccessedResult(new ResultData());
    }

}
