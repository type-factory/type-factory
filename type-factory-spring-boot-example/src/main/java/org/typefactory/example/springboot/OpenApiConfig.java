package org.typefactory.example.springboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = OpenApiConfig.class)
public class OpenApiConfig {
}
