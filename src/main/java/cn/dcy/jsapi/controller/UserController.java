package cn.dcy.jsapi.controller;

import cn.dcy.jsapi.annotation.LogAnnotation;
import cn.dcy.jsapi.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kyle
 * @date 2024/07/12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public List<User> list() {
        return Arrays.asList(
                new User(1, "张三"),
                new User(2, "李四"),
                new User(3, "王五")
        );
    }

    @LogAnnotation("query user by id")
    @GetMapping("/getById/{id}")
    public User list(@PathVariable("id") Integer id) {
         return new User(1, "张三");
    }

    @LogAnnotation("save user")
    @GetMapping("/save")
    public boolean save() {
        System.out.println("save: save");
        return true;
    }

    @LogAnnotation("edit user")
    @GetMapping("/edit")
    public boolean edit() {
        return true;
    }

    @LogAnnotation("delete user")
    @GetMapping("/del/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        return true;
    }
}
