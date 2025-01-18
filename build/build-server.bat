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
    call :eval_echo "set HOST=localhost"
    call :eval_echo "set PORT=8080"

    call :eval_echo "cd .."
    call :eval_echo "set ROOT=%cd%"

    call :color "%YELLOW%" "Please run 'http://%HOST%:%PORT%/' after build"

    call :eval_echo "%ROOT%\mvnw.cmd clean package -DskipTests=true -Pjar-with-dependencies"
    call :eval_echo "%ROOT%\mvnw.cmd clean package jetty:run -DskipTests=true -Dserver.host=%HOST% -Dserver.port=%PORT% -Prun-server"

    echo.
    call :color "%YELLOW%" "Press Enter to continue"
    pause
    goto :eof