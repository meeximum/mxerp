<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%@taglib prefix="t" uri="/WEB-INF/eclnt"%>


<!-- ========== CONTENT BEGIN ========== -->
<f:view>
<h:form>
<f:subview id="style_styledef_mxerpg_sv">
<t:beanprocessing id="g_1" >
<t:sessioncloser id="g_2" />
</t:beanprocessing>
<t:row id="g_3" >
<t:scrollpane id="g_4" height="100%" width="100%" >
<t:row id="g_5" >
<t:pane id="g_6" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_7" />
<t:row id="g_8" >
<t:textpane id="g_9" font="size:16" text="TITLEBAR - default" width="100%" />
</t:row>
<t:rowtitlebar id="g_10" bgpaint="rectangle(0,0,100%,100%,#e93145,#bf1e2b,vertical);rectangle(100%-250,0,100,100%,#ffffff00,#ffffffff,horizontal);rectangle(100%-150,0,150,100%,#ffffff)" font="size:18;weight:bold" foreground="#fffffff0" padding="3" stylevariant="def_default" text="Titlebar" />
</t:pane>
</t:row>
<t:row id="g_11" >
<t:pane id="g_12" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_13" />
<t:row id="g_14" >
<t:textpane id="g_15" font="size:16" text="BUTTON - default" width="100%" />
</t:row>
<t:row id="g_16" >
<t:button id="g_17" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" stylevariant="def_default" text="button" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_168" >
<t:pane id="g_169" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_170" />
<t:row id="g_171" >
<t:textpane id="g_172" font="size:16" text="BUTTON - Favorites" width="100%" />
</t:row>
<t:row id="g_173" >
<t:button id="g_174" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" stylevariant="def_favs" text="button" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_18" >
<t:pane id="g_19" background="#ff3333" padding="5" rowdistance="5" width="100%" >
<t:row id="g_20" >
<t:textpane id="g_21" font="size:16" text="BUTTON - header" width="100%" />
</t:row>
<t:row id="g_22" >
<t:button id="g_23" background="#ffffff" bgpaint="border(1,1,100%-1,100%-1,#000000,2);rectangle(2,2,100%-4,100%-4,#ffffff)" border="left:7;right:7;top:4;bottom:4;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="size:16;weight:bold" foreground="#000000" stylevariant="def_header" text="button" />
<t:coldistance id="g_24" />
<t:button id="g_25" background="#ffffff" bgpaint="border(1,1,100%-1,100%-1,#000000,2);rectangle(2,2,100%-4,100%-4,#ffcc0060)" border="left:7;right:7;top:4;bottom:4;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="size:16;weight:bold" foreground="#000000" stylevariant="def_header_active" text="button" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_176" >
<t:pane id="g_177" padding="5" rowdistance="5" width="100%" >
<t:row id="g_178" >
<t:textpane id="g_179" font="size:16" text="BUTTON - Variants" width="100%" />
</t:row>
<t:row id="g_180" >
<t:button id="g_181" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/eclntjsfserver/images/disk.png" rendered="#{d.DetailPB.renderCommitBtn}" stylevariant="def_save" text="#{rr.literals[&#39;save&#39;]}" />
<t:coldistance id="g_183" />
<t:button id="g_27" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/eclntjsfserver/images/cross.png" stylevariant="def_cancel" text="#{rr.literals[&#39;cancel&#39;]}" />
<t:coldistance id="g_185" />
<t:button id="g_187" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/images/trash_16_16.png" stylevariant="def_delete" text="#{rr.literals[&#39;delete&#39;]}" />
<t:coldistance id="g_188" />
<t:button id="g_189" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/eclntjsfserver/images/arrow_refresh.png" stylevariant="def_refresh" text="#{rr.literals[&#39;refresh&#39;]}" />
<t:coldistance id="g_191" />
<t:button id="g_192" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/images/edit_16_16.png" stylevariant="def_edit" text="#{rr.literals[&#39;edit&#39;]}" />
<t:coldistance id="g_194" />
<t:button id="g_195" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/eclntjsfserver/images/yesnopopup_yes.png" stylevariant="def_commit" text="#{rr.literals[&#39;commit&#39;]}" />
<t:coldistance id="g_196" />
<t:button id="g_197" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/eclntjsfserver/images/yesnopopup_no.png" stylevariant="def_rollback" text="#{rr.literals[&#39;rollback&#39;]}" />
<t:coldistance id="g_199" />
<t:button id="g_200" bgpaint="roundedrectangle(0,0,100%,100%,22,22,#f2f4f6);roundedrectangle(0,0,100%,100%,18,18,#00000000,#00000020,vertical);roundedborder(0,0,100%,100%,18,18,#00000018,2)" border="left:13;right:13;top:3;bottom:3;color:#00000000" contentareafilled="false" focusable="false" focusdrawborder="false" font="weight:bold" foreground="#393939" image="/images/add_document_16_16.png" stylevariant="def_new" text="#{rr.literals[&#39;new&#39;]}" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_36" >
<t:pane id="g_37" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_38" />
<t:row id="g_39" >
<t:textpane id="g_40" font="size:16" text="ROWBODYPANE - default - embedded: the default one is used with stand alone pages, the embedded one is to be used for embedded pages. Embedded pages do not provide some own padding and do not bring in own background coloring." width="100%" />
</t:row>
<t:row id="g_41" >
<t:pane id="g_42" height="50" width="200" >
<t:rowbodypane id="g_43" bgpaint="rectangle(0,0,100%,100%,#FFFFFFc0)" padding="5" rowdistance="5" stylevariant="def_default" >
<t:row id="g_44" >
<t:label id="g_45" text="Some Content in default" />
</t:row>
</t:rowbodypane>
</t:pane>
<t:pane id="g_46" height="50" width="200" >
<t:rowbodypane id="g_47" padding="0" rowdistance="5" stylevariant="def_embedded" >
<t:row id="g_48" >
<t:label id="g_49" text="Some Content in embedded" />
</t:row>
</t:rowbodypane>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_50" >
<t:pane id="g_51" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_52" />
<t:row id="g_53" >
<t:textpane id="g_54" font="size:16" text="PANE - headline: for areas that are highlighted, e.g. because of containing headline/summary data" width="100%" />
</t:row>
<t:row id="g_55" >
<t:pane id="g_56" background="#0000000a" bgpaint="rrectangle(0,0,100%,100%,#00000000,#00000020,vertical)" padding="left:8;top:3;bottom:3;right:3" stylevariant="def_headline" width="100%" >
<t:row id="g_57" >
<t:label id="g_58" foreground="#000000F0" text="Some content in the headline area" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_59" >
<t:textpane id="g_60" font="size:14;weight:bold" stylevariant="def_TRANSPARENT" text="PANE - popups (CC standard)" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_61" >
<t:pane id="g_62" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_63" />
<t:row id="g_64" >
<t:textpane id="g_65" font="size:16" text="LABEL - default - headline" width="100%" />
</t:row>
<t:row id="g_66" >
<t:label id="g_67" font="weight:bold" stylevariant="def_default" text="Default label" />
<t:link id="g_68" font="size:10" stylevariant="def_tableconfiguration" text="Tabelle konfigurieren" />
</t:row>
<t:row id="g_69" >
<t:label id="g_70" font="size:20;weight:bold" stylevariant="def_highlight" text="highlight" />
<t:formattedfield id="g_71" font="size:20;weight:bold" stylevariant="def_highlight" />
<t:field id="g_72" font="size:20;weight:bold" stylevariant="def_highlight" />
</t:row>
<t:row id="g_73" >
<t:label id="g_74" font="size:14;weight:bold" foreground="#000000F0" stylevariant="def_headline" text="Headline Label" />
</t:row>
<t:row id="g_75" >
<t:label id="g_76" align="center" background="null!" bgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000020,vertical)" font="size:12;weight:bold" foreground="#000000c0" stylevariant="def_header" text="Header" width="100" />
</t:row>
<t:row id="g_77" >
<t:label id="g_78" align="center" background="null!" bgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000060,vertical)" font="size:12;weight:bold" foreground="#000000c0" stylevariant="def_header2" text="Header2" width="100" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_79" >
<t:pane id="g_80" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_81" />
<t:row id="g_82" >
<t:textpane id="g_83" font="size:16" text="LINK - default - tableconfiguration: the &#34;tableconfiguration&#34; is used for the links above tables to open column selection and sort sequence" width="100%" />
</t:row>
<t:row id="g_84" >
<t:link id="g_85" font="size:10" stylevariant="def_tableconfiguration" text="Table Configuration" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_86" >
<t:pane id="g_87" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_88" />
<t:row id="g_89" >
<t:textpane id="g_90" font="size:16" text="FIXGRID / ARRAYGRID - default" width="100%" />
</t:row>
<t:row id="g_91" >
<t:fixgrid id="g_92" background="#ffffff" bordercolor="#00000030" borderheight="1" borderwidth="1" drawoddevenrows="false" height="60" objectbinding="#{tobedfined}" rowheight="20" sbvisibleamount="10" scrollbartype="dark" selectioncolor1="#ffcc0060" selectioncolor2="#ffcc0060" stylevariant="def_default" width="100%" >
<t:gridcol id="g_93" align="center" background="null!" bgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000020,vertical)" font="size:12;weight:bold" foreground="#000000c0" stylevariant="def_default" text="Column" width="100" />
<t:gridcol id="g_94" align="center" background="#ff3333" font="size:12;weight:bold" stylevariant="def_red" text="Column" width="100" />
</t:fixgrid>
<t:arraygrid id="g_95" background="#ffffff" bordercolor="#00000030" borderheight="1" borderwidth="1" drawoddevenrows="false" height="60" objectbinding="#{tobedfined}" rowheight="20" sbvisibleamount="10" selectioncolor1="#ffcc0010" selectioncolor2="#ffcc0040" stylevariant="def_default" width="100%" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_96" >
<t:pane id="g_97" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_98" />
<t:row id="g_99" >
<t:textpane id="g_100" font="size:16" text="TABBEDPANE / TABBEDPANETAB - default" width="100%" />
</t:row>
<t:row id="g_101" >
<t:tabbedpane id="g_102" background="#00000010" backgroundunselected="#fafafa" borderdarkshadowcolor="#00000000" borderlightcolor="#00000000" bordershadowcolor="#00000000" font="size:12;weight:bold" foreground="#000000c0" height="100" onetablineonly="true" onlydrawselectedtab="true" stylevariant="def_default" tabshadingbackground1="#00000030" tabshadingbackground2="#00000000" tabstyle="rounded" width="100%" >
<t:tabbedpanetab id="g_103" padding="5" rowdistance="2" stylevariant="def_default" text="tab" >
<t:row id="g_104" >
<t:label id="g_105" text="label" />
</t:row>
</t:tabbedpanetab>
<t:tabbedpanetab id="g_106" text="tab" />
</t:tabbedpane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_107" >
<t:pane id="g_108" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_109" />
<t:row id="g_110" >
<t:textpane id="g_111" font="size:16" text="FOLDABLEPANE - default" width="100%" />
</t:row>
<t:row id="g_112" >
<t:pane id="g_113" padding="10" width="100%" >
<t:row id="g_114" >
<t:foldablepane id="g_115" background="#00000010" font="size:12;weight:bold" headerbgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000020,vertical)" height="100" image="/images/search_minus.gif" imageto="/images/search_plus.gif" innerpadding="10" padding="1" stylevariant="def_default" text="Some title" titlepadding="0" width="100%" >
<t:row id="g_116" >
<t:label id="g_117" text="label" />
</t:row>
</t:foldablepane>
</t:row>
</t:pane>
</t:row>
</t:pane>
</t:row>
<t:row id="g_118" >
<t:pane id="g_119" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_120" />
<t:row id="g_121" >
<t:textpane id="g_122" font="size:16" text="Treenode" width="100%" />
</t:row>
<t:row id="g_123" >
<t:fixgrid id="g_124" objectbinding="#{tobedfined}" sbvisibleamount="2" >
<t:gridcol id="g_125" text="Treenode" width="100" >
<t:treenode id="g_126" background="null!" bgpaint="rectangle(0,0,100%,100%,#ffffff);rectangle(0,0,100%,100%,#00000000,#00000020,vertical)" font="size:12;weight:bold" foreground="#000000c0" stylevariant="def_header" />
</t:gridcol>
</t:fixgrid>
</t:row>
</t:pane>
</t:row>
<t:row id="g_127" >
<t:pane id="g_128" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_129" />
<t:row id="g_130" >
<t:textpane id="g_131" font="size:16" text="Icon" width="100%" />
</t:row>
<t:row id="g_132" >
<t:icon id="g_133" focusable="false" focusdrawborder="false" stylevariant="def_default" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_134" >
<t:pane id="g_135" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_136" />
<t:row id="g_137" >
<t:textpane id="g_138" font="size:16" text="Textarea" width="100%" />
</t:row>
<t:row id="g_139" >
<t:textarea id="g_140" selectallwhenfocussed="false" stylevariant="def_default" />
</t:row>
</t:pane>
</t:row>
<t:row id="g_141" >
<t:pane id="g_142" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_143" />
<t:row id="g_144" >
<t:textpane id="g_145" font="size:16" text="Outlookbar" width="100%" />
</t:row>
<t:row id="g_146" >
<t:outlookbar id="g_147" bgpaintdefault="rectangle(0,0,100%,100%,#e93145,#bf1e2b,vertical)" bgpaintrollover="rectangle(0,0,100%,100%,#e9314590,#bf1e2b90,vertical)" bgpaintselected="rectangle(0,0,100%,100%,#e93145,#bf1e2b,vertical)" font="size:16;weight:bold" fontselected="size:18;weight:bold" foregrounddefault="#000000" foregroundselected="#fffffff0" height="100" stylevariant="def_default" width="100" >
<t:outlookbaritem id="g_148" focusable="false" focusdrawborder="false" text="item" />
<t:outlookbaritem id="g_149" focusable="false" focusdrawborder="false" stylevariant="def_default" text="item" />
<t:outlookbarcontent id="g_150" background="null!" />
</t:outlookbar>
</t:row>
</t:pane>
</t:row>
<t:row id="g_151" >
<t:pane id="g_152" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_153" />
<t:row id="g_154" >
<t:textpane id="g_155" font="size:16" text="Workpage Selector" width="100%" />
</t:row>
<t:row id="g_157" >
<t:tabbedline id="g_164" bgpaintdefault="rectangle(0,0,100%,100%,#e93145,#bf1e2b,vertical)" bgpaintrollover="rectangle(0,0,100%,100%,#e9314590,#bf1e2b90,vertical)" bgpaintselected="rectangle(0,0,100%,100%,#e93145,#bf1e2b,vertical)" font="size:10;weight:bold" fontselected="size:12;weight:bold" foregrounddefault="#000000" foregroundselected="#fffffff0" stylevariant="def_WP_WORKPAGESELECTOR" >
<t:tabbedlinetab id="g_165" stylevariant="WP_WORKPAGESELECTOR" text="tab" />
<t:tabbedlinetab id="g_166" text="tab" />
</t:tabbedline>
</t:row>
</t:pane>
</t:row>
<t:row id="g_158" >
<t:pane id="g_159" padding="5" rowdistance="5" width="100%" >
<t:rowline id="g_160" />
<t:row id="g_161" >
<t:textpane id="g_162" font="size:16" text="(...copy this section for new definitions....)" width="100%" />
</t:row>
</t:pane>
</t:row>
</t:scrollpane>
</t:row>
<t:pageaddons id="g_pa"/>
</f:subview>
</h:form>
</f:view>
<!-- ========== CONTENT END ========== -->
