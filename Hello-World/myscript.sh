#!/bin/sh
ant
echo "PROJECT HAS BEEN BUILT"
adb install bin/HelloWorld.apk
echo "PROJECT HAS BEEN INSTALLED"
adb shell am instrument -w com.helloworld/android.test.InstrumentationTestRunner
echo "TESTS HAVE BEEN BUILT"
adb uninstall com.helloworld
echo "PROJECT HAS BEEN UNINSTALLED"
adb pull /sdcard/Android/data/TEST_RESULTS.xml
echo "COPIED TEST RESULTS"
adb shell rm /sdcard/Android/data/TEST_RESULTS.xml
echo "REMOVED TEST RESULTS FILE FROM DEVICE"