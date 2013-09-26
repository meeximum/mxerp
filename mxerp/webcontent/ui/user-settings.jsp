<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_user-settingsg_sv">
<t:row id="g_2" >
<t:pane id="g_3" height="100%" rowdistance="5" width="100%" >
<t:row id="g_4" >
<t:tabbedpane id="g_5" height="100%" width="100%" >
<t:tabbedpanetab id="g_6" text="Standartwerte" >
<t:row id="g_7" >
<t:button id="g_8" text="button" />
<t:coldistance id="g_9" />
<t:button id="g_10" text="button" />
<t:coldistance id="g_11" />
<t:button id="g_12" text="button" />
</t:row>
<t:row id="g_13" >
<t:fixgrid id="g_14" objectbinding="#{d.UserSettingsPB.gridUserPresets}" width="100%" >
<t:gridcol id="g_15" text="Column" width="100" />
<t:gridcol id="g_16" text="Column" width="100" />
<t:gridcol id="g_17" text="Column" width="100" />
</t:fixgrid>
</t:row>
<t:row id="g_18" >
<t:button id="g_19" text="button" />
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
