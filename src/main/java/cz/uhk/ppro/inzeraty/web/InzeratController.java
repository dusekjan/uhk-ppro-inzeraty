package cz.uhk.ppro.inzeraty.web;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.UlozisteInzeratu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/")
public class InzeratController {

    private UlozisteInzeratu ulozisteInzeratu = null;

    public UlozisteInzeratu getUlozisteInzeratu() {
        return ulozisteInzeratu;
    }

    @Autowired
    public void setUlozisteInzeratu(UlozisteInzeratu ulozisteInzeratu) {
        this.ulozisteInzeratu = ulozisteInzeratu;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "category", required = false) String category, Model model) {
        ModelAndView modelView = new ModelAndView("home");

        if (category != null) {
            modelView.addObject("inzeraty", ulozisteInzeratu.getInzeratyByKategorie(category));
        } else {
            modelView.addObject("inzeraty", ulozisteInzeratu.getInzeraty());
        }
        modelView.addObject("inzerat", new Inzerat());

        return modelView;
    }

    @RequestMapping(params = "akce=add", method = RequestMethod.POST)
    public String novyInzerat(@ModelAttribute Inzerat inzerat, Model model) {
        ulozisteInzeratu.pridej(inzerat);

        return "redirect:/";
    }

    @RequestMapping(value = "editPage", method = RequestMethod.GET)
    public ModelAndView zobrazitUpravuInzeratu(@RequestParam(value = "id") int id, Model model) {
        ModelAndView modelView = new ModelAndView("edit");

        Inzerat newInzerat = new Inzerat();
        newInzerat.setId(id);
        newInzerat.setHesloProUpravu("");

        modelView.addObject("inzerat", newInzerat);

        return modelView;
    }

    @RequestMapping(value = "edit", params = "akce=edit", method = RequestMethod.POST)
    public String upravitInzerat(@ModelAttribute Inzerat inzerat, Model model) {

        if(ulozisteInzeratu.getById(inzerat.getId()).getHesloProUpravu().equals(inzerat.getHesloProUpravu())){
            System.out.println("Majitel inzerátu byl ověřen.");
            ulozisteInzeratu.uprav(inzerat);
        }else {
            System.out.println("Nepodařilo se ověřit majitele inzerátu. Inzerát se nebude měnit.");
        }

        return "redirect:/";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String smazatInzerat(@ModelAttribute Inzerat inzerat, Model model) {

        if(ulozisteInzeratu.getById(inzerat.getId()).getHesloProUpravu().equals(inzerat.getHesloProUpravu())){
            System.out.println("Majitel inzerátu byl ověřen.");
            ulozisteInzeratu.odstran(inzerat.getId());
        }else {
            System.out.println("Nepodařilo se ověřit majitele inzerátu. Inzerát se nebude měnit.");
        }

        return "redirect:/";
    }


}
