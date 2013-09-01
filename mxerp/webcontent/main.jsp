<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="maing_sv">
<t:rowbodypane id="g_2" popupmenu="GENERAL" >
<t:row id="g_18" >
<t:coldistance id="g_10" width="26" />
<t:field id="g_5" popupmenu="TRANSACTION" selectallwhenfocussed="true" text="#{d.MainUI.transaction}" userhint="Transaktionscode" width="150" />
<t:coldistance id="g_21" width="100%" />
<t:combofield id="g_17" actionListener="#{d.MainUI.onSavedSearchAction}" flush="true" flushtimer="500" keepfocus="true" popupmenu="SAVEDSEARCH" text="#{d.MainUI.savedSearch}" userhint="gesicherte Suchen" width="200" />
<t:coldistance id="g_13" width="50" />
<t:hyperlink id="g_22" font="size:12;weight:bold" image="/images/bulb.png" text="Hilfe" url="http://www.meeximum.at" />
</t:row>
<t:row id="g_6" >
<t:pane id="g_1" height="100%" width="26" >
<t:rowworkpagefavorites id="g_30" objectbinding="#{d.WorkplaceFavorites}" stylevariant="null!" />
<t:rowdistance id="g_4" height="25" />
</t:pane>
<t:splitpane id="g_7" dividerlocation="#{d.MainUI.dividerlocation}" height="100%" onetouchexpandable="true" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_8" padding="2" rowdistance="5" >
<t:row id="g_9" >
<t:coldistance id="g_41" width="50%" />
<t:label id="g_11" font="size:30;weight:bold" text="MxERP" />
<t:coldistance id="g_12" width="50%" />
</t:row>
<t:row id="g_32" >
<t:outlookbar id="g_23" height="100%" width="100%" >
<t:outlookbaritem id="g_24" actionListener="#{d.MainUI.onSwitchNode}" configinfo="1" text="#{rr.literals[&#39;masterdata&#39;]}" />
<t:outlookbarcontent id="g_25" >
<t:rowworkplacefunctiontree id="g_28" foreground=".{foreground}" objectbinding="#{d.MainUI.masterDataFT}" rendered="#{d.MainUI.selectedNode == 1}" singleclickexecute="false" treenodefont=".{font}" treenoderowheight="30" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
<t:rowdistance id="g_34" height="100%" />
<t:row id="g_42" >
<t:label id="g_43" rendered="true" text="#{h.user}" />
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_47" padding="2" rowdistance="5" >
<t:rowworkplace id="g_15" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:rowstatusbar id="g_16" />
<t:popupmenu id="GENERAL" comment="GENERAL" >
<t:menuitem id="g_37" actionListener="#{d.MainUI.onExpandCollapseSidebar}" comment="collapse sidepanel" configinfo="collapse" hotkey="ctrl-37" hotkeyonly="true" />
<t:menuitem id="g_39" actionListener="#{d.MainUI.onExpandCollapseSidebar}" comment="expand sidepanel" configinfo="expand" hotkey="ctrl-39" hotkeyonly="true" />
</t:popupmenu>
<t:popupmenu id="TRANSACTION" comment="TRANSACTION" >
<t:menuitem id="g_45" actionListener="#{d.MainUI.onCallTransaction}" comment="call transaction" hotkey="10" hotkeyonly="true" />
</t:popupmenu>
<t:popupmenu id="SAVEDSEARCH" comment="SAVEDSEARCH" >
<t:menuitem id="g_49" actionListener="#{d.MainUI.onLoadSavedSearch}" comment="open saved search" hotkey="10" hotkeyonly="true" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
