#!/bin/sh
ant
echo "PROJECT HAS BEEN BUILT"
adb install bin/HelloWorld.apk
echo "PROJECT HAS BEEN INSTALLED"
adb shell am instrument -w com.helloworld/com.helloworld.InstrumentationTestRunner
echo "TESTS HAVE BEEN BUILT"
adb uninstall com.helloworld
echo "PROJECT HAS BEEN UNINSTALLED"
adb pull /sdcard/Android/TEST-all.xml
echo "COPIED TEST RESULTS"
