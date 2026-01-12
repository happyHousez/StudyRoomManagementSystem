package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.domain.Room;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RoomMapper {

    //  查询热门推荐的自习室
    @Select("SELECT * FROM room where status !='2' and isHot='1'")
    @Results(id = "roomMap",value = {
            //id字段默认为false，表示不是主键
            //column表示数据库表字段，property表示实体类属性名。
            @Result(id = true,column = "room_id",property = "id"),
            @Result(column = "room_name",property = "name"),
            @Result(column = "location",property = "location"),
            @Result(column = "capacity",property = "capacity"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "status",property = "status"),
            @Result(column = "open_time",property = "openTime"),
            @Result(column = "close_time",property = "closeTime"),
            @Result(column = "room_appointment",property = "appointment"),
            @Result(column = "room_appointTime",property = "appointTime"),
            @Result(column = "description",property = "description"),
            @Result(column = "isHot",property = "isHot")

    })
    Page<Room> selectHotRooms();

    //分页查询自习室
//    @Select("select * from room where status !=2")
    @Select({"<script>" +
            "select * from room where status !=2" +
            "<if test=\"name !=null\"> AND  room_name like  CONCAT('%',#{name},'%')</if>"+
            "</script>"})
    @ResultMap("roomMap")
    Page<Room>  searchRooms(Room room);

    //根据Id查询自习室信息
    @Select("select * from room where room_id=#{id}")
    @ResultMap("roomMap")
    Room findById(String id);

    //新增自习室
    Integer addRoom(Room room);
    //编辑自习室信息
    Integer editRoom(Room room);
}
