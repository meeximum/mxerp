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
<t:button id="g_31" actionListener="#{d.DetailPB.onEdit}" image="/images/edit_16_16.png" rendered="#{d.DetailPB.inReadMode}" text="Ändern" />
<t:coldistance id="g_2" rendered="#{d.DetailPB.inReadMode}" />
<t:button id="g_33" actionListener="#{d.DetailPB.onDelete}" rendered="#{d.DetailPB.inReadMode}" text="Löschen" />
<t:button id="g_13" actionListener="#{d.DetailPB.onCommit}" rendered="#{d.DetailPB.inEditMode}" text="Commit" />
<t:coldistance id="g_26" rendered="#{d.DetailPB.inEditMode}" />
<t:button id="g_27" actionListener="#{d.DetailPB.onRollback}" rendered="#{d.DetailPB.inEditMode}" text="Rollback" />
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
