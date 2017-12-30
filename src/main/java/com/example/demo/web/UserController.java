//package com.example.demo.web;
//
//
//import com.example.demo.entity.UserEntity;
//import com.example.demo.enums.UserSexEnum;
//import com.example.demo.mapper.ue.User1Mapper;
//import com.example.demo.mapper.web.User2Mapper;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private User1Mapper user1Mapper;
//
//    @Autowired
//    private User2Mapper user2Mapper;
//
//    @RequestMapping("/getUsers1")
//    public List<UserEntity> getUsers1() {
//        List<UserEntity> users = user1Mapper.getAll();
//        return users;
//    }
//
//    @RequestMapping("/getUsers2")
//    public List<UserEntity> getUsers2() {
//        List<UserEntity> users = user2Mapper.getAll();
//        return users;
//    }
//
//    @RequestMapping("/add")
//    public void save() {
//        UserEntity user = null;
//        if (user == null) {
//            user = new UserEntity();
//            user.setUserName("自定义");
//            user.setPassWord("password");
//            user.setUserSex(UserSexEnum.MAN);
//            user.setNickName("nick");
//        }
//        user2Mapper.insert(user);
//    }
//
//    @RequestMapping(value = "update")
//    public void update(UserEntity user) {
//        user2Mapper.update(user);
//    }
//
//    @RequestMapping(value = "/delete/{id}")
//    public void delete(@PathVariable("id") Long id) {
//        user1Mapper.delete(id);
//    }
//
//
//    @RequestMapping(value = "/queryall.do", method = RequestMethod.GET)
//    public PageInfo<UserEntity> queryAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
//                                         @RequestParam(value = "pageSize", required = false, defaultValue = "2") Integer pageSize) {
//        PageHelper.startPage(1, 1);
//        List<UserEntity> list = user1Mapper.getAll();
//        PageInfo<UserEntity> pageInfo = new PageInfo<>(list);
//
//        PageHelper.startPage(1, 2);
//        List<UserEntity> list2 = user2Mapper.getAll();
//        return pageInfo;
//    }
//
//    @GetMapping(value = "/testByMap")
//    public List<UserEntity> testByMap() {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("id", 28);
////        map.put("userName", "dsf");
//        List list = user2Mapper.listByMap(map);
//        return list;
//    }
//}