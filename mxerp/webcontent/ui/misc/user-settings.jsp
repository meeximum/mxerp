<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_misc_user-settingsg_sv">
<t:rowdistance id="g_5" />
<t:row id="g_2" >
<t:button id="g_3" actionListener="#{d.UserSettingsPB.onCommit}" stylevariant="commit" text="#{rr.literals[&#39;commit&#39;]}" />
<t:coldistance id="g_4" />
<t:button id="g_46" actionListener="#{d.UserSettingsPB.onRollback}" stylevariant="rollback" text="#{rr.literals[&#39;rollback&#39;]}" />
<t:coldistance id="g_6" width="100%" />
<t:icon id="g_7" image="/images/history_16_16.png" />
</t:row>
<t:rowdistance id="g_8" height="10" />
<t:row id="g_9" >
<t:pane id="g_10" height="100%" rowdistance="5" width="100%" >
<t:row id="g_11" >
<t:tabbedpane id="g_12" height="100%" width="100%" >
<t:tabbedpanetab id="g_13" rowdistance="5" text="Gespeicherte Suchen" >
<t:row id="g_14" >
<t:button id="g_15" actionListener="#{d.UserSettingsPB.onRefreshUserPresets}" stylevariant="refresh" text="#{rr.literals[&#39;refresh&#39;]}" />
<t:coldistance id="g_16" />
</t:row>
<t:row id="g_17" >
<t:fixgrid id="g_18" objectbinding="#{d.UserSettingsPB.gridSavedSearches}" >
<t:gridcol id="g_19" width="25" >
<t:icon id="g_20" actionListener=".{onDelete}" image="/eclntjsfserver/images/cross.png" />
</t:gridcol>
<t:gridcol id="g_21" text="#{rr.literals[&#39;entity&#39;]}" width="150" >
<t:label id="g_22" bgpaint="mandatory()" text=".{search.entity}" />
</t:gridcol>
<t:gridcol id="g_23" text="#{rr.literals[&#39;name&#39;]}" width="150" >
<t:field id="g_24" bgpaint="mandatory()" text=".{search.name}" />
</t:gridcol>
<t:gridcol id="g_25" text="#{rr.literals[&#39;global&#39;]}" width="250" >
<t:checkbox id="g_26" align="center" selected=".{search.global}" />
</t:gridcol>
<t:gridcol id="g_27" text="#{rr.literals[&#39;default&#39;]}" width="100" >
<t:checkbox id="g_28" align="center" selected=".{search.default}" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_29" rowdistance="5" text="Standardwerte" >
<t:row id="g_30" >
<t:button id="g_31" actionListener="#{d.UserSettingsPB.onRefreshSavedSearches}" stylevariant="refresh" text="#{rr.literals[&#39;refresh&#39;]}" />
<t:coldistance id="g_32" />
</t:row>
<t:row id="g_33" >
<t:fixgrid id="g_34" objectbinding="#{d.UserSettingsPB.gridUserPresets}" >
<t:gridcol id="g_35" width="25" >
<t:icon id="g_36" actionListener=".{onDelete}" image="/eclntjsfserver/images/cross.png" />
</t:gridcol>
<t:gridcol id="g_37" text="#{rr.literals[&#39;entity&#39;]}" width="150" >
<t:field id="g_38" bgpaint="mandatory()" text=".{preset.entity}" />
</t:gridcol>
<t:gridcol id="g_39" text="#{rr.literals[&#39;field&#39;]}" width="150" >
<t:field id="g_40" bgpaint="mandatory()" text=".{preset.field}" />
</t:gridcol>
<t:gridcol id="g_41" text="#{rr.literals[&#39;value&#39;]}" width="250" >
<t:field id="g_42" bgpaint="mandatory()" text=".{preset.value}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_43" >
<t:button id="g_44" actionListener="#{d.UserSettingsPB.onAddUserPreset}" stylevariant="new" text="#{rr.literals[&#39;create_new_date&#39;]}" />
</t:row>
</t:tabbedpanetab>
</t:tabbedpane>
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
