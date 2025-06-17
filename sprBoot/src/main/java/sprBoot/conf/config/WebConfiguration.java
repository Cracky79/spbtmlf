package sprBoot.conf.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sprBoot.conf.comm.interceptor.MngrLogInterceptor;
import sprBoot.conf.comm.resolver.ParamUtilHandlerMethodArgumentResolver;

/**
 * cust ParamUtil bean 등록 
 * @author Cracky
 * 2022.06.12
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	

    @Autowired
    public ParamUtilHandlerMethodArgumentResolver paramUtilHandlerMethodArgumentResolver;
    
    @Override
    public void addArgumentResolvers(List <HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(paramUtilHandlerMethodArgumentResolver);
    }
    
    
    /**
     * 리소스 핸들러 추가 ( static 하위 리소스 접근 )
     * 25.06.18
     *  
     */
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}
    
    /**
     * 관리자 로그인 처리 인터셉터 추가
     * 25.06.17
     *  
     */
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MngrLogInterceptor())	
				.addPathPatterns("/mngr/**")
				.excludePathPatterns("/common/css/**", "/common/img/**", "/common/js/**", "/common/font/**", "/common/images/**")		/** 정적 리소스 제외 */
				.excludePathPatterns("/mngr/log/**");	/** mngr/log 를 제외한 나머지는 인터셉터 영향을 받도록 한다. */
	}
}
