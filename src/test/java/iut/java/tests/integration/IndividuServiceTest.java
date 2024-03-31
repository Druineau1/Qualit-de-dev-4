package iut.java.tests.integration;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import iut.java.dto.IndividuDto;
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
	
	@Test
	public void testLoadedIndividusName() {
		//Act Appel de la méthode getIndividusList pour charger les individus 
		List<IndividuDto> list = service.getIndividusList();
		//Assert Vérification nom attendu
		assertThat(list.get(0).getLastName()).startsWith("Twomey");
		assertThat(list.get(1).getLastName()).startsWith("Snoden");
		assertThat(list.get(2).getLastName()).startsWith("Pendlebury");
		assertThat(list.get(3).getLastName()).startsWith("Tumbelty");
		assertThat(list.get(4).getLastName()).startsWith("Robertz");
		
	}
	
	@Test
	public void testLoadedIndividusTitle() {
		// Liste des titres attendus
		List<String> listTitle = List.of("Honorable", "Mr", "Ms", "Mrs", "Dr", "Rev");

	    // Vérification des titres des individus chargés
		assertThat(service.getIndividusList().stream().map(IndividuDto::getTitle)).containsOnly(listTitle.toArray(new String[0]));
	}
	
	@Test
	public void testLoadedIndividusBirthDate() {
		//Création des variables qui contiennent les nombres attendus d'individus en fonction des conditions
		int sansDateAttendu =4;
		int avant2000Attendu =35;
		int apres2000Attendu =11;
		// Récupération du nombre de personnes remplissant les différentes conditions
	    List<IndividuDto> individusList = service.getIndividusList();
	    long nombreIndividuSansDate = individusList.stream().filter(i -> i.getBirthDate() == null).count();
	    long nombreIndividuAvant2000 = individusList.stream().filter(i -> i.getBirthDate() != null && i.getBirthDate().isBefore(LocalDate.of(2000, 1, 1))).count();
	    long nombreIndividuApres2000 = individusList.stream().filter(i -> i.getBirthDate() != null && i.getBirthDate().isAfter(LocalDate.of(2000, 1, 1))).count();

	    // Vérifications du nombres d'individus
	    assertThat(nombreIndividuSansDate).isEqualTo(sansDateAttendu);
	    assertThat(nombreIndividuAvant2000).isEqualTo(avant2000Attendu);
	    assertThat(nombreIndividuApres2000).isEqualTo(apres2000Attendu);
	
	}
	@Test
	public void testLoadedIndividusTitleRepartition() {
		// Récupération des titres
	    Map<String, Long> titleRepartition = service.getTitleRepartition();
	    long attenduHonorable=12l;
	    long attenduMr=7l;
	    long attenduMs=9l;
	    long attenduMrs=7l;
	    long attenduDr=12l;
	    long attenduRev=3l;

	    // Vérification du nombres de titres
	    assertThat(titleRepartition)
	        .containsOnly(
	            entry("Honorable",attenduHonorable),
	            entry("Mr", attenduMr),
	            entry("Ms", attenduMs),
	            entry("Mrs", attenduMrs),
	            entry("Dr", attenduDr),
	            entry("Rev", attenduRev)
	        );
	}
}
