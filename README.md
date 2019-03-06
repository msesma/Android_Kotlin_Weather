Paraguas
================

#Current weather and hourly forecast

Pet project for testing several technologies:
- Kotlin
- MVP architecture
- Dagger 2 Dependency injection
- RxJava 2
- Retrofit Rx
- Firebase Job dispatcher
- Android wear face with weather support
- Constraint layout
- MockWebServer based Unit testing for RxJava
- Espresso tests
 
Shows the Current weather and hourly forecast for the next 36 hours. 
Android location  permissions are supported by means of Easypermissions library (https://github.com/googlesamples/easypermissions). Storage permission must be granted manually, but it is only necessary for disk logging.

Get a Dark Sky API KEY and add it to your global gradle properties file as `DarkSky_api_token="lkhdkfldskf,jhkjhkjlsd"`

Based on https://github.com/kevinzetterstrom/forecast-android
https://api.darksky.net/forecast/apikey/51.5268642,-0.0927058