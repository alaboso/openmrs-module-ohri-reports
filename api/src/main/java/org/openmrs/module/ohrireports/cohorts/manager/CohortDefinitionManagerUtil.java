package org.openmrs.module.ohrireports.cohorts.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;

/**
 * This class contains the logic necessary to set-up and tear down a cohort definition defined
 * through a CohortManager
 */
public class CohortDefinitionManagerUtil {
	
	private static final Log log = LogFactory.getLog(CohortDefinitionManagerUtil.class);
	
	/**
	 * Convenience method that can be used to automatically setup all cohort definitions. It
	 * typically loads all registered components of type {@link CohortDefinitionManager} with
	 * exception of the inactivated cohort definitions.
	 */
	public static void setUpCohortsDefinitions() {
		for (CohortDefinitionManager manager : Context.getRegisteredComponents(CohortDefinitionManager.class)) {
			setCohortDefinition(manager);
		}
	}
	
	/**
	 * Sets up a single cohort definition, overwriting the existing one if the version changes or if
	 * version is <code>SNAPSHOT</code>
	 * 
	 * @param cohortDefinitionManager the manager holding the cohort definition
	 */
	private static void setCohortDefinition(CohortDefinitionManager cohortDefinitionManager) {
		CohortDefinition incomingDef = cohortDefinitionManager.constructCohortDefinition();
		CohortDefinitionService service = Context.getService(CohortDefinitionService.class);
		CohortDefinition existingDef = service.getDefinitionByUuid(incomingDef.getUuid());
		String gpName = "ohri-reports.cohortManager." + cohortDefinitionManager.getUuid() + ".version";
		GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(gpName);
		if (gp == null) {
			gp = new GlobalProperty(gpName, "");
		}
		if (existingDef != null && !isSnapshotOrVersionChanged((String) gp.getValue(), cohortDefinitionManager.getVersion())) {
			// At this point the version didn't change, skip to the next.
			return;
		}
		if (existingDef != null) {
			log.info("Overwriting existing CohortDefinition");
			incomingDef.setId(existingDef.getId());
			try {
				Context.evictFromSession(existingDef);
			}
			catch (IllegalArgumentException ex) {
				// intentionally ignored as per REPORT-802
			}
		} else {
			log.info("Creating new CohortDefinition");
		}
		service.saveDefinition(incomingDef);
		gp.setPropertyValue(cohortDefinitionManager.getVersion());
		Context.getAdministrationService().saveGlobalProperty(gp);
	}
	
	private static Boolean isSnapshotOrVersionChanged(String currentVersion, String incomingVersion) {
		return incomingVersion.contains("SNAPSHOT") || !currentVersion.equals(incomingVersion);
	}
}
