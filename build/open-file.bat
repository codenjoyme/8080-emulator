@echo off
setlocal enabledelayedexpansion

set "template=open-file.re_"
set "output=open-file.reg"
set "current_path=%~dp0"
set "formatted_path=%current_path:\=\\%"

if exist "%output%" del "%output%"

for /f "tokens=*" %%a in (%template%) do (
    set "line=%%a"
    set "line=!line:<PATH>\\=%formatted_path%!"
    echo !line! >> "%output%"
)

start "" "%output%"

endlocal