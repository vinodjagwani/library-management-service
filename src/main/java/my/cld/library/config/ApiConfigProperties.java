package my.cld.library.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Getter
@Validated
@AllArgsConstructor
@ConfigurationProperties(prefix = "api-config")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApiConfigProperties {

    // Adding custom config prop

}
