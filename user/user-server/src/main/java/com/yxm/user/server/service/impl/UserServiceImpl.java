package com.yxm.user.server.service.impl;

import com.yxm.user.server.dataobject.UserInfo;
import com.yxm.user.server.repository.UserInfoRepository;
import com.yxm.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
