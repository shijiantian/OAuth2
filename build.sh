#!/bin/bash
##打包成可运行的jar包,文件位于./build/libs/目录下
exec ./gradlew clean bootJar
