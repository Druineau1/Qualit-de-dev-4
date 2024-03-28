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
		String expectedLine = new String("1,Billi,Beves,Ms,163,27/03/2001");
		IndividuDto dto = new IndividuDto();
		dto.setId((long) 1);
	    dto.setFirstName("Billi");
	    dto.setLastName("Beves");
	    dto.setTitle("Ms");
	    dto.setHeight(163);
	    LocalDate birthDate = LocalDate.of(2001, 03, 27);
        dto.setBirthDate(birthDate);

		//ACT
        String actualLine = IndividuUtils.getLine(dto);
		//ASSERT
        assertThat(actualLine).isEqualTo(expectedLine);
        
        
	}
	@Test
	void testGetIndividu() {
		//Initialiation de la ligne de test ainsi que du format nécessaire pour birthDate
		String expectedLine = new String("1,Billi,Beves,Ms,163,27/03/2001");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//Déclaration d'un individu de type IndividuDto
		IndividuDto individu = IndividuUtils.getIndividu(expectedLine);
		//Test des valeurs 
		assertThat(individu.getId()).isEqualTo(1);
		assertThat(individu.getFirstName()).isEqualTo("Billi");
		assertThat(individu.getLastName()).isEqualTo("Beves");
		assertThat(individu.getTitle()).isEqualTo("Ms");
		assertThat(individu.getHeight()).isEqualTo(163);
		assertThat(individu.getBirthDate().format(formatter)).isEqualTo("27/03/2001");
	}
}

