package kurierdienst.einstellungen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * ServletConfig für die Klasse initalisierung.java
 * 
 * @author xorca
 *
 */
@Configuration
@ComponentScan({ "kurierdienst.controller" })
public class ServletConfig {
	
	/**
	 * Fügt der View immer das Suffix .jsp hinzu.
	 * 
	 * @return 
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		
		vr.setSuffix(".jsp");
		
		return vr;
	}
	
}
