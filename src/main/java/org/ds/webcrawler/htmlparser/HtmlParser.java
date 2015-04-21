package org.ds.webcrawler.htmlparser;

import org.ds.webcrawler.model.Site;

import java.util.List;


public interface HtmlParser {

    public List<Site> getListOfSitesByCountry(String country, int countOfSites) throws HtmlParserException;

    public List<Site> getListOfSitesByCountries(List<String> countries, int countOfSites) throws HtmlParserException;

    public Site getSiteByUrl(String siteAlexaUrl) throws HtmlParserException;
}
