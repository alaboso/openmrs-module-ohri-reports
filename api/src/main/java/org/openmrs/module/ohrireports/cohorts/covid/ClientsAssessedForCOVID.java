package org.openmrs.module.ohrireports.cohorts.covid;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.CLIENTS_ASSESSED_FOR_COVID;
import static org.openmrs.module.ohrireports.OHRIReportsConfig.COVID_ASSESSMENT_ENCOUNTER_TYPE;

@Component
public class ClientsAssessedForCOVID implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return CLIENTS_ASSESSED_FOR_COVID;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.accessed.covid.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.accessed.covid.reportDescription");
	}
	
	@Override
	public Boolean isActivated() {
		return true;
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		EncounterCohortDefinition cd = new EncounterCohortDefinition();
		cd.setUuid(getUuid());
		cd.setName(getName());
		cd.setDescription(getDescription());
		
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(COVID_ASSESSMENT_ENCOUNTER_TYPE));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
	
}
