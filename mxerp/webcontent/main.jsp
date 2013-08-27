<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="maing_sv">
<t:rowbodypane id="g_2" >
<t:row id="g_18" >
<t:coldistance id="g_19" />
<t:label id="g_20" rendered="true" text="#{h.user}" />
<t:coldistance id="g_21" width="100%" />
<t:hyperlink id="g_22" font="size:12;weight:bold" image="/images/bulb.png" text="Hilfe" url="http://www.meeximum.at" />
</t:row>
<t:row id="g_6" >
<t:splitpane id="g_7" dividerlocation="150" height="100%" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_8" padding="2" rowdistance="5" >
<t:row id="g_9" >
<t:coldistance id="g_10" width="50%" />
<t:label id="g_11" font="size:30;weight:bold" text="MxERP" />
<t:coldistance id="g_12" width="50%" />
</t:row>
<t:row id="g_4" >
<t:outlookbar id="g_23" height="100%" width="100%" >
<t:outlookbaritem id="g_24" actionListener="#{d.MainUI.onSwitchNode}" configinfo="1" text="#{rr.literals[&#39;masterdata&#39;]}" />
<t:outlookbarcontent id="g_25" >
<t:rowworkplacefunctiontree id="g_28" foreground=".{foreground}" objectbinding="#{d.MainUI.masterDataFT}" rendered="#{d.MainUI.selectedNode == 1}" singleclickexecute="false" treenodefont=".{font}" treenoderowheight="30" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
<t:rowdistance id="g_5" height="100%" />
</t:splitpanesplit>
<t:splitpanesplit id="g_14" padding="2" rowdistance="5" >
<t:rowworkplace id="g_15" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:rowstatusbar id="g_16" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
