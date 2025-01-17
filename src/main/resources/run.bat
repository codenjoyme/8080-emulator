set ROOT=%~dp0
set BASE=%1
set PLATFORM=%2
set ROM_FILE=%3
cd %ROOT%

java -jar emulator-1.0.jar %BASE% %PLATFORM% %ROM_FILE%

echo Press Enter to continue
pause