<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_test_jasperreportg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:label id="g_3" text="Parameter" />
<t:coldistance id="g_4" />
<t:field id="g_5" text="#{d.TestJasperReportPB.parameter}" width="100" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.TestJasperReportPB.onRender}" text="Ausführen" />
</t:row>
<t:rowdistance id="g_8" height="10" />
<t:row id="g_9" >
<t:pdfrenderer id="g_10" height="100%" pdf="#{d.TestJasperReportPB.data}" width="100%" />
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
