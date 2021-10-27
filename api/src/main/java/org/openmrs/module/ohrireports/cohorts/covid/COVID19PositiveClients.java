package org.openmrs.module.ohrireports.cohorts.covid;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.*;

@Component
public class COVID19PositiveClients implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return COVID19_POSITIVE_CLIENTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.covid.positive.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.covid.positive.reportDescription");
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
		cd.setQuestion(Context.getConceptService().getConceptByUuid(FINAL_COVID19_TEST_RESULT));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
}
