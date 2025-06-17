package sprBoot.conf.config;

import java.util.Locale;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * 메세지 소스 bean 등록
 * 2025.06.16 
 * @author Cracky
 *
 */
@Configuration
@EnableWebMvc
public class MessageConfiguration {

	/*
     * 사용자 언어 환경을 설정해주기 위한 bean 설정
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.KOREAN);      // <---- 해당 값을 수정하여 언어 결정
        return sessionLocaleResolver;
    }
    
    /**
     * 메세지 소스 prop
     * @return
     */
    @Bean 
    public MessageSource messageSource() { 
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource(); 
    	messageSource.setBasenames("message/message-common"); 
    	messageSource.setDefaultEncoding("UTF-8"); 
    	return messageSource; 
    }
	
}
