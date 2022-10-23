package com.bilitech.yilimusic;

import com.bilitech.yilimusic.Repository.UserRepository;
import com.bilitech.yilimusic.enetity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YiliMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(YiliMusicApplication.class, args);
    }

}
