package kurierdienst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Stellt die Methode zur Verfügung um wieder zurück zur Startseite(index.jsp) zu kommen. 
 * 
 * @author xorca
 *
 */
@Controller
public class IndexController {
	
	/**
	 * Führt zurück zur Startseite. 
	 * 
	 * @return Gib die Startseite zurück
	 */
	@RequestMapping("/index")
	public ModelAndView zurStartseite() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		
		return mv;
	}

}
