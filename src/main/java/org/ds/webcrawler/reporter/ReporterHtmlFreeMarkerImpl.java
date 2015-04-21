package org.ds.webcrawler.reporter;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.*;
import org.ds.webcrawler.model.Site;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporterHtmlFreeMarkerImpl implements Reporter {

    private Configuration configuration;
    private String templatesDirectory;
    private String templateName;
    private String reportName;

    public ReporterHtmlFreeMarkerImpl(String templatesDirectory, String templateName, String reportName) {
        this.templatesDirectory = templatesDirectory;
        this.templateName = templateName;
        this.reportName = reportName;
        configuration = new Configuration();
        configuration.setTemplateLoader(new ClassTemplateLoader(getClass(), templatesDirectory));
    }


    public void generateReport(List<Site> sites) throws ReporterException {
        Template template = null;
        PrintWriter printWriter = null;
        try {
            template = configuration.getTemplate(templateName);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("sites", sites);

            File file = new File(reportName);
            printWriter = new PrintWriter(file.getAbsoluteFile());

            template.process(data, printWriter);
        } catch (IOException e) {
            throw new ReporterException("Cannot find template or cannot write data", e);
        } catch (TemplateException e) {
            throw new ReporterException("Cannot write data to template", e);
        } finally {
            printWriter.flush();
            printWriter.close();
        }


    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

}
