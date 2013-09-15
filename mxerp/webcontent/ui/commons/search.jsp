<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_searchg_sv">
<t:rowbodypane id="g_1" padding="0" popupmenu="GENERAL" >
<t:row id="g_3" >
<t:foldablepane id="g_4" rowalignmenty="top" rowdistance="5" text="Suche" width="100%" >
<t:rowdynamiccontent id="g_24" contentbinding="#{d.SearchPB.selectionPanel}" />
<t:rowdistance id="g_21" height="10" />
<t:row id="g_26" >
<t:coldistance id="g_27" width="100%" />
<t:label id="g_28" text="Max. Ergebnisse" />
<t:coldistance id="g_29" />
<t:formattedfield id="g_9" align="right" format="int" value="#{d.SearchPB.fetchLimit}" width="50" />
</t:row>
<t:row id="g_46" >
<t:coldistance id="g_47" width="100%" />
<t:checkbox id="g_49" selected="#{d.SearchPB.selectDeleted}" text="gelöschte auch selektieren" />
</t:row>
<t:row id="g_10" >
<t:button id="g_11" actionListener="#{d.SearchPB.onSearch}" image="/eclntjsfserver/images/magnifier.png&amp;buffer" text="Suchen" />
<t:coldistance id="g_12" />
<t:button id="g_13" actionListener="#{d.SearchPB.onReset}" text="Zurücksetzen" />
<t:coldistance id="g_14" width="100%" />
<t:label id="g_15" text="Suche speichern" />
<t:coldistance id="g_16" />
<t:field id="g_17" text="#{d.SearchPB.savedSearchName}" width="150" />
<t:coldistance id="g_18" />
<t:button id="g_19" actionListener="#{d.SearchPB.onSaveSearch}" image="/eclntjsfserver/images/disk.png&amp;buffer" text="Speichern" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_7" >
<t:pane id="g_40" background="#00000010" border="#00000010" height="100%" width="100%" >
<t:rowheader id="g_37" bgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000020,vertical)" border="#00000010" >
<t:button id="g_38" actionListener="#{d.SearchPB.onNew}" bgpaint="null!" image="/images/add_document_16_16.png&amp;buffer" text="Neu" />
<t:coldistance id="g_42" width="100%" />
<t:dynamiccontent id="g_23" contentbinding="#{d.SearchPB.variantsBinding}" />
<t:coldistance id="g_22" />
<t:icon id="g_43" actionListener="#{d.SearchPB.gridResult.onEditColumnDetails}" image="/images/setting_tool_16_16.png&amp;buffer" />
<t:icon id="g_20" actionListener="#{d.SearchPB.gridResult.onOpenGridFunctions}" image="/images/export_16_16.png&amp;buffer" />
</t:rowheader>
<t:rowdynamiccontent id="g_33" contentbinding="#{d.SearchPB.resultGrid}" />
</t:pane>
</t:row>
</t:rowbodypane>
<t:popupmenu id="GENERAL" >
<t:menuitem id="g_2" actionListener="#{d.SearchPB.onSearch}" hotkey="10" hotkeyonly="true" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
