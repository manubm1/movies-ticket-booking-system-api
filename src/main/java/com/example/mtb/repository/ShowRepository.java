package com.example.mtb.repository;

import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show,String> {



   public  List<Show> findByScreen_ScreenId(String screenId);
}
