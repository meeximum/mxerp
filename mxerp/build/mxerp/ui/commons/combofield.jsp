<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_combofieldg_sv">
<t:row id="g_1" rendered="#{d.CombofieldPB.multiSelect}" >
<t:pane id="g_2" background="#cccccc" width="#{d.CombofieldPB.width}" >
<t:row id="g_3" >
<t:icon id="g_4" actionListener="#{d.CombofieldPB.onSelectAll}" image="/eclntjsfserver/images/checkbox/checkbox_selected_normal.png" />
<t:icon id="g_5" actionListener="#{d.CombofieldPB.onSelectNone}" image="/eclntjsfserver/images/checkbox/checkbox_normal.png" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_6" >
<t:fixgrid id="g_7" bordercolor="#cccccc" borderwidth="1" horizontalscrollmode="hidden" multiselect="#{d.CombofieldPB.multiSelect}" multiselectmode="1" objectbinding="#{d.CombofieldPB.gridValues}" sbvisibleamount="#{d.CombofieldPB.amount}" selectorcolumn="1" selectorcolumnimagefalse="/eclntjsfserver/images/checkbox/checkbox_normal.png" selectorcolumnimagetrue="/eclntjsfserver/images/checkbox/checkbox_selected_normal.png" suppressheadline="true" width="#{d.CombofieldPB.width}" >
<t:gridcol id="g_8" text="Column" width="100%" >
<t:label id="g_9" image=".{image}" text=".{value}" tooltip=".{comment}" width="100%" />
</t:gridcol>
</t:fixgrid>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
