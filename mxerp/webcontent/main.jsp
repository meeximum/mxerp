<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="maing_sv">
<t:rowbodypane id="g_1" popupmenu="GENERAL" >
<t:row id="g_2" >
<t:coldistance id="g_3" width="26" />
<t:field id="g_4" popupmenu="TRANSACTION" selectallwhenfocussed="true" text="#{d.MainUI.transaction}" userhint="Transaktionscode" width="150" />
<t:coldistance id="g_5" width="100%" />
<t:combofield id="g_6" actionListener="#{d.MainUI.onSavedSearchAction}" flush="true" flushtimer="500" keepfocus="true" popupmenu="SAVEDSEARCH" text="#{d.MainUI.savedSearch}" userhint="gesicherte Suchen" width="200" />
<t:coldistance id="g_7" width="50" />
<t:icon id="g_8" actionListener="#{d.MainUI.onUserSettings}" image="/images/person.png" tooltip="Benutzereinstellungen" />
<t:coldistance id="g_9" />
<t:hyperlink id="g_10" font="size:12;weight:bold" image="/images/bulb.png" text="Hilfe" url="http://www.meeximum.at" />
</t:row>
<t:row id="g_11" >
<t:pane id="g_12" height="100%" width="26" >
<t:rowworkpagefavorites id="g_13" objectbinding="#{d.Favorites}" />
<t:rowdistance id="g_14" height="25" />
</t:pane>
<t:splitpane id="g_15" dividerlocation="#{d.MainUI.dividerlocation}" height="100%" onetouchexpandable="true" orientation="horizontal" width="100%" >
<t:splitpanesplit id="g_16" padding="2" rowdistance="5" >
<t:row id="g_17" >
<t:coldistance id="g_18" width="50%" />
<t:label id="g_19" font="size:30;weight:bold" text="MxERP" />
<t:coldistance id="g_20" width="50%" />
</t:row>
<t:row id="g_21" >
<t:outlookbar id="g_22" height="100%" width="100%" >
<t:outlookbaritem id="g_23" actionListener="#{d.MainUI.onSwitchNode}" configinfo="1" text="#{rr.literals[&#39;masterdata&#39;]}" />
<t:outlookbaritem id="g_24" actionListener="#{d.MainUI.onSwitchNode}" configinfo="2" text="#{rr.literals[&#39;customizing&#39;]}" />
<t:outlookbarcontent id="g_25" >
<t:rowworkplacefunctiontree id="g_26" foreground=".{foreground}" objectbinding="#{d.MainUI.masterDataFT}" rendered="#{d.MainUI.selectedNode == 1}" singleclickexecute="false" treenodefont=".{font}" treenoderowheight="30" />
<t:rowworkplacefunctiontree id="g_27" foreground=".{foreground}" objectbinding="#{d.MainUI.customizingFT}" rendered="#{d.MainUI.selectedNode == 2}" singleclickexecute="false" treenodefont=".{font}" treenoderowheight="30" />
</t:outlookbarcontent>
</t:outlookbar>
</t:row>
<t:rowdistance id="g_28" height="100%" />
<t:row id="g_29" >
<t:label id="g_30" rendered="true" text="#{h.user}" />
</t:row>
</t:splitpanesplit>
<t:splitpanesplit id="g_31" padding="2" rowdistance="5" >
<t:rowworkplace id="g_32" animationtype="foglight" objectbinding="#{d.workpageContainer}" />
</t:splitpanesplit>
</t:splitpane>
</t:row>
</t:rowbodypane>
<t:rowstatusbar id="g_33" />
<t:popupmenu id="GENERAL" comment="GENERAL" >
<t:menuitem id="g_34" actionListener="#{d.MainUI.onExpandCollapseSidebar}" comment="collapse sidepanel" configinfo="collapse" hotkey="ctrl-37" hotkeyonly="true" />
<t:menuitem id="g_35" actionListener="#{d.MainUI.onExpandCollapseSidebar}" comment="expand sidepanel" configinfo="expand" hotkey="ctrl-39" hotkeyonly="true" />
</t:popupmenu>
<t:popupmenu id="TRANSACTION" comment="TRANSACTION" >
<t:menuitem id="g_36" actionListener="#{d.MainUI.onCallTransaction}" comment="call transaction" hotkey="10" hotkeyonly="true" />
</t:popupmenu>
<t:popupmenu id="SAVEDSEARCH" comment="SAVEDSEARCH" >
<t:menuitem id="g_37" actionListener="#{d.MainUI.onLoadSavedSearch}" comment="open saved search" hotkey="10" hotkeyonly="true" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
