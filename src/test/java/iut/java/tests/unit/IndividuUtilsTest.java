package iut.java.tests.unit;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import iut.java.dto.IndividuDto;
import iut.java.utils.IndividuUtils;

public class IndividuUtilsTest {
	
	@Test
	public void testGetLine() {
		//ARRANGE
		String expectedLine = new String("1,Jehu,Twomey,Honorable,168,04/07/1978");
		IndividuDto dto = new IndividuDto();
		dto.setId((long) 1);
	    dto.setFirstName("Jehu");
	    dto.setLastName("Twomey");
	    dto.setTitle("Honorable");
	    dto.setHeight(168);
	    LocalDate birthDate = LocalDate.of(1978, 07, 04);
        dto.setBirthDate(birthDate);

		//ACT
        String actualLine = IndividuUtils.getLine(dto);
		//ASSERT
        assertThat(actualLine).isEqualTo(expectedLine);
      
        
	}
	@Test
	void testGetIndividu() {
		//Initialiation de la ligne de test ainsi que du format nécessaire pour birthDate
		String expectedLine = new String("1,Jehu,Twomey,Honorable,168,04/07/1978");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//Déclaration d'un individu de type IndividuDto
		IndividuDto individu = IndividuUtils.getIndividu(expectedLine);
		//Test des valeurs 
		assertThat(individu.getId()).isEqualTo(1);
		assertThat(individu.getFirstName()).isEqualTo("Jehu");
		assertThat(individu.getLastName()).isEqualTo("Twomey");
		assertThat(individu.getTitle()).isEqualTo("Honorable");
		assertThat(individu.getHeight()).isEqualTo(168);
		assertThat(individu.getBirthDate().format(formatter)).isEqualTo("04/07/1978");
	}
}

