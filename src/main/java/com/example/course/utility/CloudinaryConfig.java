package com.example.course.utility;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "patika-dev",
                        "api_key", "997655856424281",
                        "api_secret", "EPw6dvoVSaoAwDAwf95TgAb9KPM"
                ));
    }
}
