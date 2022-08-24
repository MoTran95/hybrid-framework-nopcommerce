#!/bin/sh
project_path="/Volumes/Macintosh HD - Data/FullStack Automation Testing/03 - Java Hybrid Framework"
cd %projectPath%
set p=%PATH%
java -javaagent:"%projectPath%/libAllure/aspectjweaver-1.9.8.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllure\*;%ProjectPath%libExtendReportV5\*;%ProjectPath%libLog4J\*;%ProjectPath%libReportNG\*;%ProjectPath%libWebDriverManager\*" org.testng.TestNG "%ProjectPath%bin\runNopCommerceTest.xml"
source ~/.bash_profile
Allure serve ./allure-json/