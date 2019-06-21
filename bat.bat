@echo off  
title 自动打包工具【玄玉制作】  
color 02  
set project=%~n0  
set curdir=%~dp0  
set partition=%curdir:~0,1%  
cd %curdir:~0,20%  
  
REM 此文件需保存为ANSI编码，方可在Windows下执行  
REM java -cp .;jacob.jar test.CameraTest  
REM rem------------表示行注释，相当于Java中的//  
REM @echo off------表示关闭回显，即不显示本行即下面的命令行(默认DOS编程会把命令显示出来)  
REM color----------设置为02表示DOS窗口背景为黑色，前景(即字体)为绿色  
REM xcopy----------拷贝命令，/Y表示自动覆盖同名文件(否则会在DOS窗口询问Y还是N)  
REM del------------删除文件命令，/S用于删除目录树(即删除目录及目录下的所有子目录和文件)，/Q表示确认要删除(否则DOS会提示用户是否确认删除)  
REM rd-------------删除文件夹命令，/S和/Q含义与del命令的含义相同  
REM ren------------重命名文件，用法：[ren 11.exe 22.exe]  
REM echo 此批处理文件名为：%project%  
REM echo 此批处理文件所在路径为：%curdir%  
REM echo 此批处理文件所在盘符为：%partition%  
REM echo 此批处理文件所在工程为：%curdir:~0,30%  
  
echo 开始打包Maven工程 =================================  
xcopy %curdir:~0,19%\fxpgy-parent\pom.xml %curdir:~0,20% /Y  
REM call mvn clean package  
call mvn package  
echo Maven工程打包完毕 =================================  
  
echo;  
echo 准备清除临时文件 =================================  
REM rd %curdir:~0,19%\.settings /S /Q  
del %curdir:~0,19%\pom.xml /Q  
echo 临时文件清除完毕 =================================  
  
echo;  
echo 开始拷贝War包至桌面 =================================  
if exist "%userprofile%\Desktop\" (  
    REM 适用于Win7系统  
    xcopy %curdir:~0,19%\fxpgy-wth\target\wth.war %userprofile%\Desktop\ /Y  
) else if exist "%userprofile%\桌面\" (  
    REM 适用于XP系统  
    xcopy %curdir:~0,19%\fxpgy-wth\target\wth.war %userprofile%\桌面\ /Y  
)  
echo War包已经拷贝至桌面 =================================  
  
echo;  
pause 