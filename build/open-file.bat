@echo off
setlocal EnableDelayedExpansion

set "INPUT_FILE=open-file.re_"
set "OUTPUT_FILE=open-file.reg"
set "CURRENT_DIR=%~dp0"
set "CURRENT_DIR=%CURRENT_DIR:~0,-1%"
set "CURRENT_DIR=%CURRENT_DIR:\=\\%"

if exist "%OUTPUT_FILE%" del "%OUTPUT_FILE%"

for /f "tokens=*" %%a in ('type "%INPUT_FILE%"') do (
    set "line=%%a"
    if not "!line!"=="!line:<PATH>=!" (
       set "line=!line:<PATH>=%CURRENT_DIR%!"
    )
    echo !line! >> "%OUTPUT_FILE%"
)

start "" "%OUTPUT_FILE%"