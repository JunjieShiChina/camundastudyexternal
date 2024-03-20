package me.shijunjiee.camundastudyexternal.controller;

import me.shijunjiee.camundastudyexternal.dto.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("/user/{id}")
    public UserInfoResponse userInfo(@PathVariable(name = "id") Long id) {
        UserInfoResponse response = new UserInfoResponse();
        if (id == 1L) {
            response.setSex("女");
        }
        if (id == 2L) {
            response.setSex("男");
        }
        if (id == 3L) {
            response.setSex("女");
        }
        return response;
    }

}
