package kurierdienst.einstellungen;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * RootConfig für die Klasse initalisierung.java
 * 
 * @author xorca
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("kurierdienst.einstellungen")
public class RootConfig extends WebMvcConfigurerAdapter {

}
