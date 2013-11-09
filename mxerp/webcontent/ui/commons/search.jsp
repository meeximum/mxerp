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
<t:row id="g_2" >
<t:foldablepane id="g_3" rowalignmenty="top" rowdistance="5" text="Suche" width="100%" >
<t:row id="g_4" >
<t:field id="g_5" bgpaint="writeifempty(100%-2,100%,Indexsuche,rightbottom)" text="#{d.SearchPB.indexField}" userhint="#{d.SearchPB.indexHint}" width="250" />
</t:row>
<t:rowdistance id="g_6" />
<t:rowdynamiccontent id="g_7" contentbinding="#{d.SearchPB.selectionPanel}" />
<t:rowdistance id="g_8" height="10" />
<t:row id="g_9" >
<t:coldistance id="g_10" width="100%" />
<t:label id="g_11" text="Max. Ergebnisse" />
<t:coldistance id="g_12" />
<t:formattedfield id="g_13" align="right" format="int" value="#{d.SearchPB.fetchLimit}" width="50" />
</t:row>
<t:row id="g_14" >
<t:coldistance id="g_15" width="100%" />
<t:checkbox id="g_16" selected="#{d.SearchPB.selectDeleted}" text="gelöschte auch selektieren" />
</t:row>
<t:row id="g_17" >
<t:button id="g_18" actionListener="#{d.SearchPB.onSearch}" image="/eclntjsfserver/images/magnifier.png&amp;buffer" text="Suchen" />
<t:coldistance id="g_19" />
<t:button id="g_20" actionListener="#{d.SearchPB.onReset}" text="Zurücksetzen" />
<t:coldistance id="g_21" width="100%" />
<t:label id="g_22" text="Suche speichern" />
<t:coldistance id="g_23" />
<t:field id="g_24" text="#{d.SearchPB.savedSearchName}" width="150" />
<t:coldistance id="g_25" />
<t:button id="g_26" actionListener="#{d.SearchPB.onSaveSearch}" image="/eclntjsfserver/images/disk.png&amp;buffer" text="Speichern" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_27" >
<t:pane id="g_28" background="#00000010" border="#00000010" height="100%" width="100%" >
<t:rowheader id="g_29" bgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000020,vertical)" border="#00000010" >
<t:button id="g_30" actionListener="#{d.SearchPB.onNew}" bgpaint="null!" image="/images/add_document_16_16.png&amp;buffer" text="Neu" />
<t:coldistance id="g_31" width="100%" />
<t:dynamiccontent id="g_32" contentbinding="#{d.SearchPB.variantsBinding}" />
<t:coldistance id="g_33" />
<t:icon id="g_34" actionListener="#{d.SearchPB.gridResult.onEditColumnDetails}" image="/images/setting_tool_16_16.png&amp;buffer" />
<t:icon id="g_35" actionListener="#{d.SearchPB.gridResult.onOpenGridFunctions}" image="/images/export_16_16.png&amp;buffer" />
</t:rowheader>
<t:rowdynamiccontent id="g_36" contentbinding="#{d.SearchPB.resultGrid}" />
</t:pane>
</t:row>
</t:rowbodypane>
<t:popupmenu id="GENERAL" >
<t:menuitem id="g_37" actionListener="#{d.SearchPB.onSearch}" hotkey="10" hotkeyonly="true" />
</t:popupmenu>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
