package radio.model;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository {

	Optional<RadioCompetition> competitionBy(int id);
	
	void addInscription(String name, String lastName, String id,
			String phone, String email, int idCompetition)
			throws RadioException;

	List<RadioCompetition> competitionsForInscription()
			throws RadioException;
}
