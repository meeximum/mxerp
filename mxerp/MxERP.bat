set TCP=C:\mySoftware\eclipse\workspace\MxERP\webcontent\eclnt\lib\eclnt.jar

::[Parameter]
::set PARAMETERS=%PARAMETERS% headerline=false			&::Kopfzeile
::set PARAMETERS=%PARAMETERS% footerline=false			&::Footer
::set PARAMETERS=%PARAMETERS% clientlogfile=c:/log.txt	&::Logfile
::set PARAMETERS=%PARAMETERS% loglevel=INFO				&::Loglevel [SEVERE, WARNING, INFO, FINE, ALL]
::set PARAMETERS=%PARAMETERS% sizefactor=1.0		    &::Float value by which all pixel sizes are multiplied in the client
::set PARAMETERS=%PARAMETERS% fontfactor=1.0			&::Float value by which all font sizes are multiplied in the client
::set PARAMETERS=%PARAMETERS% restrictedconfig=false
set PARAMETERS=%PARAMETERS% startx=0
set PARAMETERS=%PARAMETERS% starty=0
set PARAMETERS=%PARAMETERS% startwidth=max
set PARAMETERS=%PARAMETERS% startheight=max
set PARAMETERS=%PARAMETERS% maximized=true
::set PARAMETERS=%PARAMETERS% confirmexit=true
::set PARAMETERS=%PARAMETERS% errorscreen=/faces/eclntjsfserver/includes/showservererror.jsp
::set PARAMETERS=%PARAMETERS% imagebuffersize

::set PARAMETERS=%PARAMETERS% selectioncolor1
::set PARAMETERS=%PARAMETERS% selectioncolor2
::set PARAMETERS=%PARAMETERS% rollovercolor1
::set PARAMETERS=%PARAMETERS% rollovercolor2
::set PARAMETERS=%PARAMETERS% disabledcolor

set PARAMETERS=%PARAMETERS% kiosk=true				&::Often used in combination with startx=0 starty=0 startwidth=max startheight=max.
::set PARAMETERS=%PARAMETERS% touch=true				&::Scrollbars will be drawn wider/higher than normally in order to allow a usage via touch screen
::set PARAMETERS=%PARAMETERS% jsessionidname
::set PARAMETERS=%PARAMETERS% clientid
::set PARAMETERS=%PARAMETERS% animate=false
::set PARAMETERS=%PARAMETERS% sound=false
::set PARAMETERS=%PARAMETERS% reconnectpopup=false
::set PARAMETERS=%PARAMETERS% numberofreconnects=1
::set PARAMETERS=%PARAMETERS% downloadopenimmediately=true

::set PARAMETERS=%PARAMETERS% focusdarkbgpaint
::set PARAMETERS=%PARAMETERS% focuslightbgpaint
::set PARAMETERS=%PARAMETERS% regexerrorbgpaint
::set PARAMETERS=%PARAMETERS% errorbgpaint
::set PARAMETERS=%PARAMETERS% errorbgpaintcombo
::set PARAMETERS=%PARAMETERS% mandatorybgpaint
::set PARAMETERS=%PARAMETERS% mandatorybgpaintcombo

::set PARAMETERS=%PARAMETERS% imagetreenodeopened,
::set PARAMETERS=%PARAMETERS% imagetreenodeclose,
::set PARAMETERS=%PARAMETERS% imagetreenodeendenode

::set PARAMETERS=%PARAMETERS% imagesortup
::set PARAMETERS=%PARAMETERS% imagesortdown

::set PARAMETERS=%PARAMETERS% transferxmlinputupdate=true
::set PARAMETERS=%PARAMETERS% errorscreenproviderclassName

::set PARAMETERS=%PARAMETERS% unfilledbuttonpressedbgpaint
::set PARAMETERS=%PARAMETERS% unfilledbuttonmouseoverbgpaint

::set PARAMETERS=%PARAMETERS% filledbuttoncolor1
::set PARAMETERS=%PARAMETERS% filledbuttoncolor2
::set PARAMETERS=%PARAMETERS% filledbuttonpressedcolor1
::set PARAMETERS=%PARAMETERS% filledbuttonpressedcolor2
::set PARAMETERS=%PARAMETERS% filledbuttonmouseovercolor1
::set PARAMETERS=%PARAMETERS% filledbuttonmouseovercolor2
::set PARAMETERS=%PARAMETERS% filledbuttonradius

::set PARAMETERS=%PARAMETERS% embeddedwebappdir
::set PARAMETERS=%PARAMETERS% dateresolutionintopast=true
::set PARAMETERS=%PARAMETERS% tabonenter=false
::set PARAMETERS=%PARAMETERS% hotkeyrowexecute
::set PARAMETERS=%PARAMETERS% userhintfont Font							&::Font definition (“family:...;size:...”) that is used for rendering the user hints.

java.exe -cp %TCP% org.eclnt.client.page.PageBrowser ^
http://localhost:50000 	^
/mxerp/faces/index.jsp ^
false ^
%PARAMETERS%