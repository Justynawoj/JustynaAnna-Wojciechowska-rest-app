call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo cannot run runcrud
goto fail

:runbrowser

start chrome
if "%ERRORLEVEL%" == "0" goto setwebsite
echo Cannot open browser
goto fail

:setwebsite
start "" http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot load http://localhost:8080/crud/v1/task/getTasks
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.