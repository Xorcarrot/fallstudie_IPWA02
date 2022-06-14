package kurierdienst.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kurierdienst.daten.Brief;
import kurierdienst.daten.Briefmarke;
import kurierdienst.daten.Empfaenger;
import kurierdienst.daten.Paket;
import kurierdienst.daten.Sendung;
import kurierdienst.datenbank.EmpfaengerDAO;
import kurierdienst.datenbank.SendungDAO;
import kurierdienst.datenbank.BriefDAO;

/**
 * Controller der übernimmt wenn auf View sendung.jsp gewechselt wird
 * 
 * @author xorca
 *
 */
@Controller
public class SendungController {
	
	/**
	 * Diese Methode übernimmt die Aufgabe um auf die View sendung.jsp zu wechseln. 
	 * 
	 * @return Die View sendung.jsp
	 */
	@RequestMapping("/sendung")
	public ModelAndView sendungErstellen() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sendung");
		
		return mv;       
	}
	
	/**
	 * Diese Methode übernimmt das Formular der sendung.jsp zum erstellung einer Sendung und dem dazugehörigen Empfänger. Sie generiert je nach Auswahl ein Paket 
	 * oder einen Brief. Sendung und Empfänger werden auf der Datenbank hinterlegt.
	 * 
	 * @param paket Param zum erkennen ob es sich um ein Paket handelt
	 * @param laenge Länge der Sendung
	 * @param breite Breite der Sendung
	 * @param hoehe Höhe der Sendung
	 * @param gewicht Gewicht der Sendung 
	 * @param vorname Vorname des Empfängers
	 * @param nachname Nachname des Empfängers 
	 * @param land Land des Empfängers 
	 * @param wohnort Wohnort des Empfängers
	 * @param postleitzahl Postleitzahl des Empfängers
	 * @param adresse Adresse des Empfängers
	 * @return Gibt die View bestaetigt.jsp zurück, mit den angehengden Information: neu generierte Referenznummer, Gesamtpreis der Sendung und Anzahl der benötigten Briefmarken
	 */
	@RequestMapping("/sendungErstellen")
	public ModelAndView sendungBestaetigen(@RequestParam("paket") String paket, @RequestParam("laenge") int laenge, @RequestParam("breite") int breite, @RequestParam("hoehe") int hoehe, @RequestParam("gewicht") int gewicht, @RequestParam("vorname") String vorname, @RequestParam("nachname") String nachname, @RequestParam("land") String land, @RequestParam("wohnort") String wohnort, @RequestParam("postleitzahl") int postleitzahl, @RequestParam("adresse") String adresse) {		
		
	Empfaenger emp = new Empfaenger(vorname, nachname, land, wohnort, postleitzahl, adresse);
	EmpfaengerDAO empDAO = new EmpfaengerDAO();
	Briefmarke marke = new Briefmarke();
	
	Sendung sen;
	
	String preis;
	String anzahlMarken;
	int empfaengerVorhanden = empDAO.sucheEmpfaengerNummer(emp);
		
		if(paket.equals("paket")) {
			sen = new Paket(laenge, breite, hoehe, gewicht);
			preis = marke.preisAusgeben(marke.preisBerechnenPaket((Paket) sen));
			anzahlMarken = marke.anzahlBriefmarken(marke.preisBerechnenPaket((Paket) sen));
			
			if(empfaengerVorhanden >= 1) {
				
				empDAO.sendungUpdaten(sen, empfaengerVorhanden);
				
			} else {
			
				emp.getSendung().add(sen);
				sen.setEmp(emp);
				empDAO.speichern(emp, sen);
				
			}
			
		} else {
			sen = new Brief(laenge, breite, hoehe, gewicht);
			preis = marke.preisAusgeben(marke.preisBerechnenBrief((Brief) sen));
			anzahlMarken = marke.anzahlBriefmarken(marke.preisBerechnenBrief((Brief) sen));
			
			if(empfaengerVorhanden >= 1) {
				
				empDAO.sendungUpdaten(sen, empfaengerVorhanden);
				
			} else {
			
				emp.getSendung().add(sen);
				sen.setEmp(emp);
				empDAO.speichern(emp, sen);
			
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("preis", preis);
		mv.addObject("anzahlMarken", anzahlMarken);
		mv.addObject("nummer", sen.getReferenznummer());
		mv.setViewName("bestaetigt");
		
		return mv;
	}

}
