package org.ds.webcrawler.reporter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.ds.webcrawler.model.Site;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReporterCsvImpl implements Reporter {

    private String[] FILE_HEADER = {
            "Site URL",
            "Country rank",
            "Global rank",
            "Alexa's page"};
    private char recordSeparator;
    private char delimiter;
    private String reportName;
    CSVFormat csvFormat;

    public ReporterCsvImpl(char recordSeparator, char delimiter, String reportName) {
        this.recordSeparator = recordSeparator;
        this.delimiter = delimiter;
        this.reportName = reportName;
        csvFormat = CSVFormat.DEFAULT.withRecordSeparator(recordSeparator).withDelimiter(delimiter);
    }

    @Override
    public void generateReport(List<Site> sites) throws ReporterException {
        FileWriter fileWriter = null;
        CSVPrinter csvPrinter = null;
        try {
            fileWriter = new FileWriter(reportName);
            csvPrinter = new CSVPrinter(fileWriter, csvFormat);
            csvPrinter.printRecord(FILE_HEADER);

            for(Site site: sites) {
                List siteDataRecord = new ArrayList();
                siteDataRecord.add(site.getSiteUrl());
                siteDataRecord.add(site.getCountryRank());
                siteDataRecord.add(site.getGlobalRank());
                siteDataRecord.add(site.getAlexaLink());
                csvPrinter.printRecord(siteDataRecord);
            }
        } catch (IOException e) {
            throw new ReporterException("Cannot open or write data to CSV file", e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                //NOP
            }
        }

    }
}
