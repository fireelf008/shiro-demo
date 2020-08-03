package com.wsf.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.wsf.demo.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/getToken")
    public ResponseEntity getToken() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", JwtUtils.createToken());
        jsonObject.put("status", HttpStatus.OK.value());

        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK.value()).body(jsonObject);
        return responseEntity;
    }

    @GetMapping("/tokenTest")
    public ResponseEntity tokenTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "访问成功");
        jsonObject.put("status", HttpStatus.OK.value());

        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK.value()).body(jsonObject);
        return responseEntity;
    }
}
