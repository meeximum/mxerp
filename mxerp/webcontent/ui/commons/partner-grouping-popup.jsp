<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_partner-grouping-popupg_sv">
<t:row id="g_1" >
<t:pane id="g_11" >
<t:row id="g_12" >
<t:fixgrid id="g_5" avoidroundtrips="true" objectbinding="#{d.PartnerGroupingPopupPB.gridGrouping}" selectorcolumn="0" suppressheadline="true" width="304" >
<t:gridcol id="g_6" text="Column" width="100" >
<t:label id="g_8" text="label" />
</t:gridcol>
<t:gridcol id="g_14" text="Column" width="100" >
<t:label id="g_15" text="label" />
</t:gridcol>
<t:gridcol id="g_7" text="Column" width="100" >
<t:label id="g_9" text="label" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
