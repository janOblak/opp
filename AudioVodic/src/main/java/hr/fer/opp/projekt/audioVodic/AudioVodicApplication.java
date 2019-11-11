package hr.fer.opp.projekt.audioVodic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AudioVodicApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(AudioVodicApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AudioVodicApplication.class);
    }
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//	    // Register resource handler for images
//	    registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
//	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
//	}

}
