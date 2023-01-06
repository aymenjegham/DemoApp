# TestApp
## Native android Evaluation App for hiring purposes

This is just a simple two screen ,app, home screen with list fetched from API with random users ,click on one item and you'll navigate to the next screen with more details

- Kotlin 
- Clean architecture

## Features

- Modular architecture for lower compiling time  and easier to collaborate within a team
- Clean architecture : presentation layer, domain and data with respect to the inversion of dependency
- Coroutines ktx for asynchronous tasks , Coroutine flow 
- Room database for caching (offline app) 
- Retrofit as Service client
- Kotlin instead of groovy for gradle configuration
- MVVM component for state management 
- Alternative layout for landscape mode
- Dagger hilt for dependency injection
- Picasso library 
- Junit,Truth for Unit testing
- Repository pattern for single source of truth 
- Observables using Livedata 
- Databinding 
- Massive use of kotlin extention functions

> This app uses the view system with XML files to draw UI elements 
> This app uses Livedata 

## Perspective

This repo is basically just a demo and definitely lacks tons of stuff due to short time frame ,these are the possible future improvements :

- Moving from Room database to delight and the purpose is possible of doing KMP and code sharing
- Moving from Livedata to use of statflow,SharedFlow  ,the purpose is preparing for moving toward a full KMP structure
- moving from View system to jetpack compose to draw the UI 
- Using Pagination 3 for making API calls paginated and at the same time cached in local database
- Using crashlytics for error signaling
- More animations
- Doing more unit tests and UI tests 
- Assure CI/CD

For a glimpse on some of these mentioned capabilities please visit my other public repo for an idea on more in-depth UI tests and Jetpack compose ,Flow,...etc
https://github.com/aymenjegham/MyStore
