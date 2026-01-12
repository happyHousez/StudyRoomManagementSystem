package com.itheima.controller;

import com.itheima.domain.Appoint;
import com.itheima.domain.Room;
import com.itheima.domain.User;
import com.itheima.service.AppointService;
import com.itheima.service.RoomService;
import entity.PageResult;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private AppointService appointService;
    /**
     * 查询热门的自习室
     */
    @RequestMapping("/selectHotRoom")
    public ModelAndView selectHotRoom()
    {
        int pageNum=1;
        int pageSize=5;
        PageResult pageResult = roomService.selectHotRooms(pageNum, pageSize);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("rooms_new");
        mv.addObject("pageResult",pageResult);
        return mv;
    }

    /**
     * 分页查询符合条件且未下架图书信息
     * @param room 查询的条件封装到room中
     * @param pageNum  数据列表的当前页码
     * @param pageSize 数据列表1页展示多少条数据
     */
    @RequestMapping("/searchRooms")
    public ModelAndView searchRooms(Room room, Integer pageNum, Integer pageSize, HttpServletRequest request)
    {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
//        查询到的自习室信息
        PageResult pageResult = roomService.searchRooms(room, pageNum, pageSize);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("rooms");
        mv.addObject("pageResult", pageResult);
        mv.addObject("search",room);
        mv.addObject("pageNum",pageNum);
        mv.addObject("pageSize",pageSize);
        mv.addObject("gourl",request.getRequestURI());
        return mv;
    }

    /**
     * 根据自习室id 查询自习室西悉尼
     * @param id 查询的自习室id
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Result<Room> findById(String id){
        try {
            Room room=roomService.findById(id);
            if(room==null){
                return new Result(false,"查询自习室失败！");
            }
            return new Result(true,"查询自习室成功",room);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"查询自习室失败！");
        }
    }

    /**
     * 借阅图书
     * @param room 借阅的图书
     * @return
     */
    @ResponseBody
    @RequestMapping("/borrowRoom")
    public Result borrowRoom(Room room, HttpSession session) {
        //获取当前登录的用户姓名
        String pname = ((User) session.getAttribute("USER_SESSION")).getName();
        room.setAppointment(pname);
        try {
            //根据图书的id和用户进行图书借阅
            Integer count = roomService.borrowRoom(room);
            System.out.println("count:"+room.toString());
            Appoint appoint = new Appoint();
            appoint.setRoomname(room.getName());
            appoint.setLocation(room.getLocation());
            appoint.setAppointment(room.getAppointment());
            appoint.setAppointTime(room.getAppointTime());
            Integer i = appointService.addAppoint(appoint);
            if (count != 1 || i != 1 ) {

                return new Result(false, "预约失败!");
            }
            return new Result(true, "预约成功,请按时到自习室指定位置！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "预约失败!");
        }
    }

    /**
     * 编辑自习室信息
     * @param room 编辑的图书信息
     */
  @ResponseBody
  @RequestMapping("/editRoom")
    public Result editRoom(Room room) {
        try {
            Integer count = roomService.editRoom(room);
            if (count != 1) {
                return new Result(false, "编辑失败!");
            }
            return new Result(true, "编辑成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "编辑失败!");
        }
    }


    /**
     * 添加自习室信息
     * @param room 添加的图书信息
     */
   @ResponseBody
   @RequestMapping("/addRoom")
    public Result addRoom(Room room) {
        try {
            Integer count = roomService.addRoom(room);

            if (count != 1) {
                return new Result(false, "添加失败!");
            }
            return new Result(true, "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败!");
        }
    }
}
