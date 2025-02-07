package com.example.componenteecommerce.config;

import com.example.componenteecommerce.filter.TokenFilter;
import com.example.componenteecommerce.service.AuthClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TokenFilter> tokenFilter(AuthClient authClient) {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TokenFilter(authClient));
        registrationBean.addUrlPatterns("/ecommerce/users/getAll");
        return registrationBean;
    }

}
