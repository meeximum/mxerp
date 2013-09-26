<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_misc_user-settingsg_sv">
<t:row id="g_1" >
<t:button id="g_2" actionListener="#{d.UserSettingsPB.onCommit}" stylevariant="commit" text="#{rr.literals[&#39;commit&#39;]}" />
<t:coldistance id="g_3" />
<t:button id="g_4" actionListener="#{d.UserSettingsPB.onRollback}" stylevariant="rollback" text="#{rr.literals[&#39;rollback&#39;]}" />
<t:coldistance id="g_5" width="100%" />
<t:icon id="g_6" image="/images/history_16_16.png" />
</t:row>
<t:rowdistance id="g_7" height="10" />
<t:row id="g_8" >
<t:pane id="g_9" height="100%" rowdistance="5" width="100%" >
<t:row id="g_10" >
<t:tabbedpane id="g_11" height="100%" width="100%" >
<t:tabbedpanetab id="g_12" rowdistance="5" text="Standartwerte" >
<t:row id="g_13" >
<t:button id="g_14" actionListener="#{d.UserSettingsPB.onRefreshUserPresets}" stylevariant="refresh" text="#{rr.literals[&#39;refresh&#39;]}" />
<t:coldistance id="g_15" />
</t:row>
<t:row id="g_16" >
<t:fixgrid id="g_17" objectbinding="#{d.UserSettingsPB.gridUserPresets}" >
<t:gridcol id="g_18" width="25" >
<t:icon id="g_19" actionListener=".{onDelete}" image="/eclntjsfserver/images/cross.png" />
</t:gridcol>
<t:gridcol id="g_20" text="#{rr.literals[&#39;entity&#39;]}" width="150" >
<t:field id="g_21" bgpaint="mandatory()" text=".{preset.entity}" />
</t:gridcol>
<t:gridcol id="g_22" text="#{rr.literals[&#39;field&#39;]}" width="150" >
<t:field id="g_23" bgpaint="mandatory()" text=".{preset.field}" />
</t:gridcol>
<t:gridcol id="g_24" text="#{rr.literals[&#39;value&#39;]}" width="250" >
<t:field id="g_25" bgpaint="mandatory()" text=".{preset.value}" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:row id="g_26" >
<t:button id="g_27" actionListener="#{d.UserSettingsPB.onAddUserPreset}" stylevariant="new" text="#{rr.literals[&#39;create_new_date&#39;]}" />
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
