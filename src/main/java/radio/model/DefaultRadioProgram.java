package radio.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import radio.model.ports.CompetitionRepository;
import radio.model.ports.Competitor;
import radio.model.ports.RadioCompetition;
import radio.model.ports.RadioException;
import radio.model.ports.RadioProgram;

public class DefaultRadioProgram implements RadioProgram {

	private CompetitionRepository repository;

	public DefaultRadioProgram(CompetitionRepository repository) {
		this.repository = repository;
	}

	@Override
	public Iterable<RadioCompetition> availableCompetitions() {
		List<RadioCompetition> cs = repository.competitionsForInscription();

		return cs.stream().map(d -> new RadioCompetition() {
			@Override
			public int id() {
				return d.id();
			}

			@Override
			public String description() {
				return d.description();
			}

   @Override
   public String rules() {
    return d.rules();
   }

   @Override
   public LocalDateTime startDate() {
    return d.startDate();
   }

   @Override
   public LocalDateTime inscriptionStartDate() {
    return d.inscriptionStartDate();
   }

   @Override
   public LocalDateTime inscriptionEndDate() {
    return d.inscriptionEndDate();
   }
		}).collect(Collectors.toList());
	}

	@Override
	public void addInscription(int idCompetition, Competitor competitor) {
		repository.competitionBy(idCompetition)
				.orElseThrow(() -> new RadioException(
						"Selected competition does not exists..."));

		Competitor c = new DefaultCompetitor(competitor.id(),
				competitor.name(), competitor.lastName(),
				competitor.email(), competitor.phone());

		repository.addInscription(c.name(), c.lastName(), c.id(),
				c.phone(), c.email(), idCompetition);

	}
}
