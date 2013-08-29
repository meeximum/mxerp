<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="logong_sv">
<t:rowbodypane id="g_1" >
<t:rowdistance id="g_2" height="50%" />
<t:row id="g_3" >
<t:coldistance id="g_4" width="50%" />
<t:label id="g_5" font="size:60;weight:bold" text="MxERP" />
<t:coldistance id="g_6" />
<t:pane id="g_7" background="#CCCCCC50" border="#000000" padding="2" rowdistance="5" >
<t:rowdistance id="g_8" height="10" />
<t:row id="g_9" comment="User" >
<t:coldistance id="g_10" width="10" />
<t:label id="g_11" font="size:14" text="#{rr.literals[&#39;user&#39;]}" width="100" />
<t:coldistance id="g_12" width="10" />
<t:field id="g_13" bgpaint="mandatory()" font="size:14" requestfocus="creation" text="#{d.LogonUI.user}" width="160" />
<t:coldistance id="g_14" width="10" />
</t:row>
<t:rowdistance id="g_15" height="5" rendered="false" />
<t:row id="g_16" >
<t:coldistance id="g_17" width="10" />
<t:label id="g_18" font="size:14" text="#{rr.literals[&#39;password&#39;]}" width="100" />
<t:coldistance id="g_19" width="10" />
<t:password id="g_20" bgpaint="mandatory()" font="size:14" requestfocus="creation" text="#{d.LogonUI.password}" width="160" />
<t:coldistance id="g_21" width="10" />
</t:row>
<t:rowdistance id="g_22" height="5" />
<t:row id="g_23" >
<t:coldistance id="g_24" width="10" />
<t:label id="g_25" font="size:14" text="#{rr.literals[&#39;language&#39;]}" width="100" />
<t:coldistance id="g_26" width="10" />
<t:combobox id="g_27" actionListener="#{d.LogonPB.onLanguage}" bgpaint="mandatory()" flush="true" font="size:14" value="#{d.LogonUI.language}" width="160" withnullitem="false" >
<t:comboboxitem id="g_28" text="Deutsch" value="de" />
<t:comboboxitem id="g_29" text="Englisch" value="en" />
</t:combobox>
<t:coldistance id="g_30" width="10" />
</t:row>
<t:rowdistance id="g_31" height="10" />
<t:row id="g_32" >
<t:coldistance id="g_33" width="10" />
<t:button id="g_34" actionListener="#{d.LogonUI.onLogon}" font="size:14" hotkey="10" text="#{rr.literals[&#39;logon&#39;]}" width="130" />
</t:row>
<t:rowdistance id="g_35" height="10" />
</t:pane>
<t:coldistance id="g_36" width="200" />
<t:coldistance id="g_37" width="50%" />
</t:row>
<t:rowdistance id="g_38" height="50%" />
</t:rowbodypane>
<t:beanprocessing id="g_39" >
<t:clientconfig id="g_40" language="#{d.LogonUI.language}" />
</t:beanprocessing>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
