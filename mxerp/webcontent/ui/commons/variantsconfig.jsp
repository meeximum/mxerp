<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_util_variantsconfigg_sv">
<t:row id="g_1" >
<t:pane id="g_2" padding="20" rowdistance="5" stylevariant="asbodypane" >
<t:row id="g_3" >
<t:colsynchedpane id="g_4" coldistance="5" rowdistance="5" >
<t:colsynchedrow id="g_5" >
<t:label id="g_6" text="Name" />
<t:field id="g_7" maxlength="20" requestfocus="creation" text="#{d.VariantsConfigPB.name}" userhint="Wenn der Name dem Benutzernamen entspricht wird diese Variante per Default geladen!" width="200" />
</t:colsynchedrow>
<t:colsynchedrow id="g_8" >
<t:label id="g_9" text="Beschreibung" />
<t:field id="g_10" maxlength="20" requestfocus="creation" text="#{d.VariantsConfigPB.description}" width="200" />
</t:colsynchedrow>
</t:colsynchedpane>
<t:coldistance id="g_11" />
<t:button id="g_12" actionListener="#{d.VariantsConfigPB.onSave}" image="/eclntjsfserver/images/disk.png" text="Speichern" />
</t:row>
<t:row id="g_13" >
<t:coldistance id="g_14" width="100" />
</t:row>
<t:rowdistance id="g_15" height="10" />
<t:row id="g_16" >
<t:label id="g_17" text="Bestehende Benutzervarianten" />
</t:row>
<t:rowdistance id="g_18" height="10" />
<t:row id="g_19" >
<t:fixgrid id="g_20" background="#FFFFFF80" bordercolor="#00000030" borderheight="1" borderwidth="1" objectbinding="#{d.VariantsConfigPB.gridVariants}" rowheight="16" sbvisibleamount="8" suppressheadline="true" width="100%" >
<t:gridcol id="g_21" text="Name" width="30%" >
<t:label id="g_22" text=".{variant.name}" />
</t:gridcol>
<t:gridcol id="g_23" text="Beschreibung" width="70%" >
<t:label id="g_24" text=".{variant.description}" />
</t:gridcol>
<t:gridcol id="g_25" width="20" >
<t:icon id="g_26" actionListener=".{onDelete}" image="/eclntjsfserver/images/cross.png" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:pane>
</t:row>
<t:row id="g_27" >
<t:buttonmenu id="g_28" buttonmenumode="buttonandmenu" stylevariant="WP_PERSPECTIVEMANAGER" >
<t:menuitem id="g_29" text="AAAA" />
</t:buttonmenu>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
