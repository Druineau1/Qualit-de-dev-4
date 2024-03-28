package iut.java.tests.integration;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.java.service.IndividuService;

public class IndividuServiceTest {

	private IndividuService service;
	
	@BeforeEach
	public void before() {
		// Arrange Création d'une instance de IndividuService avec un fichier spécifique
		service = new IndividuService("DruineauThomas.csv");
}

	@Test
	public void testCountLoadedIndividus() {
		// Déclaration de la valeur attendu
        int attendu =50;
        //Vérification du nombre d'individus chargés
        
       assertThat(service.getIndividusList()).hasSize(attendu);

		
	}
}
