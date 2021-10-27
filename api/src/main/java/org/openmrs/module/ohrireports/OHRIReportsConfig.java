/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.ohrireports;

import org.springframework.stereotype.Component;

/**
 * Contains module's config.
 */
@Component("ohrireports.OHRIReportsConfig")
public class OHRIReportsConfig {
	
	/**
	 * Encounter types
	 */
	public final static String COVID_ASSESSMENT_ENCOUNTER_TYPE = "253a43d3-c99e-415c-8b78-ee7d4d3c1d54";
	
	public final static String CARE_AND_TREATMENT_SERVICE_ENROLLMENT_ENCOUNTER_TYPE = "7e54cd64-f9c3-11eb-8e6a-57478ce139b0";
	
	public final static String HTS_ENCOUNTER_TYPE = "30b849bd-c4f4-4254-a033-fe9cf01001d8";
	
	public final static String COVID_VACCINATION_ENCOUNTER_TYPE = "5b37ce7a-c55e-4226-bdc8-5af04025a6de";
	
	/**
	 * Cohort definitions
	 */
	public final static String CLIENTS_ASSESSED_FOR_COVID = "ec373b01-4ba3-488e-a322-9dd6a50cfdf7";
	
	public final static String CLIENTS_ENROLLED_TO_CARE = "51bec6f7-df43-426e-a83e-c1ae5501372f";
	
	public final static String HTS_CLIENTS = "7c1b4906-1caf-4a8e-a51d-7abdbb896805";
	
	public final static String CLIENTS_VACCINATED_FOR_COVID = "b5d52da9-10c2-43af-ae23-552acc5e445b";
	
	public final static String CLIENTS_WITH_COVID_OUTCOMES = "afb0d950-48fd-44d7-ae2c-79615cd125f0";
	
	public final static String COVID_CLIENTS_WITH_COLLECTED_SAMPLES = "a56b9edb-454a-4524-bc91-f5e3cdd10b6a";
	
	public final static String COVID_CLIENTS_WITH_CONFIRMED_LAB_RESULTS = "0cb7a13d-9088-4be4-9279-51190f9abd1b";
	
	public final static String COVID19_POSITIVE_CLIENTS = "1523b1e5-b6d0-44fb-bd9e-c91bfefb4d1c";
	
	public final static String TODAYZ_APPOINTMENTS = "ccbcf6d8-77b7-44a5-bb43-d352478ea4e9";
	
	/**
	 * Associated Concepts
	 */
	public final static String VACCINATION_DATE = "1410AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	
	public final static String COVID_TREATMENT_OUTCOME = "a845f3e6-4432-4de4-9fff-37fa270b1a06";
	
	public final static String SPECIMEN_COLLECTION_DATE = "159951AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	
	public final static String COVID_LAB_TEST_CONFIRMATION_DATE = "a51c05e1-5ad5-420d-a082-966d2989b716";
	
	public final static String FINAL_COVID19_TEST_RESULT = "5da5c21b-969f-41bd-9091-e40d4c707544";
	
	public final static String RETURN_VISIT_DATE = "5096AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	
}
