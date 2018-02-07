# CreditScore
 
#### A technical test

## Structure
`domain.network` contains the two entities that represent the data fetched from the server. All other values are discarded during the JSON de- serialisation. `NetworkError` is also added.
The main activity `ui.MainActicity` loads a ViewModel `CreditScoreViewModel` using the Google Android Lifecycle architectural components. This contains the data and error as `LiveData` objects. The function `updateCreditScore` also creates an RXJava SingleObservable that uses Retrofit to query the REST service. This updates the `error` or `creditReport` LiveData.<br> 
The Activity then draws a circle arc to represent the data, and fills in some text.

## Testing
Classes follow SOLID principles where possible, and should be easy to test. <br>
Tests are provided for `ClearscoreRestAPI`, but currently the second test (`getCreditSuccess()`)fails when both tests are run together. This requires further investigation.

## Problems
Android animations can not have values set at runtime. I looked at using an XML AnimatedVector, but found that I could not set values within the drawing, like trimPathStart. This lost me some time.<br>
I attempted to add dagger2. This proved a bigger challenge than I expected. There is little written about using Dagger 2 with Kotlin, and although I seemed to have some early success, it proved a more difficult problem for the length of this exercise. 
## Third Party Libs
`AppCompat` and `Constraint Layout` from Android support<br>
`Android lifecycle architecture components` for the lifecycle aware ViewModel and LiveData<br>
`Retrofit2` for Rest/Http calls. Overkill for a single http call, but how I would want to build something bigger out<br>
`RxJava2` for async handling. 
## What’s missing
Crashalytics, dagger2, analytics and more unit testing for a production project

 


 

