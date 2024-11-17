package my.cld.library.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ApiConfigProperties.class})
public class AppConfig {

    // Define custom beans here.


}
