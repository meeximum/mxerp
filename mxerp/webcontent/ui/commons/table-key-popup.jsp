<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_table-key-popupg_sv">
<t:row id="g_1" >
<t:pane id="g_2" height="200" padding="2" rowdistance="5" width="400" >
<t:rowdynamiccontent id="g_3" contentbinding="#{d.TableKeyPopupPB.content}" />
<t:row id="g_4" >
<t:button id="g_5" actionListener="#{d.TableKeyPopupPB.onOk}" hotkey="10" text="Ok" />
<t:coldistance id="g_6" />
<t:button id="g_7" actionListener="#{d.TableKeyPopupPB.onCancel}" text="Cancel" />
</t:row>
</t:pane>
</t:row>
<t:rowstatusbar id="g_8" />
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
