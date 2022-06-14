package kurierdienst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import kurierdienst.daten.Sendung;
import kurierdienst.daten.SendungAbholung;
import kurierdienst.datenbank.SendungDAO;

/**
 * Übernimmt die Kontrolle wenn man auf die Seite kurier.jps welchselt.
 * 
 * @author xorca
 *
 */
@Controller
public class KurierController {
	
	/**
	 * Wenn die View auf kurier.jps gewechselt wird, setzt diese Methode ein. Sie generiert automatisch drei Listen die sortiert sind nach akutellem Status
	 * und giebt dies weiter an die ModelAndView Klasse. 
	 * 
	 * @return ModelAndView die auf kurier.jsp Seite navigiert und der drei Listen beigefügt sind. 
	 */
	@RequestMapping("/kurier")
	public ModelAndView kurierView() {
		
		List<SendungAbholung> listSendungAbholung = this.alleAbholbereitenSendungenAusgeben('a');
		List<Sendung> listSendungEntgegengenommen = this.alleSendungenAusgebenNachStatus('e');
		List<Sendung> listSendungInZustellung = this.alleSendungenAusgebenNachStatus('i');
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("listAbh", listSendungAbholung);
		mv.addObject("listEnt", listSendungEntgegengenommen);
		mv.addObject("listZus", listSendungInZustellung);
		mv.setViewName("kurier");
		
		return mv;
	}
	
	/**
	 * Diese Mehode generiert eine Liste aus SendungAbholung Objekten. Diese Objekte werden aus der Datenbank genommen.
	 * 
	 * @param status Es werden nur die Sendungen aus der Datenbank geholt die den angegeben Status besitzen
	 * @return Eine Liste aus SendungAbholung Objekten 
	 */
	public List<SendungAbholung> alleAbholbereitenSendungenAusgeben(char status) {
		
		List<SendungAbholung> abhList = new ArrayList<SendungAbholung>();
		SendungDAO sDAO = new SendungDAO();
		List<Sendung> allSen = sDAO.allePaketeSuchen();
		
		for(Sendung s : allSen) {
			if(s.getStatus() == status) {
				SendungAbholung sA = new SendungAbholung(s.getReferenznummer(), s.getLaenge(), s.getBreite(), s.getHoehe(), s.getGewicht());
				abhList.add(sA);
			}
		}
		
		return abhList;
	}
	
	/**
	 * Diese Methode generiert eine Liste aus Sendung Objekten. Sie werden aus der Datenbank geladen
	 * 
	 * @param status Es werden nur die Sendungen aus der Datenbank geladen die den angegeben Status besitzen 
	 * @return Eine Liste aus Sendung Objekten
	 */
	public List<Sendung> alleSendungenAusgebenNachStatus(char status) {
		
		List<Sendung> statusList = new ArrayList<Sendung>();
		SendungDAO sDAO = new SendungDAO();
		List<Sendung> allSen = sDAO.allePaketeSuchen();
		
		for(Sendung s : allSen) {
			if(s.getStatus() == status) {
				statusList.add(s);
			}
		}
		
		return statusList;
	}
	
	/**
	 * Methode um die Sendung Objekte in den nächsten Status weiterzuschalten : 'a'->'e'->'i'->'z'
	 * 
	 * @param id Ist die Referenznummer um welche Sendung es sich handelt 
	 * @return Lädt die Seite neu mit den aktuellen und veränderten Objekten
	 */
	@RequestMapping("/senAbholen")
	public ModelAndView sendungAbholen(@RequestParam("abgeholt")int id) {
		
		if(id > 0) {
			SendungDAO sDAO = new SendungDAO();
			sDAO.statusWeiterschalten(id);
		}
		
		ModelAndView mv = this.kurierView();
		return mv;

	}

}
