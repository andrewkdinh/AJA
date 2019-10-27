# AJA Messenger

AJA is an open-source messaging app for communication with anybody, anywhere. It provides spam filtering, search of all messages (including PDFs and images), and high quality voice and video calling. 

This app is a fork of [Mesibo Messenger for Android](https://github.com/mesibo/messenger-app-android), created by Andrew Dinh, Abhik Ahuja, and Jake Johnson at [Cal Hacks](https://calhacks.io) 2019. 

## Features

- Group chats
- Rich messages (picture, video, audio, & other files)
- Location sharing
- Works with SMS as well as any app using the Mesibo backend, including [Mesibo Messenger for iOS](https://github.com/mesibo/messenger-app-ios)
- Message status and typing indicators
- Advanced search capabilities
- Spam filtering

## Contributing

### Clone the Repository

If you have git installed, this is a recommended approach as you can quickly sync and stay up to date with the latest version. To do so, run the following commands:

```bash
mkdir AJA && cd AJA
git clone https://github.com/origamiman72/messenger-app-android-sms-filter.git
```

### Build and Run

Building the code is as simple as:

 1. Launch Android Studio
 2. Open the project from the folder where you have downloaded the code using menu `File -> Open`
 3. Build using menu `Build -> Rebuild Project`
 5. Once the build is over, run on the device using menu `Run -> Run (app)

Login using your phone number, and you’re ready to go.

## SDKs and Backend

AJA uses many [Mesibo](https://mesibo.com) projects:

- Mesibo SDK
- Mesibo Messaging UI Module
- Mesibo Call UI Module
- [Mesibo backend](https://github.com/mesibo/messenger-app-backend)

AJA also uses the following third party libraries/services:

- [Facebook AccountKit](https://www.accountkit.com/) for phone verification
- [Google Maps](https://developers.google.com/maps/documentation/) and [Google Places](https://cloud.google.com/maps-platform/places/) SDKs for geolocation integration
- [Firebase](https://firebase.google.com/) for push notifications
- [CockroachDB](https://www.cockroachlabs.com/product/) for distributed SQL

## Documentation & Tutorials

- [Mesibo Documentation](https://mesibo.com/documentation/) 
- [Mesibo Get Started Guide](https://mesibo.com/documentation/get-started/).
- Tutorial - [A fully featured WhatsApp clone using mesibo](https://mesibo.com/documentation/tutorials/open-source-whatsapp-clone/)
