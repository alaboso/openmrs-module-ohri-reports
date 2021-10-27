package org.openmrs.module.ohrireports.cohorts.hts;

import org.openmrs.api.context.Context;
import org.openmrs.module.ohrireports.cohorts.manager.CohortDefinitionManager;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.EncounterCohortDefinition;
import org.openmrs.module.reporting.common.MessageUtil;
import org.springframework.stereotype.Component;

import static org.openmrs.module.ohrireports.OHRIReportsConfig.HTS_CLIENTS;
import static org.openmrs.module.ohrireports.OHRIReportsConfig.HTS_ENCOUNTER_TYPE;

@Component
public class HTS_Clients implements CohortDefinitionManager {
	
	@Override
	public String getUuid() {
		return HTS_CLIENTS;
	}
	
	@Override
	public String getName() {
		return MessageUtil.translate("ohrireports.clients.hts.reportName");
	}
	
	@Override
	public String getDescription() {
		return MessageUtil.translate("ohrireports.clients.hts.reportDescription");
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
		
		cd.addEncounterType(Context.getEncounterService().getEncounterTypeByUuid(HTS_ENCOUNTER_TYPE));
		return cd;
	}
	
	@Override
	public String getVersion() {
		return "0.1";
	}
}
