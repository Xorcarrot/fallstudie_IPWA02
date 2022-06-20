package kurierdienst.einstellungen;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Einstellung für das MVC-Spring Framework und dessen Front-Controller.
 * 
 * @author xorca
 *
 */
public class Initalisierung extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/**
	 * Generiert die RootConfig Klasse
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	/**
	 * Generiert die ServletConfig Klasse
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfig.class};
	}
	
	/**
	 * Löst die web.xml ab und wird aktiv bei allen Views die nach einem / sind
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

}
