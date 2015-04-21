package org.ds.webcrawler.main;


import freemarker.template.TemplateException;
import org.ds.webcrawler.htmlparser.HtmlParserException;
import org.ds.webcrawler.reporter.*;
import org.ds.webcrawler.service.CrawlerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/appContext.xml");
        CrawlerService crawlerService = (CrawlerService) applicationContext.getBean("crawlerService");

        List<String> countries = new ArrayList<String>();
        System.out.println("Start working!");

        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-count")) {
                i++;
                crawlerService.setCountOfSites(Integer.parseInt(args[i]));
            } else if(args[i].equals("-format")) {
                i++;
                crawlerService.setReporterType(ReporterType.valueOf(args[i].toUpperCase()));
            } else {
                countries.add(args[i]);
            }
        }

        try {
            if(countries.size() > 1) {
                crawlerService.generateReportWithCountries(countries);
                System.out.println("Report successfully generated!");
            } else if(countries.size() == 1) {
                crawlerService.generateReportWithCountry(countries.get(0));
                System.out.println("Report successfully generated!");
            }
        } catch (ReporterException e) {
            System.out.println("Reporter error!");
        } catch (HtmlParserException e) {
            System.out.println("Resource is not available or HTML parsing error!");
        }
    }

}
