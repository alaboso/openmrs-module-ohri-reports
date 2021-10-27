package org.openmrs.module.ohrireports.cohorts.care_and_treatment;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.CARE_AND_TREATMENT_SERVICE_ENROLLMENT_ENCOUNTER_TYPE;
import static org.openmrs.module.ohrireports.OHRIReportsConfig.CLIENTS_ENROLLED_TO_CARE;

@Component
public class ClientsEnrolledToCare implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return CLIENTS_ENROLLED_TO_CARE;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.enrolled.care.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.enrolled.care.reportDescription");
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
		
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(
		    CARE_AND_TREATMENT_SERVICE_ENROLLMENT_ENCOUNTER_TYPE));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
}
