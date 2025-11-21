# CLAUDE.md
This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Coding Rules
You are a Senior Kotlin programmer with experience in the Android framework and a preference for clean programming and design patterns.
Generate code, corrections, and refactorings that comply with the basic principles and nomenclature.

## Essential Commands
### Build & Development
- `./gradlew build` - Build the entire project
- `./gradlew assembleDebug` - Build debug APK
- `./gradlew assembleRelease` - Build release APK

### Testing
- `./gradlew test` - Run all unit tests
- `./gradlew testDebugUnitTest` - Run unit tests for debug variant
- `./gradlew connectedAndroidTest` - Run instrumentation tests on connected devices
- `./gradlew testDebugScreenshotTest` - Run screenshot tests for debug build
- `./gradlew updateDebugScreenshotTest` - Update screenshot test references

### Code Quality
- `./gradlew ktlintCheck` - Run ktlint checks on all Kotlin sources
- `./gradlew ktlintFormat` - Format Kotlin code using ktlint
- `./gradlew lint` - Run Android lint checks
- `./gradlew lintFix` - Apply safe lint suggestions
- `./gradlew check` - Run all verification tasks (tests, lint, ktlint)

## Project Information
### Features
- Display list of installed apps with usage statistics
  - Shows app name, icon, install date, and last used date
  - Sorts apps by most recently used or installed
- Identify unused apps (apps that haven't been used for a while)
- Filter out system apps and show only user-installed apps
- Uninstall functionality for user apps (excludes system apps)
- Pull-to-refresh to reload app list
- Permission handling for PACKAGE_USAGE_STATS
  - Shows instructional UI when permission is not granted
  - Automatically checks permission status on app resume

### Architecture
This project follows Clean Architecture principles with MVVM pattern:

#### Layer Structure
- **Presentation Layer**
  - `view/`: Jetpack Compose UI components (100% Compose, no XML layouts)
  - `viewmodel/`: ViewModels managing UI state with StateFlow
  - Material 3 design with custom theme

- **Domain Layer**
  - `usecase/`: Business logic encapsulation (e.g., GetAppUsages)
  - Domain models in `model/` package

- **Data Layer**
  - `repository/`: Repository interfaces and implementations (using UsageStatsManager)

#### Technology Stack
- **Dependency Injection**: Dagger Hilt with @HiltViewModel and @Inject
- **Async Operations**: Kotlin Coroutines with StateFlow for reactive state management
- **UI Framework**: 100% Jetpack Compose with Material 3
- **Build System**: Gradle with Kotlin DSL (KTS) and Version Catalog
- **Testing**:
  - Unit tests with JUnit4, Robolectric
  - Compose UI tests with androidx.compose.ui.test
  - Screenshot tests with Android Compose Testing
- **Code Quality**: ktlint for Kotlin code formatting and style checks

### Coding Conventions
- Use English for code, Japanese for test method names and comments
- Always declare types explicitly, avoid `any`
- Use PascalCase for classes, camelCase for variables/functions
- Functions should be short (<20 instructions) with single purpose
- Prefer Jetpack Compose over XML layouts for new UI
- Follow SOLID principles and prefer composition over inheritance
- Use data classes for data structures
- Write tests following Given-When-Then convention
