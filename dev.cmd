@echo off
rem Run from the repository root even when launched from another directory.
setlocal
cd /d "%~dp0"
rem Create local development settings once; preserve user changes afterward.
if not exist ".env" copy /y ".env.example" ".env" >nul
if "%~1"=="start" goto start
if "%~1"=="stop" goto stop
if "%~1"=="status" goto status
if "%~1"=="logs" goto logs
echo Usage: dev.cmd start ^| stop ^| status ^| logs
exit /b 1
:start
rem Build and wait until the application services pass health checks.
docker compose up -d --build --wait --wait-timeout 180
exit /b %errorlevel%
:stop
rem Keep named volumes so PostgreSQL and pgAdmin data survive.
docker compose down
exit /b %errorlevel%
:status
docker compose ps
exit /b %errorlevel%
:logs
docker compose logs -f --tail 100
exit /b %errorlevel%
