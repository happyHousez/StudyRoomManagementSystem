package com.itheima.service;
import com.itheima.domain.Appoint;
import com.itheima.domain.User;
import entity.PageResult;

/**
 * 预约记录接口
 */
public interface AppointService {
    //新增预约记录
    Integer addAppoint(Appoint appoint);
//查询预约记录
PageResult searchAppoint(Appoint appoint, User user, Integer pageNum, Integer pageSize);
}
