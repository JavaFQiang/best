package com.best.operate.service.impl;

import com.best.operate.dao.AdminMapper;
import com.best.operate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by fanqiang on 2018-06-02.
 */
@Service(value = "adminService")
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminMapper adminMapper;

}
