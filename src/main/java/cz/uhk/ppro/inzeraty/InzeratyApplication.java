package cz.uhk.ppro.inzeraty;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.PametoveUlozisteInzeratu;
import cz.uhk.ppro.inzeraty.sluzby.UlozisteInzeratu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;

@SpringBootApplication
public class InzeratyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(InzeratyApplication.class, args);
    }

    @Bean
    @Primary
    UlozisteInzeratu getUloziste() {
        UlozisteInzeratu ulozisteInzeratu = new PametoveUlozisteInzeratu();
        ulozisteInzeratu.pridej(new Inzerat("nakup", "Koupim byt v Praze", new BigDecimal(5000000)));
        ulozisteInzeratu.pridej(new Inzerat("nakup", "Koupim byt v Brne", new BigDecimal(2000000)));
        ulozisteInzeratu.pridej(new Inzerat("prodej", "Prodam kralika s papirama", new BigDecimal(5000)));
        ulozisteInzeratu.pridej(new Inzerat("prodej", "Prodam pytel brambor", new BigDecimal(50)));
        ulozisteInzeratu.pridej(new Inzerat("vymena", "Vymenim iphone 12 za iphone 13", new BigDecimal(0)));
        ulozisteInzeratu.pridej(new Inzerat("vymena", "Vymenim pocitatc s Windows za Linux", new BigDecimal(0)));
        ulozisteInzeratu.pridej(new Inzerat("vymena", "Vymenim cokoliv do 1000,-", new BigDecimal(1000)));
        return ulozisteInzeratu;
    }
}
