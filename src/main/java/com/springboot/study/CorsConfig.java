package com.springboot.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 1、允许任何域名使用 2、允许任何头 3、允许任何方法（post、get等）
 * 
 * @ClassName: CorsConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019年1月15日
 *
 */
@Configuration
public class CorsConfig {

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 1
		corsConfiguration.addAllowedOrigin("*");
		// 2
		corsConfiguration.addAllowedHeader("*");
		// 3
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 4
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

}
