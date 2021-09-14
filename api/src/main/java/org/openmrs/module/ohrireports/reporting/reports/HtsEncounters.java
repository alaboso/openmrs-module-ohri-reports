package org.openmrs.module.ohrireports.reporting.reports;

import org.openmrs.module.reporting.dataset.definition.SqlDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.manager.ReportManagerUtil;

import java.util.ArrayList;
import java.util.List;

public class HtsEncounters extends OHRIReportsManager {

    private static final String DATA_SET_UUID = "9b26f06c-d38d-4470-abb7-86de417c1877";

    public HtsEncounters() {
    }

    @Override
    public String getUuid() {
        return "d4950f7c-4881-11e7-a919-92ebcb67fe44";
    }

    @Override
    public String getName() {
        return "OHRI HTS Encounters List";
    }

    @Override
    public String getDescription() {
        return "OHRI HTS Encounters List";
    }

    @Override
    public List<Parameter> getParameters() {
        List<Parameter> parameterArrayList = new ArrayList<Parameter>();
        return parameterArrayList;
    }

    @Override
    public ReportDefinition constructReportDefinition() {
        ReportDefinition reportDef = new ReportDefinition();
        reportDef.setUuid(getUuid());
        reportDef.setName(getName());
        reportDef.setDescription(getDescription());
        reportDef.setParameters(getParameters());

        SqlDataSetDefinition sqlDataDef = new SqlDataSetDefinition();
        sqlDataDef.setUuid(DATA_SET_UUID);
        sqlDataDef.setName(getName());
        sqlDataDef.addParameters(getParameters());
        sqlDataDef.setSqlQuery(getSQLQuery());

        reportDef.addDataSetDefinition("listOfHTSEncounters", Mapped.mapStraightThrough(sqlDataDef));

        return reportDef;
    }

    @Override
    public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
        List<ReportDesign> l = new ArrayList<ReportDesign>();
        l.add(ReportManagerUtil.createExcelDesign("99258729-0c4f-5d51-add8-0cf1d1bd7777", reportDefinition));
        return l;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    private String getSQLQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT" +
                "    e.patient_id," +
                "    o.encounter_id," +
                "  (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'ce3816e7-082d-496b-890b-a2b169922c22'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `reason_for_testing`," +
                "   (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '1492AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `ever_tested_for_hiv`," +
                "    (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_text = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '80bcc9c1-e328-47e8-affe-6d1bffe4adf1'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `facility_service_delivery_point`," +

                "  (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '1710AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `client_consent`," +

                "    (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '164401AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `hiv_test_conducted`," +

                "    (SELECT o1.value_datetime" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '164400AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `date_of_hiv_test`," +

                "    (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id AND cn.locale='en' AND cn.locale_preferred=1" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'e16b0068-b6a2-46b7-aba9-e3be00a7b4ab'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `final_hiv_test_result`," +

                "    (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '4fe5857e-c804-41cf-b3c9-0acc1f516ab7'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `recency_test_conducted`," +
                "    (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id AND cn.locale='en' AND cn.locale_preferred=1" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '165092AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `recency_test_result`," +

                "    (SELECT cn.name" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'e8e8fe71-adbb-48e7-b531-589985094d30'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `linked_to_care_and_treatment`," +
                "    (SELECT o1.value_text" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = '162576AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `patient_identification_number`," +

                "    (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '678f3144-302f-493e-ba22-7ec60a84732a'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_adolescent_girls_women`," +
                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '63ea75cb-205f-4e7b-9ede-5f9b8a4dda9f'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_migrant_workers`," +

                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = 'b282bb08-62a7-42c2-9bea-8751c267d13e'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_uniformed_forces`," +

                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '22b202fc-67de-4af9-8c88-46e22559d4b2'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_transgender`," +

                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = 'def00c73-f6d5-42fb-bcec-0b192b5be22d'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_fisher_folk`," +
                "   (SELECT 1" +

                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '8da9bf92-22f6-40be-b468-1ad08de7d457'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_prisoners`," +

                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = 'dc1058ea-4edd-4780-aeaa-a474f7f3a437'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_refugees`," +
                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '160578AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_men_who_have_sex_with_men`," +
                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '160579AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_sex_worker`," +
                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '162198AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_truck_drivers`," +
                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = 'c038bff0-8e33-408c-b51f-7fb6448d2f6c'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_inject_drugs`," +
                "   (SELECT 1" +
                "   FROM openmrs_working.obs o1" +
                "   INNER JOIN openmrs_working.concept c1 ON o1.concept_id=c1.concept_id" +
                "   INNER JOIN openmrs_working.concept c2 ON o1.value_coded = c2.concept_id" +
                "   INNER JOIN concept_name cn ON c2.concept_id = cn.concept_id" +
                "   WHERE o1.encounter_id=o.encounter_id" +
                "     AND c1.uuid = 'd3d4ae96-8c8a-43db-a9dc-dac951f5dcb3'" +
                "     AND c2.uuid = '365371fd-0106-4a53-abc4-575e3d65d372'" +
                "   ORDER BY o1.obs_datetime DESC LIMIT 1) AS `key_pop_have_disabilities`" +
                "FROM openmrs_working.obs o" +
                "INNER JOIN openmrs_working.concept c ON o.concept_id = c.concept_id" +
                "INNER JOIN openmrs_working.encounter e ON o.encounter_id = e.encounter_id" +
                "INNER JOIN openmrs_working.encounter_type et ON e.encounter_type = et.encounter_type_id" +
                "WHERE et.uuid='79c1f50f-f77d-42e2-ad2a-d29304dde2fe'" +
                "GROUP BY o.encounter_id");

        return stringBuilder.toString();
    }
}
