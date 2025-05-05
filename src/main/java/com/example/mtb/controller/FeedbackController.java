package com.example.mtb.controller;

import com.example.mtb.dto.FeedbackRequest;
import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.responseBuilders.ResponseBuilder;
import com.example.mtb.responseBuilders.ResponseStructure;
import com.example.mtb.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    private  final ResponseBuilder responseBuilder;

    @PreAuthorize("hasAuthority('USER')")
   @PostMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<ResponseStructure<FeedbackResponse>> creatingFeedback(@PathVariable String userId, @PathVariable String movieId, @Valid  @RequestBody FeedbackRequest feedbackRequest){

         FeedbackResponse response = feedbackService.createFeedback(userId,movieId,feedbackRequest);

         return responseBuilder.success(HttpStatus.CREATED,"Feedback sent successfully",response);
    }
}
