package cz.uhk.ppro.inzeraty;

import cz.uhk.ppro.inzeraty.model.Inzerat;
import cz.uhk.ppro.inzeraty.sluzby.PametoveUlozisteInzeratu;
import cz.uhk.ppro.inzeraty.sluzby.UlozisteInzeratu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class InzeratyApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(InzeratyApplication.class, args);
    }

    @Bean
    @Primary
    UlozisteInzeratu getUloziste() {
        UlozisteInzeratu ulozisteInzeratu = new PametoveUlozisteInzeratu();
        ulozisteInzeratu.pridej(new Inzerat());
        ulozisteInzeratu.pridej(new Inzerat());
        ulozisteInzeratu.pridej(new Inzerat());
        ulozisteInzeratu.pridej(new Inzerat());
        return ulozisteInzeratu;
    }
}
