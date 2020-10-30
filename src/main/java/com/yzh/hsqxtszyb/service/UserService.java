package com.yzh.hsqxtszyb.service;

import com.yzh.hsqxtszyb.dao.StationDaoImpl;
import com.yzh.hsqxtszyb.model.User;
import com.yzh.hsqxtszyb.result.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    StationDaoImpl stationDao=new StationDaoImpl();

    public int userIsExist(User myUser){

        List<User> personList = stationDao.获取指定ID用户信息(myUser);
        if (personList.size() > 0) {
            if (personList.stream().anyMatch(y -> y.getPassword().equals(myUser.getPassword()))) {
                return 200;
            }else {
                return 201;
            }

        } else {
            return 400;
        }
    }
}
