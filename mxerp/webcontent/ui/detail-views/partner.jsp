<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_detail-views_partnerg_sv">
<t:rowdistance id="g_17" height="5" />
<t:rowinclude id="g_21" contentreplacedrilldown="DetailPB:PartnerPB" page="/ui/commons/detail-header.jsp" />
<t:rowdistance id="g_22" height="5" />
<t:row id="g_14" >
<t:pane id="g_15" height="100%" rowdistance="5" width="100%" >
<t:row id="g_18" >
<t:foldablepane id="g_19" text="Allgemein" width="100%" >
<t:row id="g_20" >
<t:colsynchedpane id="g_37" coldistance="10" rowdistance="5" >
<t:colsynchedrow id="g_39" >
<t:label id="g_33" text="Partnernr." />
<t:field id="g_34" enabled="#{d.PartnerPB.inEditMode}" text="#{d.PartnerPB.data.partnerNo}" width="100" />
<t:coldistance id="g_35" />
<t:label id="g_23" text="Name" />
<t:field id="g_24" enabled="#{d.PartnerPB.inEditMode}" text="#{d.PartnerPB.data.name}" width="250" />
</t:colsynchedrow>
</t:colsynchedpane>
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
