<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_customize-tableg_sv">
<t:rowbodypane id="g_1" rowdistance="5" >
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.CustomizeTablePB.onRefresh}" image="/eclntjsfserver/images/arrow_refresh.png" text="#{rr.literals[&#39;refresh&#39;]}" />
<t:coldistance id="g_4" />
<t:button id="g_5" actionListener="#{d.CustomizeTablePB.onCommit}" image="/eclntjsfserver/images/yesnopopup_yes.png" text="#{rr.literals[&#39;commit&#39;]}" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.CustomizeTablePB.onRollback}" image="/eclntjsfserver/images/yesnopopup_no.png" text="#{rr.literals[&#39;rollback&#39;]}" />
</t:row>
<t:row id="g_12" >
<t:foldablepane id="g_13" height="100%" rowdistance="5" width="100%" >
<t:rowdynamiccontent id="g_8" contentbinding="#{d.CustomizeTablePB.content}" />
<t:row id="g_9" >
<t:button id="g_10" actionListener="#{d.CustomizeTablePB.onAddRow}" configinfo="date" text="#{rr.literals[&#39;create_new_date&#39;]}" />
</t:row>
</t:foldablepane>
</t:row>
<t:row id="g_14" >
<t:foldablepane id="g_15" height="100%" rowdistance="5" width="100%" >
<t:row id="g_17" >
<t:fixgrid id="g_18" objectbinding="#{d.CustomizeTablePB.gridTableT}" >
<t:gridcol id="g_21" text="Sprache" width="80" >
<t:combobox id="g_11" validvaluesbinding="#{h.vvb.language}" value=".{vvb.language}" />
</t:gridcol>
<t:gridcol id="g_22" text="Beschreibung" width="500" >
<t:field id="g_24" text=".{vvb.description}" width="100" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_26" >
<t:button id="g_27" actionListener="#{d.CustomizeTablePB.onAddRow}" configinfo="text" text="#{rr.literals[&#39;create_new_date&#39;]}" />
</t:row>
</t:foldablepane>
</t:row>
</t:rowbodypane>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
