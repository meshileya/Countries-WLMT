### MVVM + Clean Architecture while utilizing Gradle Version Catalogs

# Gradle version catalogs streamlines the dependencies while helping out with plugin managment
# across modules within the project

### Project Structure
- Presentation Layer: 
- -- UI -- Activity/Fragment/Adapter
- -- ViewModel
- Data Layer:
- -- Entity/Model
- -- Data Source
- -- -- Remote/Local
- -- -- -- Repository
- -- -- -- Service
- -- -- -- Exceptions
- Domain Layer:
- -- UseCase
- Dependency Injection
- -- Module
- -- Component