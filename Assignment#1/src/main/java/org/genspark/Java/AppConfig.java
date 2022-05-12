package org.genspark.Java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Student getStudent(){
        return new Student(1,"Ian");
    }
    @Bean
    public Address getAddress(){
        return new Address("Broussard","LA","USA","70518");
    }
    @Bean
    public Phone getPhone(){
        return new Phone("Nokia");
    }
}
