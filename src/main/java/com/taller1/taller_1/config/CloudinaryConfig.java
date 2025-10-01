package com.taller1.taller_1.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

//    @Value("${cloudinary.cloud_name}")
//    private String cloudName;
//
//    @Value("${cloudinary.api_key}")
//    private String apiKey;
//
//    @Value("${cloudinary.api_secret}")
//    private String apiSecret;
//
//    @Value("${cloudinary.secure}")
//    private boolean secure;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "daxahcakc",
                "api_key", "922847878686654",
                "api_secret", "OSdxwvVIx3RbsuX0ThzWXQBNeq4",
                "secure", true
        ));
    }
}

