@echo off

call :init_colors
goto :main

:init_colors
    rem red *;91
    rem green *;92
    rem yellow *;93
    rem blue *;94
    rem pink *;95
    rem light blue *;96
    rem purple *;97
    rem black background 40;*
    rem dark yellow background 43;*
    rem purple background 45;*
    rem blue background 44;*
    set BLUE=40;94
    set GRAY=40;89
    set YELLOW=40;93
    goto :eof

:eval_echo
    set input=%~1%
    call :color "%BLUE%" "%input%"
    call %input%
    goto :eof

:color
    set color=%~1%
    set message=%~2%
    echo [%color%m%message%[0m
    echo.
    goto :eof

:main
    call :eval_echo "set ROOT=%~dp0"
    call :eval_echo "set BASE=%1"
    call :eval_echo "set PLATFORM=%2"
    call :eval_echo "set ROM_FILE=%3"
    call :eval_echo "set COMMAND=%4"
    call :eval_echo "cd %ROOT%"

    call :eval_echo "java -jar emulator-1.0.jar %BASE% %PLATFORM% %ROM_FILE% %COMMAND%"

    echo.
    call :color "%YELLOW%" "Press Enter to continue"
    pause
    goto :eof