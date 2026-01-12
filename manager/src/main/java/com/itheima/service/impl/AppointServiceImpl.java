package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Appoint;
import com.itheima.domain.User;
import com.itheima.mapper.AppointMapper;
import com.itheima.service.AppointService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AppointServiceImpl implements AppointService {
    @Autowired
    private AppointMapper appointMapper;

    @Override
    public Integer addAppoint(Appoint appoint) {
        return appointMapper.addAppoint(appoint);
    }

    /**
     * 查询借阅记录
     * @param appoint 借阅记录的查询条件
     * @param user 当前的登录用户
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     */
    @Override
    public PageResult searchAppoint(Appoint appoint, User user, Integer pageNum, Integer pageSize) {
        // 设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum, pageSize);
        //如果不是管理员，则查询条件中的借阅人设置为当前登录用户
        if(!"ADMIN".equals(user.getRole())){
            appoint.setAppointment(user.getName());
        }
        Page<Appoint> page= appointMapper.searchAppoint(appoint);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
