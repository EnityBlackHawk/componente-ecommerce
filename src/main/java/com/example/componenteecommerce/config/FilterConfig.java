package com.example.componenteecommerce.config;

import com.example.componenteecommerce.filter.AdminUserFilter;
import com.example.componenteecommerce.filter.AnyUserFilter;
import com.example.componenteecommerce.filter.TokenFilter;
import com.example.componenteecommerce.service.AuthClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AnyUserFilter> tokenFilter(AuthClient authClient) {
        FilterRegistrationBean<AnyUserFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AnyUserFilter(authClient));
        registrationBean.addUrlPatterns("/ecommerce/orders/create");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminUserFilter> adminUserFilter(AuthClient authClient) {
        FilterRegistrationBean<AdminUserFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminUserFilter(authClient));
        registrationBean.addUrlPatterns("/ecommerce/users/getAll");
        registrationBean.addUrlPatterns("/ecommerce/orders/getAll");
        return registrationBean;
    }

}
