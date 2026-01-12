package com.itheima.mapper;
import com.github.pagehelper.Page;
import com.itheima.domain.Appoint;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AppointMapper {
//新增借阅记录
Integer addAppoint(Appoint appoint);

@Select({"<script>" +
        "select * from appoint " +
        "where 1=1" +
        "<if test=\"appointment != null\">AND appointment like  CONCAT('%',#{appointment},'%')</if>" +
        "<if test=\"roomname != null\">AND appoint_roomname  like  CONCAT('%',#{roomname},'%') </if>" +
        "</script>"
})
@Results(id = "appointMap",value = {
        //id字段默认为false，表示不是主键
        //column表示数据库表字段，property表示实体类属性名。
        @Result(id = true,column = "appoint_id",property = "id"),
        @Result(column = "appoint_roomname",property = "roomname"),
        @Result(column = "appoint_location",property = "location"),
        @Result(column = "appointment",property = "appointment"),
        @Result(column = "appointTime",property = "appointTime")
})
//查询借阅记录
Page<Appoint> searchAppoint (Appoint appoint);
}
