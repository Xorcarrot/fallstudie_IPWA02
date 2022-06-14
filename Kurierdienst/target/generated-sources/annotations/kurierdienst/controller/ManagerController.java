package kurierdienst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kurierdienst.daten.Sendung;
import kurierdienst.daten.SendungAbholung;
import kurierdienst.daten.SendungManager;
import kurierdienst.datenbank.SendungDAO;

/**
 * Übernimmt die Kontrolle wenn auf die View manger.jsp gewechselt wird. 
 * 
 * @author xorca
 *
 */
@Controller
public class ManagerController {

	/**
	 * Methode die beim wechseln auf die manager.jsp View aktiviert wird. Sie erzeugt vier Listen die dann vom der .jsp Seite
	 * ausgelesen werden könnnen.
	 * 
	 * @return Die View manager.jsp mit vier Listen angehängt
	 */
	@RequestMapping("/manager")
	public ModelAndView kurierView() {
		
		List<SendungManager> listSendungAbholung = this.statuslisteManagerErstellen('a');
		List<SendungManager> listSendungEntgegengenommen = this.statuslisteManagerErstellen('e');
		List<SendungManager> listSendungInZustellung = this.statuslisteManagerErstellen('i');
		List<SendungManager> listSendungZugestellt = this.statuslisteManagerErstellen('z');
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("listAbh", listSendungAbholung);
		mv.addObject("listEnt", listSendungEntgegengenommen);
		mv.addObject("listZus", listSendungInZustellung);
		mv.addObject("listZug", listSendungZugestellt);
		mv.setViewName("manager");
		
		return mv;
	}
	
	/**
	 * Diese Methode erzeugt eine Liste die nur die Sendungen enthält die auch den selben Status haben wie im Parameter status angegeben.
	 * 
	 * @param status Parameter welches die definiert welche Sendung Objekte geladen werden sollen
	 * @return Eine Liste aus SendungManager Objekten
	 */
	public List<SendungManager> statuslisteManagerErstellen(char status) {
		
		List<SendungManager> manList = new ArrayList<SendungManager>();
		SendungDAO sDAO = new SendungDAO();
		List<Sendung> allSen = sDAO.allePaketeSuchen();
		
		for(Sendung s : allSen) {
			if(s.getStatus() == status) {
				SendungManager sM = new SendungManager(s.getReferenznummer(), s.getStatus());
				manList.add(sM);
			}
		}
		
		return manList;
		
	}
	
}
