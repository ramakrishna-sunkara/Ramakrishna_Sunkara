# Ramakrishna_Sunkara

This is android app which is for system test

## Intro

This app containes single screen with one button (button text is LOAD DATA)

When you click on button 3 service calls (Truecaller10thCharacterRequest, TruecallerEvery10thCharacterRequest, TruecallerWordCounterRequest) runs simulataneously. And exach output will process and display in each textview.

### Prerequisites & Libs

* Android Studio latest version
* gladle 3.4.0

* Dagger dependency for DI
* Retrofit
* converter-scalars lib for convert the response to text/string
* RxJava
* LiveData

### Tech Intro

I implemented this app using MVVM pattern. I used Repository class is for read data from web and DataProcessManager is for processing the data for output.

I used Rxandroid for all long running process.

I created one custome view for loading and display data

## Video


## Author

* **Ramakrishna Sunkara** - [LinkedIn](https://www.linkedin.com/in/ramandroid/)
