package org.openmrs.module.ohrireports.cohorts.covid;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.DateObsCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.CLIENTS_VACCINATED_FOR_COVID;
import static org.openmrs.module.ohrireports.OHRIReportsConfig.COVID_VACCINATION_ENCOUNTER_TYPE;
import static org.openmrs.module.ohrireports.OHRIReportsConfig.VACCINATION_DATE;

@Component
public class ClientsVaccinatedForCOVID implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return CLIENTS_VACCINATED_FOR_COVID;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.vaccinated.covid.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.vaccinated.covid.reportDescription");
	}
	
	@Override
	public Boolean isActivated() {
		return true;
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		DateObsCohortDefinition cd = new DateObsCohortDefinition();
		cd.setUuid(getUuid());
		cd.setName(getName());
		cd.setDescription(getDescription());
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(COVID_VACCINATION_ENCOUNTER_TYPE));
		cd.setQuestion(Context.getConceptService().getConceptByUuid(VACCINATION_DATE));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
}
