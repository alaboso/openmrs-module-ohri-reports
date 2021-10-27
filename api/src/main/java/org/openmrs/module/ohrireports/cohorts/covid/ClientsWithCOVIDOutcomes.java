package org.openmrs.module.ohrireports.cohorts.covid;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.*;

@Component
public class ClientsWithCOVIDOutcomes implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return CLIENTS_WITH_COVID_OUTCOMES;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.covid.outcomes.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.covid.outcomes.reportDescription");
	}
	
	@Override
	public Boolean isActivated() {
		return true;
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		CodedObsCohortDefinition cd = new CodedObsCohortDefinition();
		cd.setUuid(getUuid());
		cd.setName(getName());
		cd.setDescription(getDescription());
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(COVID_ASSESSMENT_ENCOUNTER_TYPE));
		cd.setQuestion(Context.getConceptService().getConceptByUuid(COVID_TREATMENT_OUTCOME));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
}
