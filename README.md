# Currency Converter

Currency Converter is a modern Android application that allows users to convert currencies from one unit to another seamlessly.  
The app fetches real-time exchange rates using the **Exchangerate-API**, providing accurate and up-to-date conversion results.  

This project was primarily created to learn and practice implementing **Continuous Integration (CI)** and **Continuous Delivery (CD)** with **unit testing** using **JUnit**, **MockK**, and **GitHub Actions**.  

## Features
- **Real-Time Currency Conversion**: Convert between a wide range of international currencies.
   - Fetches the most recent rates from the API when converting.
   - Displays formatted conversion results (e.g., `1000 KRW = 0.75 USD`).
   - Auto filter to search the country.  

## Technologies Used
- **Jetpack Compose**: Declarative UI for Android.
- **MVVM Architecture**: Structured and modular design.
- **Hilt**: Dependency injection.
- **Ktor**: HTTP client for network communication.
- **Coroutines + Flow**: Asynchronous programming and state management.
- **JUnit + MockK**: Unit testing and mocking.
- **GitHub Actions**: CI/CD pipeline for automated testing and APK releases.

## Additional Note
- If you want to use your own API key from the **Exchangerate-API** website:
  1. Get your own API key.
  2. Go to **local.properties**.
  3. Replace the value of `currencyApiKey` with your own key.   
