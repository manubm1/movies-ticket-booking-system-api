package com.example.mtb.repository;

import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen,String> {

   public  List<Show> findByScreenId(String screenId);
}
