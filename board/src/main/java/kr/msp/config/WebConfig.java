package kr.msp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.UrlFilenameViewController;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.msp.login.SessionInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
    @Autowired
    private SessionInterceptor sessionInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/api/**") // '/api/**' 경로에 적용
                .excludePathPatterns("/api/user/login", "/api/user/join", "/api/user/checkID"); // 예외 경로
    }

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    BeanNameViewResolver beanNameViewResolver(){
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(0);
        return beanNameViewResolver;
    }

    @Bean(name = "defaultJsonView")
    MappingJackson2JsonView mappingJackson2JsonView(){
        return new MappingJackson2JsonView();
    }

    @Bean
    UrlFilenameViewController urlFilenameViewController(){
        return new UrlFilenameViewController();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:18080")
//                .allowedMethods("GET", "HEAD", "POST", "OPTIONS")
//                .allowedHeaders("*")
//                .exposedHeaders("X-Test-1", "X-Test-2")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }

}
