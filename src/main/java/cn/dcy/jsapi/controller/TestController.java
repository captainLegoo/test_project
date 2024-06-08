package cn.dcy.jsapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kyle
 * @date 2024/06/08
 */
@RestController
public class TestController {

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
