package cn.dcy.jsapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Kyle
 * @date 2024/06/08
 */
@RestController
public class TestController {

    //@Resource(name = "testRedisTopic02")
    //private RTopic redisTopicListener02;

    //@RequestMapping(path = "/redistopic", method = RequestMethod.GET)
    //void publicRedisTopic() {
    //    redisTopicListener02.publish("Hello World!");
    //}

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
