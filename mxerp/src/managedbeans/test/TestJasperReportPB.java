package managedbeans.test;

import java.io.Serializable;
import java.util.HashMap;

import javax.faces.event.ActionEvent;

import managedbeans.WorkpageDispatchedPageBean;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.util.WebResourceReader;
import org.eclnt.util.valuemgmt.ValueManager;
import org.eclnt.workplace.IWorkpageDispatcher;

@CCGenClass(expressionBase = "#{d.TestJasperReportPB}")
public class TestJasperReportPB extends WorkpageDispatchedPageBean implements
		Serializable {
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String value) {
		this.data = value;
	}

	public void onRender(ActionEvent event) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		HashMap<String, Object> parameter = new HashMap<String, Object>();

		parameter.put("parameter", getParameter());

		try {
			String jrxmlPath = WebResourceReader.getRealPath("/jrxml/ZuschnittHinweis.jrxml");
			jasperReport = JasperCompileManager.compileReport(jrxmlPath);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameter,
					new JREmptyDataSource());

			byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
			data = ValueManager.encodeHexString(bytes);
		} catch (JRException e) {
			Statusbar.outputAlert(e.toString() + "/" + e.getMessage());
		}
	}

	private String parameter;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String value) {
		this.parameter = value;
	}

	private static final long serialVersionUID = 1L;

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public TestJasperReportPB(IWorkpageDispatcher workpageDispatcher)
			throws Exception {
		super(workpageDispatcher);
	}

	public String getPageName() {
		return "/ui/test/jasperreport.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.TestJasperReportPB}";
	}

	// ------------------------------------------------------------------------
}
