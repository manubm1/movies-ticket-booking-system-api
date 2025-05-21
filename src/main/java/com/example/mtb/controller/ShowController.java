package com.example.mtb.controller;

import com.example.mtb.dto.ShowProjection;
import com.example.mtb.dto.ShowResponse;
import com.example.mtb.dto.ShowsRequest;
import com.example.mtb.entity.Show;
import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.ShowService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Controller
@AllArgsConstructor
public class ShowController {

    private final ShowService showService;

    private final ResponseBuilder responseBuilder;
     @PostMapping("/theater/{theaterId}/screen/{screenId}/movie/{movieId}")
    public ResponseEntity<ResponseStructure<ShowResponse>> createShow(@PathVariable String theaterId,@PathVariable String screenId, @PathVariable String movieId, @RequestParam Instant startsAt){
        ShowResponse response = showService.createShow(theaterId,screenId,movieId,startsAt);
        return responseBuilder.success(HttpStatus.CREATED,"Show created successfully",response);
    }

     @GetMapping("/shows/{theaterId}")
    public ResponseEntity<ResponseStructure<ShowResponse>> findShows(String theaterID){
         ShowResponse response = showService.findShows(theaterID);
         return responseBuilder.success(HttpStatus.FOUND,"shows fetched successfully",response);
    }

    @GetMapping("/show/{movieId}")
    public ResponseEntity<ResponseStructure<Page<ShowProjection>>> findshowsBymovieId(@PathVariable String movieId, @RequestBody ShowsRequest  request, @RequestHeader String city){

         Page<ShowProjection> response = showService.findShowsByMovieId(movieId,request,city);
         return  responseBuilder.success(HttpStatus.FOUND, "show fetched successfully", response);
         }
}


