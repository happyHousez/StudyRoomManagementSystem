package com.itheima.service;

import com.itheima.domain.Room;
import entity.PageResult;

public interface RoomService {
    //查询 热门推荐
    PageResult selectHotRooms(Integer pageNum, Integer pageSize);

    //分页查询图书
    PageResult searchRooms(Room room,Integer pageNum,Integer pageSize);

    // 根据Id 查询自习室信息
    Room findById(String id);
    //新增自习室
    Integer addRoom(Room room);
    //编辑自习室信息
    Integer editRoom(Room room);
    // 预约自习室
    Integer borrowRoom(Room room);

}
