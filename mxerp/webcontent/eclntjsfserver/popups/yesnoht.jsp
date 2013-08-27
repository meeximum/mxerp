<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <style>
  	form {margin:0;padding:0}
  </style>
  <link id="mystyle" rel="stylesheet" type="text/css" href="../../eclntjsfserver/htstyle/htstyle.css">
  <script>
  function isIE() { if (navigator.appName == "Netscape") return false; else return true; }
  var m_hotkeycallbacks = new Array();
  function ccHotkey(pEvent)
  {
    for (var i=0; i<m_hotkeycallbacks.length; i++)
      m_hotkeycallbacks[i](pEvent);
  }
  function ccDisableEnterKey(e)
  {
    if(e.keyCode == 13) return false;
    else return true;
  }  
  </script>
</head>
<body class="classbody" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" style="overflow:auto" onkeyup="ccHotkey(event)">
<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="eclntjsfserver_popups_okhtg_sv">
<t:htpageaddons1 id="g_pa1"/>

    <t:htrow id="g_2" width="100%" height="100%">
        <t:htimage id="g_101" image="#{eclntdefscr.yesNoPopup.icon}" rendered="#{eclntdefscr.yesNoPopup.iconRendered}"/>
        <t:htcoldistance id="g_102" width="20" rendered="#{eclntdefscr.yesNoPopup.iconRendered}"/>
        <t:htscrollpane id="g_3" height="100%" width="100%">
            <t:htrow id="g_554" rendered="#{eclntdefscr.yesNoPopup.headlineRendered}">
                <t:htlabel id="g_555" text="#{eclntdefscr.yesNoPopup.headline}" align="#{eclntdefscr.yesNoPopup.textAlign}"
                    width="100%"/>
            </t:htrow>
            <t:htrow id="g_4" width="100%" height="100%">
                <t:htlabel id="g_5" text="#{eclntdefscr.yesNoPopup.text}" align="#{eclntdefscr.yesNoPopup.textAlign}"
                    width="100%"/>
            </t:htrow>
        </t:htscrollpane>
    </t:htrow>
    <t:htrowdistance id="g_6" height="10" />
    <t:htrow id="g_7">
        <t:htcoldistance id="g_8" width="50%" />
        <t:htbutton id="g_9" actionListener="#{eclntdefscr.yesNoPopup.onYes}"
            text="#{eclntdefscr.yesNoPopup.textYes}" width="80" requestfocus="creation"/>
        <t:htcoldistance id="g_10" width="5" />
        <t:htbutton id="g_11" actionListener="#{eclntdefscr.yesNoPopup.onNo}"
            text="#{eclntdefscr.yesNoPopup.textNo}" width="80" />
        <t:htcoldistance id="g_12"
            rendered="#{eclntdefscr.yesNoPopup.showCancel}" width="50" />
        <t:htbutton id="g_13"
            actionListener="#{eclntdefscr.yesNoPopup.onCancel}"
            rendered="#{eclntdefscr.yesNoPopup.showCancel}"
            text="#{eclntdefscr.yesNoPopup.textCancel}" width="80" />
        <t:htcoldistance id="g_14" width="50%" />
    </t:htrow>

<t:htpageaddons2 id="g_pa2"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
</body>
</html>