package com.example.mtb.controller;

import com.example.mtb.dto.ScreenRegisterResponse;
import com.example.mtb.dto.ScreenRegistrationRequest;
import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.entity.Screen;
import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.ScreenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ScreenController {

    private ScreenService screenService;

    @Autowired
    private ResponseBuilder responseBuilder;


    @PostMapping("/screen/{theaterId}")
    public ResponseEntity<ResponseStructure<ScreenRegisterResponse>> screenrRegistration (@PathVariable String theaterId, @RequestBody ScreenRegistrationRequest request){
        ScreenRegisterResponse screen = screenService.screenRegistration(theaterId,request);
        return responseBuilder.success(HttpStatus.CREATED,"Screen registered successfully",screen);
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<ResponseStructure<ScreenResponse>> findscreen(@PathVariable String screenId){
        ScreenResponse screen = screenService.findScreen(screenId);

        return  responseBuilder.success(HttpStatus.FOUND,"Screen fecthed successfully",screen);

    }
}
