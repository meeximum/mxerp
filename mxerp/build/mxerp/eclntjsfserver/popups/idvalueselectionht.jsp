<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<% 
String styleName = (String)session.getAttribute("ccstyle");
if (styleName == null) styleName = "";
String viewPortSize = (String)session.getAttribute("ccviewportsize");
if (viewPortSize == null) viewPortSize = "";
%>


<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
  <style> form {margin:0;padding:0} </style>
  <script>
  var vViewPortSize = "<%= viewPortSize %>";
  if (vViewPortSize != "")
  {
    document.write("<meta name='viewport' content='width="+vViewPortSize+"'/>");
  }
  var vStyle = "<%= styleName %>";
  if (vStyle == "")
  {
    document.write("<link id=\"mystyle\" rel=\"stylesheet\" type=\"text/css\" href=\"../eclntjsfserver/htstyle/htstyle.css\">");
  }
  else
  {
    var vStyle = "../eclntjsfserver/styles/" + vStyle + "/style.css";
    document.write("<link id=\"mystyle\" rel=\"stylesheet\" type=\"text/css\" href=\""+vStyle+"\">");
  }
  </script> 
<head>
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
<body class="classbody" topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" style="overflow:auto" onkeyup="ccHotkey(event)">
<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="workplaceht_demohtcombofieldg_sv">
<t:htpageaddons1 id="g_pa1"/>

<t:rowdynamiccontent id="g_2" contentbinding="#{eclntdefscr.popupIdTextSelection.htContent}"/>

<t:htpageaddons2 id="g_pa2"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
</body>
</html>