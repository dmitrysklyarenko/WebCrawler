package org.ds.webcrawler.service;

import org.ds.webcrawler.htmlparser.HtmlParser;
import org.ds.webcrawler.htmlparser.HtmlParserException;
import org.ds.webcrawler.model.Site;
import org.ds.webcrawler.reporter.ReporterException;
import org.ds.webcrawler.reporter.ReporterFactory;
import org.ds.webcrawler.reporter.ReporterType;
import org.ds.webcrawler.reporter.Reporter;

import java.util.List;

public class CrawlerServiceImpl implements CrawlerService {

    private int countOfSites;
    private HtmlParser htmlParser;
    private ReporterFactory reporterFactory;

    public CrawlerServiceImpl(int countOfSites) {
        this.countOfSites = countOfSites;
    }

    @Override
    public void generateReportWithCountry(String country) throws ReporterException,  HtmlParserException {
        Reporter reporter = reporterFactory.getReporter();
        List<Site> sites = htmlParser.getListOfSitesByCountry(country, countOfSites);
        reporter.generateReport(sites);
    }

    @Override
    public void generateReportWithCountries(List<String> countries) throws HtmlParserException, ReporterException {
        Reporter reporter = reporterFactory.getReporter();
        List<Site> sites = htmlParser.getListOfSitesByCountries(countries, countOfSites);
        reporter.generateReport(sites);
    }

    public void setCountOfSites(int countOfSites) {
        this.countOfSites = countOfSites;
    }

    public void setReporterType(ReporterType reporterType) {
        reporterFactory.setReporterType(reporterType);
    }

    public void setHtmlParser(HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    public void setReporterFactory(ReporterFactory reporterFactory) {
        this.reporterFactory = reporterFactory;
    }
}
