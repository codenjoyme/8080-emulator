set ROOT=%~dp0
set PARAM=%1
cd %ROOT%

java -jar emulator-1.0.jar %PARAM%

echo Press Enter to continue
pause