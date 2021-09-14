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
//        reportDef.setUuid(getUuid());
//        reportDef.setName(getName());
//        reportDef.setDescription(getDescription());
//        reportDef.setParameters(getParameters());

        SqlDataSetDefinition sqlDataDef = new SqlDataSetDefinition();
//        sqlDataDef.setUuid(DATA_SET_UUID);
//        sqlDataDef.setName(getName());
//        sqlDataDef.addParameters(getParameters());
//        sqlDataDef.setSqlQuery(getSQLQuery());

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

    private String getSQLQuery(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from analysis.tbl_hts_encounters");
        return stringBuilder.toString();
    }
}
