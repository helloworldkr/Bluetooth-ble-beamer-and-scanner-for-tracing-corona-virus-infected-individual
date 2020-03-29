### Introduction
This is a basic skeleton app with Bluetooth beacon functionality to assist easy development of contact tracing apps. The concept of human contact tracing using Bluetooth BLE beacons to fight corona virus was first introduced by the Singapore Government. This code repo can help other institutions and Governments easily develop such an app.


##### Link to TraceTogether app by Singapore Gov
[![Audi R8](https://img.youtube.com/vi/buj8ZTRtJes/0.jpg)](https://www.youtube.com/watch?v=buj8ZTRtJes "Help fight the spread of COVID-19")
###### News Coverage [latimes](https://www.latimes.com/world-nation/story/2020-03-24/coronavirus-singapore-trace-together") [ channelnewsasia](https://www.channelnewsasia.com/news/singapore/covid19-trace-together-mobile-app-contact-tracing-coronavirus-12560616)


# Details of this Repo

#### What does this app do
When you launch the app, it assigns a random ID to the device which starts getting advertised around as BLE beacon. At the same time, it listens to nearby devices that are using this app and displays their ID.


#### Features

- Converts own device into ble bluetooth beacon
- Displays and listens to all the nearby apps using this app
- Works in background (using foreground service)
- Uses Eddystone BLE protocol (can easily be customized to use other protocols eg. IBeacon)
- Eddystone BLE beacon can be scanned by IOS devices too

#### Screenshot
![](https://i.ibb.co/JcvcXmX/screen-Shot-1.jpg)


#### Library Used 

[AltBeacon Github](https://github.com/AltBeacon/android-beacon-library"AltBeacon")

### Future possible optimization 
- Battey optimization

#### Other possible ways to doing contact tracing as our understanding and their issues
- Scanning nearby Bluetooth devices and getting their mac addresses 
	Issue- >  Android doesn't allow to read own Bluetooth mac address programmatically so phone number and own mac address cannot be mapped
-  Google nearby to scan and broadcast id
	Issue ->  Cannot run nearby in the background
	
# Contribution
Feel Free to share your ideas and you can always add more functionalities to code and do a pull request. 

## Licensing
##### Apache 2.0 License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)



