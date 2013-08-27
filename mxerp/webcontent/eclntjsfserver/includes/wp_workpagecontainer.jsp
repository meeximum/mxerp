<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="wp_2">
            <t:row id="g_21">
              <t:pane id="g_22" actionListener="#{OBJECTBINDING.onPageAction}"
                                height="100%" 
                                width="100%" 
                                dropreceive="DROPRECEIVE"
                                hotkeyisolation="HOTKEYISOLATION">
                <t:captureanimator id="g_22_ca" trigger="#{OBJECTBINDING.animationValue}" animationtype="ANIMATIONTYPE" animatestepcount="ANIMATESTEPCOUNT"/>
                <t:row id="g_23" >
                  <t:pane id="g_24" border="BORDER" height="100%" padding="PADDING" width="100%" >
                    <t:rowtitlebar id="g_25" rendered="#{OBJECTBINDING.currentWorkpageDecorated}" text="#{OBJECTBINDING.currentWorkpage.title}"  dragsend="#{OBJECTBINDING.currentWorkpage.dragsend}">
                      <t:icon id="g_26" rendered="#{OBJECTBINDING.currentWorkpagePopupSupported}" actionListener="#{OBJECTBINDING.onOpenCurrentPageAsModelessPopup}" image="/eclntjsfserver/images/window_popup.png" />
                      <t:icon id="g_27" rendered="#{OBJECTBINDING.currentWorkpageCloseSupported}" actionListener="#{OBJECTBINDING.onCloseCurrentWorkpage}" image="/eclntjsfserver/images/window_close.png" />
                    </t:rowtitlebar>
                    <t:rowinclude id="g_28" contentreplace="#{OBJECTBINDING.currentContentReplaceNoDrillDown}" contentreplacedrilldown="#{OBJECTBINDING.currentContentReplace}" page="#{OBJECTBINDING.currentJspPage}" updateoninnereventonly="#{OBJECTBINDING.updateOnInnerEventOnly}"/>
                  </t:pane>
                </t:row>
              </t:pane>
            </t:row>
<t:pageaddons/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
