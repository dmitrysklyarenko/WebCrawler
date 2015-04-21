package org.ds.webcrawler.model;

public class Site {

    private String siteUrl;
    private String countryRank;
    private String globalRank;
    private String alexaLink;

    public Site(String siteUrl, String countryRank, String globalRank, String alexaLink) {
        this.siteUrl = siteUrl;
        this.countryRank = countryRank;
        this.globalRank = globalRank;
        this.alexaLink = alexaLink;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getCountryRank() {
        return countryRank;
    }

    public void setCountryRank(String countryRank) {
        this.countryRank = countryRank;
    }

    public String getGlobalRank() {
        return globalRank;
    }

    public void setGlobalRank(String globalRank) {
        this.globalRank = globalRank;
    }

    public String getAlexaLink() {
        return alexaLink;
    }

    public void setAlexaLink(String alexaLink) {
        this.alexaLink = alexaLink;
    }
}
