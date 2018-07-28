#!/bin/sh

adb shell export CLASSPATH=/data/local/tmp/com.github.megatronking.funnydraw

adb shell exec app_process /system/bin com.github.megatronking.funnydraw.Main '$@'