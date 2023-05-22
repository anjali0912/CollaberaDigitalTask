# CollaberaDigitalTask

User needs to register to access the weather application where the data is stored in local database using RoomDatabase. And login is required for the same. Validation for the email format, empty fields are added. After successful home screen is displayed.

In the first tab of the home screen the data is fetched from OpenWeatherApi based on the default city. A search option is provided where user can enter the city name and get the details of the entered city. If data is not found for the entered city, in that case an error screen will be displayed with a refresh icon, on click of the refresh icon user can able to reload or refresh the screen. Now in this case, the data will be displayed based on the defualt city.

Every time the api is called, the location data is stored in the local database using Roomdatabase. In the second tab, all the data for recently searched locations are displayed.

* Dependency Injection: Koin
* Architecture: MVVM + clean
* Database: Roomdatabase
* UI: Jetpack Compose
* Testing: Mockito(added test cases for domain layer)
* OpenWeatherApi: Key is added is gradle.properties with the name APIKey
* GoogleMap: Key is added is string.xml(app module) with the name google_maps_key
* CurrentWeatherIcon: Icon displayed in the first tab, is coming from openWeatherApi


![task](https://github.com/anjali0912/CollaberaDigitalTask/assets/18376847/c04ddb15-b603-4359-91d8-9acd1416cb29)
