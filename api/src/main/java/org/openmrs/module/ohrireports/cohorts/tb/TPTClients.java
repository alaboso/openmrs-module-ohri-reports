package org.openmrs.module.ohrireports.cohorts.tb;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.BaseCohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConstants.TPT_CLIENTS;
import static org.openmrs.module.ohrireports.OHRIReportsConstants.TPT_PROGRAM_ENROLMENT_ENCOUNTER_TYPE;
import static org.openmrs.module.ohrireports.OHRIReportsConstants.TPT_CASE_ENROLMENT_FORM_UUID;

@Component
public class TPTClients extends BaseCohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return TPT_CLIENTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.tpt.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.tpt.reportDescription");
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		EncounterCohortDefinition cd = (EncounterCohortDefinition) super.constructCohortDefinition();
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(TPT_PROGRAM_ENROLMENT_ENCOUNTER_TYPE));
		cd.addForm(Context.getFormService().getFormByUuid(TPT_CASE_ENROLMENT_FORM_UUID));
		return cd;
	}
	
	@Override
	public CohortDefinition newInstance() {
		return new EncounterCohortDefinition();
	}
}
