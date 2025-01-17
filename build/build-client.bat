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
    rem replace all " with "
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
    call :eval_echo "cd .."
    call :eval_echo "set ROOT=%cd%"
    call :eval_echo "set OUT=%ROOT%\build\out"
    call :eval_echo "set BUILD=%ROOT%\target\webapp-synth"
    call :eval_echo "set PLATFORM=lik"

    call :color "%YELLOW%" "Please get content from '%OUT%' folder after build"

    call :eval_echo "%ROOT%\mvnw.cmd clean package -DskipTests=true -Pjar-with-dependencies"

    call :eval_echo "rd /s /q %OUT%"
    call :eval_echo "mkdir %OUT%"
    call :eval_echo "xcopy %BUILD% %OUT% /s /e /y"
    call :eval_echo "del %OUT%\application.jnlp"
    call :eval_echo "del %OUT%\deployJava.js"
    call :eval_echo "del %OUT%\index.html"

    call :eval_echo "cd %OUT%"
    call :eval_echo "run.bat . %PLATFORM%"

    echo.
    call :color "Press Enter to continue" %GRAY%
    pause
    goto :eof