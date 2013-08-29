<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_searchg_sv">
<t:rowbodypane id="g_1" >
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.SearchPB.onInit}" text="Init" />
</t:row>
<t:rowdynamiccontent id="g_24" contentbinding="#{d.SearchPB.selectionPanel}" />
<t:row id="g_26" >
<t:coldistance id="g_27" width="100%" />
<t:label id="g_28" text="Max. Ergebnisse" />
<t:coldistance id="g_29" />
<t:formattedfield id="g_9" align="right" format="int" value="#{d.SearchPB.fetchLimit}" width="50" />
</t:row>
<t:row id="g_10" >
<t:button id="g_11" actionListener="#{d.SearchPB.onSearch}" hotkey="10" image="/eclntjsfserver/images/magnifier.png&amp;buffer" text="Suchen" />
<t:coldistance id="g_12" />
<t:button id="g_13" actionListener="#{d.SearchPB.onReset}" text="Zurücksetzen" />
<t:coldistance id="g_14" width="100%" />
<t:label id="g_15" text="Suche speichern" />
<t:coldistance id="g_16" />
<t:field id="g_17" text="#{d.SearchPB.savedSearchName}" width="150" />
<t:coldistance id="g_18" />
<t:button id="g_19" actionListener="#{d.SearchPB.onSaveSearch}" image="/eclntjsfserver/images/disk.png&amp;buffer" text="Speichern" />
</t:row>
<t:rowline id="g_20" />
<t:rowdynamiccontent id="g_21" contentbinding="#{d.SearchPB.resultGrid}" />
</t:rowbodypane>
<t:rowstatusbar id="g_22" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
