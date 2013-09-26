<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_detail-views_customer-orgg_sv">
<t:rowdistance id="g_1" height="5" />
<t:rowinclude id="g_2" contentreplacedrilldown="DetailPB:PartnerPB" page="/ui/commons/detail-header.jsp" />
<t:rowdistance id="g_3" height="5" />
<t:row id="g_4" >
<t:pane id="g_5" height="100%" rowdistance="5" width="100%" >
<t:row id="g_6" >
<t:foldablepane id="g_7" text="Allgemein" width="100%" >
<t:row id="g_8" >
<t:colsynchedpane id="g_9" coldistance="10" rowdistance="5" >
<t:colsynchedrow id="g_10" >
<t:label id="g_11" text="Name" />
<t:field id="g_12" enabled="#{d.PartnerPB.inEditMode}" text="#{d.PartnerPB.data.nameOrg1}" width="250" />
<t:coldistance id="g_13" />
<t:label id="g_14" text="Name 2" />
<t:field id="g_15" enabled="#{d.PartnerPB.inEditMode}" text="#{d.PartnerPB.data.nameOrg2}" width="250" />
</t:colsynchedrow>
<t:colsynchedrow id="g_16" >
<t:label id="g_17" text="Land" />
<t:combobox id="g_18" enabled="#{d.PartnerPB.inEditMode}" validvaluesbinding="#{h.vvb.countries}" value="#{d.PartnerPB.data.billingAddrCountry}" />
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
