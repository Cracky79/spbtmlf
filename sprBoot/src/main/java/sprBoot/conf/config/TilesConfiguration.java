package sprBoot.conf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * 타일즈 Configuration 및 bean 등록
 * 2025.06.11 
 * @author Cracky
 *
 */
@Configuration
public class TilesConfiguration {
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"classpath:/tiles/tiles3.xml"});		// tiles3
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	/**
	 * resolver
	 * @return
	 */
	@Bean
	public UrlBasedViewResolver tilesViewResolver(){
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
}
