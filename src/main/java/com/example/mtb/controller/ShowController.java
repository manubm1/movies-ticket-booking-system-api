package com.example.mtb.controller;

import com.example.mtb.dto.ShowResponse;
import com.example.mtb.entity.Show;
import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Controller
@AllArgsConstructor
public class ShowController {

    private final ShowService showService;

    private final ResponseBuilder responseBuilder;
     @PostMapping("/theater/{theaterId}/screen/{screenId}/movie/{movieId}")
    public ResponseEntity<ResponseStructure<ShowResponse>> createShow(@PathVariable String theaterId,@PathVariable String screenId, @PathVariable String movieId, @RequestParam Instant startsAt){
        ShowResponse response = showService.createShow(theaterId,screenId,movieId,startsAt);
        return responseBuilder.success(HttpStatus.CREATED,"Show cretaed successsfully",response);
    }

}
