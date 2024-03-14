package org.openmrs.module.ohrireports.cohorts.tb;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.BaseCohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConstants.TB_CLIENTS;
import static org.openmrs.module.ohrireports.OHRIReportsConstants.TB_PROGRAM_ENROLMENT_ENCOUNTER_TYPE;

@Component
public class TBClients extends BaseCohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return TB_CLIENTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.tb.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.tb.reportDescription");
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
	
	@Override
	public CohortDefinition constructCohortDefinition() {
		EncounterCohortDefinition cd = (EncounterCohortDefinition) super.constructCohortDefinition();
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(TB_PROGRAM_ENROLMENT_ENCOUNTER_TYPE));
		return cd;
	}
	
	@Override
	public CohortDefinition newInstance() {
		return new EncounterCohortDefinition();
	}
}
