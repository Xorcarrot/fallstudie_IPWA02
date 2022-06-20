package kurierdienst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import kurierdienst.daten.Brief;
import kurierdienst.daten.Paket;
import kurierdienst.daten.Sendung;
import kurierdienst.datenbank.BriefDAO;
import kurierdienst.datenbank.SendungDAO;

/**
 * Controller der seine Aufgaben übernimmt wenn auf die View verfolgung.jsp gewechselt wird.
 * 
 * @author xorca
 *
 */
@RestController
public class VerfolgungController {
	
	/**
	 * Diese Methode wechselt die aktuelle View auf verfolgung.jsp.
	 *  
	 * @return Es wird die View verfolgung.jsp rückgegeben
	 */
	@RequestMapping("/verfolgung")
	public ModelAndView verfolgungWaehlen() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("verfolgung");
		
		return mv;
	}
	
	/**
	 * Diese Methode durchsucht die Sendung's Datenbank nach der übergebenen Referenznummer und gibt dessen Status zurück.
	 * 
	 * @param id Referenznummer des gesuchten Sendungstatus
	 * @return Die View sendungRueckgabe.jsp wird rückgegeben und dazu wird die Information des Status der Sendung beigehengt 
	 */
	@RequestMapping("/sendungSuchen")
	public ModelAndView sendungSuchen(@RequestParam("nummer") int id) {
		
		Sendung sen;
		ModelAndView mv = new ModelAndView();
		
		
		SendungDAO senDAO = new SendungDAO();
		try {
			sen = senDAO.sucheNachPaket(id);
			char status = sen.getStatus();
			String sta = statusSchreiben(status);
			mv.addObject("status", sta);
		} catch (NestedServletException ex) {
			mv.addObject("status", "Sendung wurde nicht in die Datenbank aufgenommen.");
		} catch (NullPointerException ex) {
			mv.addObject("status", "Sendung wurde nicht in die Datenbank aufgenommen.");
		}

		mv.setViewName("sendungRueckgabe");
		return mv;
	}
	
	/**
	 * Diese Methode wandelt den char status in einen für den Kunden lesbaren String.
	 *  
	 * @param status Aktueller Status der Sendung
	 * @return String der je nach Status verschiedene Information rückgibt
	 */
	public static String statusSchreiben(char status) {
		if(status == 'a') {
			return "Ihre Sendung wurde noch nicht abgeholt.";
		} else if(status == 'e') {
			return "Ihre Sendung wurde entgegen genommen.";
		} else if(status == 'i') {
			return "Ihre Sendung befindet sich in Zustellung.";
		} else if(status == 'z') {
			return "Ihre Sendung wurde zugestellt.";
		} else {
			return "Sendung wurde nicht in die Datenbank aufgenommen.";
		}
	}

}
