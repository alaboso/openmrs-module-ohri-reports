package org.openmrs.module.ohrireports.cohorts.covid;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.BaseObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.DateObsCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.*;

@Component
public class COVIDClientsWithConfirmedLabResults implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return COVID_CLIENTS_WITH_CONFIRMED_LAB_RESULTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.covid.confirmed.lab.results.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.covid.confirmed.lab.results.reportDescription");
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
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(COVID_ASSESSMENT_ENCOUNTER_TYPE));
		cd.setQuestion(Context.getConceptService().getConceptByUuid(COVID_LAB_TEST_CONFIRMATION_DATE));
		cd.setTimeModifier(BaseObsCohortDefinition.TimeModifier.LAST);
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
}
