# android-nfc-clock

Simple Android application to demonstrate NFC hardware card emulation (HCE) functionality.

## Description

This is a simple Android application to demonstrate NFC hardware card emulation (HCE) functionality. It does so by using Android's HCE functionality to make the device "broadcast" itself as a new NFC NDEF tag, containing the current date and time, every second.

Does that sound absurd? It should, because it is. This application is meant to serve as a reference implementation and template for more sophisticated applications that want to use Android's NFC/HCE functionality.

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
            android:name=".KardService"
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