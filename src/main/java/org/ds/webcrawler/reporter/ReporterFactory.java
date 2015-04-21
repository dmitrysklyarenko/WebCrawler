package org.ds.webcrawler.reporter;

public class ReporterFactory {

    private ReporterType reporterType;
    private Reporter reporterHtml;
    private Reporter reporterCsv;

    public Reporter getReporter() throws ReporterException {
        Reporter result = null;
        switch(reporterType) {
            case HTML:
                result = reporterHtml;
                break;
            case CSV:
                result = reporterCsv;
                break;
            default:
                throw new ReporterException("This reporter does not exist");
        }
        return result;
    }

    public void setReporterType(ReporterType reporterType) {
        this.reporterType = reporterType;
    }

    public void setReporterHtml(Reporter reporterHtml) {
        this.reporterHtml = reporterHtml;
    }

    public void setReporterCsv(Reporter reporterCsv) {
        this.reporterCsv = reporterCsv;
    }
}
