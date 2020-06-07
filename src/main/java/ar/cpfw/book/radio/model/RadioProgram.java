package ar.cpfw.book.radio.model;

public interface RadioProgram {

	Iterable<RadioCompetition> availableCompetitions();

	void addInscription(int idCompetition, Competitor c);
}
