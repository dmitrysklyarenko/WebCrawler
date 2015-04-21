package org.ds.webcrawler.service;

import org.ds.webcrawler.htmlparser.HtmlParserException;
import org.ds.webcrawler.reporter.ReporterException;
import org.ds.webcrawler.reporter.ReporterType;

import java.io.IOException;
import java.util.List;

public interface CrawlerService {

    public void generateReportWithCountry(String country) throws ReporterException, IOException, HtmlParserException;

    public void generateReportWithCountries(List<String> countries) throws HtmlParserException, ReporterException;

    public void setCountOfSites(int countOfSites);

    public void setReporterType(ReporterType reporterType);

}
