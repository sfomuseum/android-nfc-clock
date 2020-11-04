# android-nfc-clock

Simple Android application to demonstrate NFC hardware card emulation (HCE) functionality.

## Description

This is a simple Android application to demonstrate NFC hardware card emulation (HCE) functionality. It does so by using Android's HCE functionality to make the device "broadcast" itself as a new NFC NDEF tag, containing the current date and time, every second.

Does that sound absurd? It should, because it is. This application is meant to serve as a reference implementation and template for more sophisticated applications that want to use Android's NFC/HCE functionality.

## About the code

This application has a hybrid Java and Kotlin code base. That's because all of the actual work listening for NFC tag readers and sending responses is handled by @underwindfall 's `CardService.kt` class which was extracted from the [NFCAndroid](https://github.com/underwindfall/NFCAndroid/) repository.

I am not an experienced Java programmer, let alone Kotlin, so I am taking advantage of the fact that the Android Studio IDE will let me mix and match the two languages seamlessly. Thanks, Google.

My contritbution to this application is distilling the documentation to make a basic HCE-enabled application and the code in [MainActivity.java](#) to update the current time in a timer and demonstrate how to dispatch those updates to the `CardService` class.

### AndroidManifest.xml

```
<uses-permission android:name="android.permission.NFC" />

    <uses-feature
        android:name="android.hardware.nfc"
        android:required="true"/>

    <uses-feature
        android:name="android.hardware.nfc"
        android:required="true" />
```

```
<service
            android:name=".CardService"
            android:exported="true"
            android:permission="android.permission.BIND_NFC_SERVICE">
            <intent-filter>
                <action android:name="android.nfc.cardemulation.action.HOST_APDU_SERVICE" />
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>

            <meta-data
                android:name="android.nfc.cardemulation.host_apdu_service"
                android:resource="@xml/apduservice" />
       </service>
```

## See also

* https://github.com/underwindfall/NFCAndroid/