@ECHO OFF
@java -Xmx512m -Xincgc -cp cursedserver.jar;lib\c3p0-0.9.1.2.jar;lib\mysql-connector-java-5.1.5-bin.jar cursed.server.Server
cls
StartServer.bat
@pause
