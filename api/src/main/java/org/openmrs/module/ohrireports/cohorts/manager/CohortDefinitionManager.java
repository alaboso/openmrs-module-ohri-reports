package org.openmrs.module.ohrireports.cohorts.manager;

import org.openmrs.module.reporting.cohort.definition.CohortDefinition;

/**
 * This is the base interface that should be implemented by any cohort definition author who wishes
 * their CohortDefinition to be automatically installed upon startup.
 */
public interface CohortDefinitionManager {
	
	/**
	 * @return cohortDefinition uuid
	 */
	public String getUuid();
	
	/**
	 * @return cohortDefinition name
	 */
	public String getName();
	
	/**
	 * @return cohortDefinition description
	 */
	public String getDescription();
	
	/**
	 * Determines whether the cohort definition should be setup
	 */
	public Boolean isActivated();
	
	/**
	 * @return the cohortDefinition
	 */
	public CohortDefinition constructCohortDefinition();
	
	public String getVersion();
}
