function isIE() { if (navigator.appName == "Netscape") return false; else return true; }

var m_hotkeycallbacks = new Array();
var m_dateSeparator = ".";
var m_decimalSeparator = ",";
var m_thousandSeparator = ".";

function ccInitPageBeforeBody()
{
    ccNoBack();
}
function ccInitPageBodyOnload()
{
    ccNoBack();
}
function ccInitPageAfterBody()
{
}
function ccNoBack()
{
    window.history.forward();
}

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

function ccReformatToRegex(ccNode,ccRegex)
{
    if (ccRegex.test(ccNode.value))
      ccNode.value = ccNode.value.replace(ccRegex,'');
}
         
function ccRegisterFieldValueComponent(ptid,pvid,pformat,pformatmask)
{
    // set up connection
    var ccvc=document.getElementById(pvid);
    var cctc=document.getElementById(ptid);
    cctc.ccValueField = ccvc;
    ccvc.ccTextField = cctc;
    // transfer value and convert
    cctc.value = ccvc.value;
    ccFormatValueToFormat(cctc,pformat,pformatmask);
    cctc.ccBeforeValue = cctc.value;
}

function ccFormatValueToFormat(ccNode,ccFormat,ccFormatmask)
{
    if (ccFormat == null) return;
    if (ccNode.value == null || ccNode.value == "") return;
    if (ccFormat == "date")
        ccFormatValueToFormatDate(ccNode,ccFormat);
    if (ccFormat == "bigdecimal" || ccFormat == "int")
        ccFormatValueToFormatBigdecimal(ccNode,ccFormat,ccFormatmask);
}
function ccFormatValueToFormatDate(ccNode,ccFormat)
{
    var ccYear = parseInt(ccNode.value.substring(0,4));
    var ccMonth = parseInt(ccNode.value.substring(4,6)) - 1;
    var ccDay = parseInt(ccNode.value.substring(6,8));
    ccNode.value = ccBuildDateString(ccYear,ccMonth,ccDay);
}
function ccFormatValueToFormatBigdecimal(ccNode,ccFormat,ccFormatmask)
{
    var vText = ccNode.value;
    vText = ccReplaceAll(vText,".",m_decimalSeparator);
    // thousands
    var vSepIndex = vText.indexOf(m_decimalSeparator);
    if (vSepIndex < 0) vSepIndex = vText.length;
    for (var i=0; i<(vSepIndex/3-1); i++)
    {
        var vPos = vSepIndex - (i+1)*3;
        vText = vText.substring(0,vPos) + m_thousandSeparator + vText.substring(vPos);
    }
    // decimals
    var vNumberOfDecimals = 0;
    if (ccFormatmask != null)
    {
        var ccDecIndex = ccFormatmask.indexOf("dec");
        if (ccDecIndex >= 0)
            vNumberOfDecimals = parseInt(ccFormatmask.substring(ccDecIndex+3));
    }
    if (ccFormat == "int") vNumberOfDecimals = 0;
    vSepIndex = vText.indexOf(m_decimalSeparator);
    if (vSepIndex < 0)
    {
        vText = vText + m_decimalSeparator;
        vSepIndex = vText.length-1;
    }
    var vToAdd = vNumberOfDecimals - (vText.length - (vSepIndex+1));
    for (var i=0; i<vToAdd; i++)
        vText += "0";
    if (vText.substring(vText.length-1) == m_decimalSeparator)
        vText = vText.substring(0,vText.length-1);
    ccNode.value = vText;
}

function ccReformatToFormat(ccNode,ccFormat,ccFormatmask)
{
    if (ccFormat == null) return;
    if (ccNode.value == null || ccNode.value == "") return;
    if (ccFormat == "date")
        ccReformatToFormatDate(ccNode,ccFormat);
    else if (ccFormat == "bigdecimal" || ccFormat == "int")
        ccReformatToFormatBigdecimal(ccNode,ccFormat,ccFormatmask);
}
function ccReformatToFormatDate(ccNode,ccFormat)
{
    var ccNow = new Date();
    var ccValue = ccNode.value;
    var ccValues = ccValue.match(/(\d+)/g);
    if (ccValues == null) { ccResetValue(ccNode); return; }
    if (ccValues.length == 1) ccValues[1] = ccNow.getMonth() + 1;
    if (ccValues.length == 2) ccValues[2] = ccNow.getFullYear();
    if (ccValues.length != 3) { ccResetValue(ccNode); return; }
    var ccYear = parseInt(ccValues[2]);
    var ccMonth = parseInt(ccValues[1]) - 1;
    var ccDay = parseInt(ccValues[0]);
    if (ccYear < 100) ccYear = 2000 + ccYear;
    try
    {
        var ccCd = new Date();
        ccCd.setFullYear(ccYear,ccMonth,ccDay);
        if (ccCd.getFullYear() != ccYear || ccCd.getMonth() != ccMonth || ccCd.getDate() != ccDay) { ccResetValue(ccNode); return; }
        ccNode.value = ccBuildDateString(ccYear,ccMonth,ccDay);
        ccNode.ccBeforeValue = ccNode.value;
        ccNode.ccValueField.value = ccBuildDateValueString(ccYear,ccMonth,ccDay);
    }
    catch (exc)
    {
        ccResetValue(ccNode);
    }
}
function ccReformatToFormatBigdecimal(ccNode,ccFormat,ccFormatmask)
{
    var ccValue = ccNode.value;
    ccValue = ccReplaceAll(ccValue,m_thousandSeparator,"");
    ccValue = ccReplaceAll(ccValue,m_decimalSeparator,".");
    try
    {
        var ccFloat = parseFloat(ccValue);
        ccNode.value = "" + ccFloat;
        ccFormatValueToFormatBigdecimal(ccNode,ccFormat,ccFormatmask);
        ccNode.ccBeforeValue = ccNode.value;
        ccNode.ccValueField.value = ""+ccFloat;
    }
    catch (exc)
    {
        ccResetValue(ccNode);
    }
}

function ccBuildDateString(ccYear,ccMonth,ccDay)
{
    return ccAddLeading0(ccDay,2) + m_dateSeparator + ccAddLeading0(ccMonth+1,2) + m_dateSeparator + ccAddLeading0(ccYear,4);
}
function ccBuildDateValueString(ccYear,ccMonth,ccDay)
{
    return ccAddLeading0(ccYear,4) + ccAddLeading0(ccMonth+1,2) + ccAddLeading0(ccDay,2);
}

function ccResetValue(ccNode)
{
    alert("Your input " + ccNode.value + " does not match the desired format - and is reset to its previous value.");
    if (ccNode.ccBeforeValue != undefined)
        ccNode.value = ccNode.ccBeforeValue;
    else
        ccNode.value = "";
}

function ccAddLeading0(ccValue,ccDigits)
{
    var ccResult = "0000000000" + ccValue;
    return ccResult.substring(ccResult.length-ccDigits);
}

function ccReplaceAll(pValue,pFrom,pTo)
{
    if (pValue == null)
        return null;
    while (pValue.indexOf(pFrom) >= 0)
    {
        pValue = pValue.replace(pFrom,pTo);
    }
    return pValue;
}