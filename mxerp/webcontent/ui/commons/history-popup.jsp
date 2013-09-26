<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_history-popupg_sv">
<t:row id="g_1" >
<t:pane id="g_2" height="570" padding="left:0;right:0;top:2;bottom:2" rowdistance="5" width="710" >
<t:row id="g_3" >
<t:coldistance id="g_4" width="50%" />
<t:fixgrid id="g_5" objectbinding="#{d.HistoryPopupPB.gridHistory}" sbvisibleamount="25" >
<t:gridcol id="g_6" text="Zeitpunkt" width="120" >
<t:label id="g_7" format="datetime" formatmask="short" text=".{header.createdAt}" />
</t:gridcol>
<t:gridcol id="g_8" text="Benutzer" width="100" >
<t:label id="g_9" text=".{header.createdBy}" />
</t:gridcol>
<t:gridcol id="g_10" text="Aktion" width="60" >
<t:label id="g_11" text=".{header.action}" />
</t:gridcol>
<t:gridcol id="g_12" text="Feld" width="100" >
<t:label id="g_13" text=".{position.field}" />
</t:gridcol>
<t:gridcol id="g_14" text="alter Wert" width="150" >
<t:label id="g_15" text=".{position.valueOld}" />
</t:gridcol>
<t:gridcol id="g_16" text="neuer Wert" width="150" >
<t:label id="g_17" text=".{position.valueNew}" />
</t:gridcol>
</t:fixgrid>
<t:coldistance id="g_18" width="50%" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
