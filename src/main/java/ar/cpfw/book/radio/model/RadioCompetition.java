package ar.cpfw.book.radio.model;

import java.time.LocalDateTime;

public interface RadioCompetition {

	int id();

	String description();
	
 String rules();

 LocalDateTime startDate();

 LocalDateTime inscriptionStartDate();

 LocalDateTime inscriptionEndDate();

}
