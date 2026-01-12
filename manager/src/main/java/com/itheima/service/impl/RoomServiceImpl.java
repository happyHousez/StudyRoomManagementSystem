package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Room;
import com.itheima.mapper.RoomMapper;
import com.itheima.service.RoomService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
     private RoomMapper roomMapper;

    /**
     * 根据当前页码和每页需要展示的数据条数，查询最新上架的图书信息
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     */
    @Override
    public PageResult selectHotRooms(Integer pageNum, Integer pageSize) {
        // 设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Room> page = roomMapper.selectHotRooms();
        return new PageResult(page.getTotal(),page.getResult());
    }
    /**
     * @param room 封装查询条件的对象
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     */
    @Override
    public PageResult searchRooms(Room room, Integer pageNum, Integer pageSize) {
        // 设置分页查询的参数，开始分页
        PageHelper.startPage(pageNum,pageSize);
        Page<Room> page = roomMapper.searchRooms(room);
        return new PageResult(page.getTotal(),page.getResult());
    }
    /**
     * 根据ID 查询自习室信息
     * @param id 自习室id
     */
    @Override
    public Room findById(String id) {
        return roomMapper.findById(id);
    }
    /**
     * 新增自习室
     * @param room 页面提交的新增自习室信息
     */
    @Override
    public Integer addRoom(Room room) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        room.setOpenTime(dateFormat.format(new Date()));
        return roomMapper.addRoom(room);
    }
    /**
     * 编辑自习室信息
     * @param room 自习室信息
     */
    @Override
    public Integer editRoom(Room room) {
        return roomMapper.editRoom(room);
    }
    /**
     *   预约自习室
     * @param room 自习室信息
     * @return
     */
    @Override
    public Integer borrowRoom(Room room) {
        Room r=this.findById(room.getId()+"");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        room.setStatus("1");
        room.setAppointTime(dateFormat.format(new Date()));
        return roomMapper.editRoom(room);
    }




}
