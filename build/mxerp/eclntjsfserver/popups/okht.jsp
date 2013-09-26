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
<t:htrow id="g_2" height="100%" width="100%" >
<t:htpane id="g_3" height="100%" width="100%" >
<t:htrow id="g_4" height="100%" width="100%" >
<t:htimage id="g_5" image="#{eclntdefscr.okPopup.icon}" rendered="#{eclntdefscr.okPopup.iconRendered}" />
<t:htcoldistance id="g_6" rendered="#{eclntdefscr.okPopup.iconRendered}" width="20" />
<t:htscrollpane id="g_7" height="100%" width="100%" >
<t:htrow id="g_8" height="30" rendered="#{eclntdefscr.okPopup.headlineRendered}" >
<t:htlabel id="g_9" align="#{eclntdefscr.okPopup.textAlign}" text="#{eclntdefscr.okPopup.headline}" />
</t:htrow>
<t:htrowdistance id="g_10" height="10" />
<t:htrow id="g_11" height="100%" width="100%" >
<t:htlabel id="g_12" align="#{eclntdefscr.okPopup.textAlign}" text="#{eclntdefscr.okPopup.text}" width="100%" />
</t:htrow>
</t:htscrollpane>
</t:htrow>
<t:htrowdistance id="g_13" height="10" />
<t:htrow id="g_14" >
<t:htcoldistance id="g_15" width="50%" />
<t:htbutton id="g_16" actionListener="#{eclntdefscr.okPopup.onClose}" text="#{eclntdefscr.okPopup.textOk}" width="100" />
<t:htcoldistance id="g_17" rendered="#{eclntdefscr.okPopup.showCancel}" width="50" />
<t:htbutton id="g_18" actionListener="#{eclntdefscr.okPopup.onCancel}" rendered="#{eclntdefscr.okPopup.showCancel}" text="#{eclntdefscr.okPopup.textCancel}" width="80+" />
<t:htcoldistance id="g_19" width="50%" />
</t:htrow>
</t:htpane>
</t:htrow>
<t:htpageaddons2 id="g_pa2"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
</body>
</html>