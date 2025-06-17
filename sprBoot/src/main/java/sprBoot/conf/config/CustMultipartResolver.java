package sprBoot.conf.config;

import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sprBoot.conf.comm.constant.Constants;

import joy.logsynk.resolver.JMultipartResolver;
import lombok.RequiredArgsConstructor;

/**
 * MultipartResolver 구현 보완 (중복 파일 처리 및 확장자 제어)
 * 멀티 파일 수정 spring boot
 * 확장제 제한 
 * @modify for spring boot 2.01   2025.0613
 * @author cracky
 *
 */
@PropertySource("classpath:multipart.properties") 
@Configuration
public class CustMultipartResolver extends CommonsMultipartResolver {
	
	@Autowired
	private Environment environment;	

    public String getProperty(String key){
        return environment.getProperty(key);
    }
	
	/**
	 * multipart bean 등록 
	 * Joy Multipart resolver override
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		JMultipartResolver multi = new JMultipartResolver(getProperty(Constants.MULTIPART_DIST_FILE_EXT));
		multi.setAppendPattern(getProperty(Constants.MULTIPART_XSS_PATTERN));
		multi.setDefaultEncoding("UTF-8");
		multi.setMaxUploadSize(Long.parseLong(getProperty(Constants.MULTIPART_MAX_UPLOAD_SIZE)));
		multi.setMaxInMemorySize(Integer.parseInt(getProperty(Constants.MULTIPART_MAX_MEMORY_SIZE)));
		multi.setMaxUploadSizePerFile(Long.parseLong(getProperty(Constants.MULTIPART_MAX_UPLOAD_PER_SIZE)));
		return multi;
	}
	/*
	@Bean
    public FilterRegistrationBean multipartFilterRegistrationBean() {
        final MultipartFilter multipartFilter = new MultipartFilter();
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
        return filterRegistrationBean;
    }*/
	
	/**
	 * JMultipartResolver 로 확장자 및 파일 관련 설정을 넘겨 처리 
	 * 25.06.12
	 */
	@Override
	protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
		FileUpload fileUpload = super.newFileUpload(fileItemFactory);
		//fileUpload.setFileSizeMax(1_073_741_824); 
		//fileUpload.setFileCountMax(10_000);
		return fileUpload;
	}
}
