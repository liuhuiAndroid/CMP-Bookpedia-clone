This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Video: https://www.youtube.com/watch?v=WT9-4DXUqsM
Resources mentioned in the video:
Initial source code: https://github.com/philipplackner/CMP-Bookpedia/tree/initial
Final source code: https://github.com/philipplackner/CMP-Bookpedia/tree/master
Open Library API: https://openlibrary.org
Jetpack Compose Crash Course:    • The Jetpack Compose Beginner Crash Co... https://www.youtube.com/watch?v=6_wK_Ud8--0
Result Wrapper:    • This Is My FAVORITE Error Handling Class https://www.youtube.com/watch?v=MiLN2vs2Oe0
UiText explanation:    • How to Use String Resources In a View... https://www.youtube.com/watch?v=mB1Lej0aDus