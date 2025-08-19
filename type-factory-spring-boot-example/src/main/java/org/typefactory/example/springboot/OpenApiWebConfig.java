package org.typefactory.example.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiWebConfig implements WebMvcConfigurer {

  private static final String OPENAPI_UI_PATH = "openapi-ui/index.html?url=/v3/api-docs.yaml";

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", OPENAPI_UI_PATH);
    registry.addRedirectViewController("/openapi-ui", OPENAPI_UI_PATH);
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/openapi-ui/**").addResourceLocations("classpath:/META-INF/openapi-ui/");
  }
}
