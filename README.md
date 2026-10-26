# BrewBuddy

A Kotlin Android coffee-app practice project with a drink catalog, favorites, and locally stored orders.

The app uses **XML layouts and Fragments**, ViewModels, repository interfaces, Retrofit, Room, and Hilt.

## What the code includes

- Onboarding and local user-name preferences.
- Hot and iced coffee API requests.
- Drink browsing and detail screens.
- Favorites backed by Room.
- Local order storage and order-list screens.
- Address-entry and payment UI.

Orders are stored locally through Room. The payment screen is part of the practice flow; this repository does not establish a real payment-processing integration.

## Architecture

| Area | Responsibility |
| --- | --- |
| `presentation/` | Fragments, adapters, and ViewModels |
| `domain/` | Models, repository interfaces, use cases, and dependency modules |
| `data/` | API client, Room entities and DAOs, mappers, and repository implementations |
| `res/layout/` | XML screen and item layouts |

Source root: [app/src/main/java/com/example/brewbuddy](app/src/main/java/com/example/brewbuddy).

## Run locally

1. Clone this repository and open it in Android Studio.
2. Install Android SDK 36 and use JDK 17 for the Gradle build.
3. Sync Gradle.
4. Run the `app` configuration on an emulator or device running Android 7.0 / API 24 or newer.

Networking is configured in [ApiManager.kt](app/src/main/java/com/example/brewbuddy/data/api/ApiManager.kt). The client uses the SampleAPIs base URL and requests `coffee/hot` and `coffee/iced`. API availability has not been verified.

## Code to explore

- [API routes](app/src/main/java/com/example/brewbuddy/data/api/WebServices.kt)
- [Order repository](app/src/main/java/com/example/brewbuddy/data/repository/OrderRepositoryImpl.kt)
- [Room database code](app/src/main/java/com/example/brewbuddy/data/db)
- [ViewModels and screens](app/src/main/java/com/example/brewbuddy/presentation)

## Status

A learning project for Android architecture, networking, local persistence, and UI navigation. Build and end-to-end behavior have not been verified as part of this documentation update.
