package com.example.mtb.service;

import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.dto.FeedbackRequest;
import jakarta.validation.Valid;

public interface FeedbackService {
    FeedbackResponse createFeedback(String userId,String movieId, @Valid FeedbackRequest feedbackRequest);
}
