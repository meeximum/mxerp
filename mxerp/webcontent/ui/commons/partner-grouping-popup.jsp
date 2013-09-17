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
<t:fixgrid id="g_5" avoidroundtrips="true" drawoddevenrows="true" objectbinding="#{d.PartnerGroupingPopupPB.gridGrouping}" rowheight="32" sbvisibleamount="6" selectorcolumn="0" suppressheadline="true" width="305" >
<t:gridcol id="g_6" text="Column" width="86" >
<t:label id="g_8" text=".{grouping.id}" />
</t:gridcol>
<t:gridcol id="g_14" text="Column" width="150" >
<t:label id="g_15" text=".{grouping.description}" />
</t:gridcol>
<t:gridcol id="g_7" text="Column" width="32" >
<t:icon id="g_17" actionListener=".{onSelect}" configinfo="O" enabled=".{grouping.organizations}" image="/images/company_32_32.png" tooltip="Neue Firma erstellen" />
</t:gridcol>
<t:gridcol id="g_18" text="Column" width="32" >
<t:icon id="g_19" actionListener=".{onSelect}" configinfo="P" enabled=".{grouping.persons}" image="/images/person_32_32.png" tooltip="Neue Person erstellen" />
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
