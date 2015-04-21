package org.ds.webcrawler.htmlparser;

import org.ds.webcrawler.model.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParserJsoupImpl implements HtmlParser {

    private String alexaTopsitesCountriesUrl;
    private String alexaSiteInfoUrl;

    public HtmlParserJsoupImpl(String alexaTopsitesCountriesUrl, String alexaSiteInfoUrl) {
        this.alexaTopsitesCountriesUrl = alexaTopsitesCountriesUrl;
        this.alexaSiteInfoUrl = alexaSiteInfoUrl;
    }

    @Override
    public List<Site> getListOfSitesByCountry(String country, int countOfSites) throws HtmlParserException {
        int numberOfPages = countOfSites / 25;
        List<Site> siteList = new ArrayList<Site>();
        Document document = null;
            try {
                for(int page = 0; page <= numberOfPages; page++) {
                    document = Jsoup.connect(alexaTopsitesCountriesUrl + ";" + page + "/" + country).get();
                    Elements listOfSites = document.select("li.site-listing");
                    for (Element element : listOfSites) {
                        String link = element.select("div.desc-container > p.desc-paragraph > a").text().toLowerCase();

                        Site site = getSiteByUrl(alexaSiteInfoUrl + link);
                        siteList.add(site);

                        int count = Integer.parseInt(element.select("div.count").text());
                        if (count == countOfSites) break;
                    }
                }
            } catch (IOException e) {
                throw new HtmlParserException("Cannot connect to resource. Resource is not available", e);
            }


        return siteList;
    }

    @Override
    public List<Site> getListOfSitesByCountries(List<String> countries, int countOfSites) throws HtmlParserException {
        List<Site> sites = new ArrayList<Site>();
        for(String country: countries) {
            sites.addAll(getListOfSitesByCountry(country, countOfSites));
        }
        return sites;
    }

    @Override
    public Site getSiteByUrl(String siteAlexaUrl) throws HtmlParserException {
        Document document = null;
        try {
            document = Jsoup.connect(siteAlexaUrl).get();
            String siteUrl = document.select("a.offsite_overview").attr("href");
            String globalRank = document.select("span.globleRank > span.col-pad > div > strong.metrics-data.align-vmiddle").text();
            String countryRank = document.select("h4.metrics-title > a").text() + ": " + document.select("span.countryRank > span.col-pad > div > strong.metrics-data.align-vmiddle").text();
            Site site = new Site(siteUrl, countryRank, globalRank, siteAlexaUrl);
            return site;
        } catch (IOException e) {
            throw new HtmlParserException("Cannot connect to resource. Resource is not available", e);
        }

    }

}
