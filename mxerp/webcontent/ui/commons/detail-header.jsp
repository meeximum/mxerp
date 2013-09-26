<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="ui_commons_detail-headerg_sv">
<t:row id="g_29" >
<t:button id="g_31" actionListener="#{d.DetailPB.onEdit}" image="/images/edit_16_16.png" rendered="#{d.DetailPB.renderEditBtn}" text="Ändern" />
<t:coldistance id="g_2" rendered="#{d.DetailPB.inReadMode}" />
<t:button id="g_33" actionListener="#{d.DetailPB.onDelete}" image="/images/trash_16_16.png" rendered="#{d.DetailPB.renderDeleteBtn}" text="Löschen" />
<t:button id="g_13" actionListener="#{d.DetailPB.onCommit}" image="/eclntjsfserver/images/disk.png" rendered="#{d.DetailPB.renderCommitBtn}" stylevariant="save" text="Speichern" />
<t:coldistance id="g_26" rendered="#{d.DetailPB.inEditMode}" />
<t:button id="g_27" actionListener="#{d.DetailPB.onRollback}" rendered="#{d.DetailPB.renderRollbackBtn}" stylevariant="cancel" text="#{rr.literals[&#39;cancel&#39;]}" />
<t:pane id="g_1" rendered="#{d.DetailPB.renderDeleteInfo}" >
<t:row id="g_35" >
<t:icon id="g_3" image="/eclntjsfserver/images/statusbar_error.png" />
<t:label id="g_5" font="size:14;weight:bold" text="Datensatz ist als gelöscht markiert!" />
</t:row>
</t:pane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
