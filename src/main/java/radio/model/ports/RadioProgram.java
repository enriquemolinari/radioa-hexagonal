package radio.model.ports;

public interface RadioProgram {

	Iterable<RadioCompetition> availableCompetitions();

	void addInscription(int idCompetition, Competitor c);
}
