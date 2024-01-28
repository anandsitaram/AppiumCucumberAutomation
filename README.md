# Mobile Automation Framework - Important Setup Notes

# NOTE - This repository is only for Android mobile automation, and I used an Android emulator to do this.

## Prerequisites
- Be an admin on your Windows/Mac.
- Use the latest Windows/MacOS operating system.
- If using an office machine, ensure that anti-virus and company policies are not blocking the installation of Appium and associated software.
- When practicing with an Android emulator, use a powerful processor and sufficient RAM.
- Avoid using phones from Chinese manufacturers that may restrict Appium due to security limitations.

## Install JAVA JDK and Configure Environment Variables
- Check if JAVA is already installed: `java -version`.
- JAVA JDK download link: [Oracle JDK Download](https://www.oracle.com/technetwork/java/javase/downloads/index.html).
    - Note: Please use Java 8/11/15; avoid Java 16 or higher as the current Appium Java Client 8.x.x is not compatible with Java 16+. You may use Java 16+ once Appium Java client becomes compatible.
- Create JAVA_HOME system environment variable and set it to JDK path (without bin folder).
- Edit the PATH system environment variable and add `%JAVA_HOME%\bin`.
    - Usually, the JDK path is "C:\Program Files\Java\<your_jdk_version>".

## Install Appium Desktop
- Download and install from [Appium Inspector Releases](https://github.com/appium/appium-desktop/releases).


## Install Appium Inspector
- Download and install from [Appium Inspector Releases](https://github.com/appium/appium-inspector/releases).

## Install Android Studio and Configure Environment Variables
- Android Studio download link: [Android Studio Download](https://developer.android.com/studio).
- Create ANDROID_HOME system environment variable and set it to the SDK path.
- Edit the PATH system environment variable and add the following:
    - `%ANDROID_HOME%\platform-tools`
    - `%ANDROID_HOME%\cmdline-tools`

## Emulator Setup: Accelerate Performance
- Launch Android Studio -> SDK Manager -> SDK Tools.
- For Intel processors, check "Intel x86 Emulator Accelerator (HAXM Installer)" and apply.
- For AMD processors, check "Android Emulator Hypervisor Driver for AMD Processors (installer)" and apply.

## Emulator Setup: Create AVD and Start It
- AVDs are resource-hungry; use a laptop with a powerful processor and sufficient RAM.
- Open Android Studio -> Configure -> Virtual Device Manager -> Create Virtual Device.
- Select Model -> Download the image for the desired OS version if not already downloaded.
- Start AVD.

## Emulator Setup: Create Driver Session Using Appium CLI
- Download the dummy app from [Appium Sample Apps](https://github.com/appium/appium/tree/master/packages/appium/sample-code/apps).

## Real Device Setup: Enable USB Debugging on Android Mobile
- Note: Steps may differ based on the phone manufacturer.
- Settings -> System -> About Phone -> Click Build Number 7-8 times.
- Settings -> Developer Options -> Enable USB Debugging.
- Permission pop-up: Check the box and press Allow to recognize the computer.
- Run "adb devices" in CMD prompt to check if the device is recognized.
- USB drivers:
    - Google: [Google USB Drivers](https://developer.android.com/studio/run/win-usb).
    - OEMs: [OEM USB Drivers](https://developer.android.com/studio/run/oem-usb).

