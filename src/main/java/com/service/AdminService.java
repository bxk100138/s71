package com.service;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.AdminDAO;
import com.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    /**
     *
     * @param username  用户名
     * @param password  密码
     * @return  对象
     */
    public Admin login(String username, String password){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);

        String pre = "123!7667@";
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String digestHex = md5.digestHex(password+pre);
        String ss = md5.digestHex(pre+digestHex);

        Admin admin = adminDAO.selectOne(queryWrapper);
        return admin;

    }
}
