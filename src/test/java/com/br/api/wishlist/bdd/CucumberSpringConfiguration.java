package com.br.api.wishlist.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class CucumberSpringConfiguration {
    // Apenas marca a configuração do contexto do Spring para os testes do Cucumber
}
