package org.ds.webcrawler.reporter;

import org.ds.webcrawler.model.Site;

import java.util.List;


public interface Reporter {

    public void generateReport(List<Site> sites) throws ReporterException;
}
