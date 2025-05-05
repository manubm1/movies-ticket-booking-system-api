package com.example.mtb.service.serviceimpl;

import com.example.mtb.dto.FeedbackResponse;
import com.example.mtb.dto.FeedbackRequest;
import com.example.mtb.entity.*;
import com.example.mtb.exception.MovieNotFoundException;
import com.example.mtb.exception.UserNotFoundException;
import com.example.mtb.repository.FeedbackRepository;
import com.example.mtb.repository.MovieRepository;
import com.example.mtb.repository.UserRepository;
import com.example.mtb.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    @Override
    public FeedbackResponse createFeedback(String userID,String movieId, FeedbackRequest feedbackRequest) {


        Optional<User> optinalUser = Optional.ofNullable(userRepository.findById(userID).
                orElseThrow(() -> new UserNotFoundException(" User can only give the reviews")));
       User user = optinalUser.get();
        Optional<Movie> optinalMovie = Optional.ofNullable(movieRepository.findById(movieId).
                orElseThrow(() -> new MovieNotFoundException(" Fetching movie is not found")));
        Movie movie = optinalMovie.get();

         Feedback feedback = new Feedback();
         feedback.setRating(feedbackRequest.rating());
         feedback.setReviews(feedbackRequest.reviews());
         feedback.setUser((User) user);

         List<Feedback> feedbacklist = new ArrayList<>();
         feedbacklist.add(feedback);
         movie.setFeedbacks(feedbacklist);
          user.setFeedback(feedbacklist);



         feedbackRepository.save(feedback);


         return  new FeedbackResponse(feedback.getRating(),feedback.getReviews());
    }
}
