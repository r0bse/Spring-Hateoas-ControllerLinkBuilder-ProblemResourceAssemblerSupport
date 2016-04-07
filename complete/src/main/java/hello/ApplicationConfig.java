package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {

    @Bean
    @Scope("prototype")
    public GreetingResource greetingResource(String text){
        return new GreetingResource(text);
    }

    @Bean
    @Scope("prototype")
    public GreetingEntity greetingEntity(String text){
        return new GreetingEntity(text);
    }

}
