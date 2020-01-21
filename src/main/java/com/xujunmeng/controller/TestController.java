package com.xujunmeng.controller;

import com.xujunmeng.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author james
 * @date 2020/1/21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ProducerService producer;

    @GetMapping("/push")
    public String pushMsg(@RequestParam("msg") String msg) {
        return producer.send("TopicTest", "pushTag", msg);
    }

}
