package com.example.logger.controller;


import com.example.logger.model.Request;
import com.example.logger.model.Response;
import com.example.logger.service.MyModifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {

    private MyModifyService myModifyService;

    @Autowired
    public MyController(@Qualifier("ModifySystemTime") MyModifyService myModifyService) {
        this.myModifyService = myModifyService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {
        log.info("incoming request:" + request);
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("234")
                .errorMessage("234")
                .build();

        Response modifiedResponse = myModifyService.modify(response);
        log.info("Outgoing request:" + modifiedResponse);
        return new ResponseEntity<Response>(modifiedResponse, HttpStatus.OK);
    }
}
