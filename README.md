# LIBRARY MANAGEMENT APP
A simple springboot application designed to help manage the operations of a library.

## Technologies
![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue?logo=apachemaven&logoColor=white)

## Prerequisites
- Java 17+
- Maven

## Installation
1. Clone the repository:
    ```bash
    https://github.com/Esosa2006/-UPDATED-Library-Management-System.git

2. cd -UPDATED-Library-Management-System

3. mvn clean install

4. mvn exec:java -Dexec.mainClass="com.LBS.Library.Management.System.LibraryManagementSystemApplication"

## Usage
You’ll probably need to open the main and try running a few things. It supports:
- Renting of books
- Returning rentals
- Adding new books
- Editing existing book details
- Removing books
- etc.

## 📁 File Structure
```powershell
src/
 ├── main/
 │   ├── java/
 │   │   └── com/LBS/Library/Management/System/
 │   │       ├── AvailabilityStatus.java
 │   │       ├── LibraryManagementSystemApplication.java
 │   │       ├── controllers/
 │   │       │   ├── LibrarianController.java
 │   │       │   └── UserController.java
 │   │       ├── dtos/
 │   │       │   ├── BookDto.java
 │   │       │   ├── RentalsDto.java
 │   │       │   └── UserRegistrationDto.java
 │   │       ├── entities/
 │   │       │   ├── Admin.java
 │   │       │   ├── Book.java
 │   │       │   ├── Librarian.java
 │   │       │   ├── Rentals.java
 │   │       │   └── User.java
 │   │       ├── exceptions/
 │   │       │   ├── Exception.java
 │   │       │   ├── GlobalExceptionHandler.java
 │   │       │   └── GlobalRuntimeException.java
 │   │       ├── mappers/
 │   │       │   ├── BookMapper.java
 │   │       │   └── RentalsMapper.java
 │   │       ├── repositories/
 │   │       │   ├── BookRepository.java
 │   │       │   ├── RentalRepository.java
 │   │       │   └── UserRepository.java
 │   │       └── services/
 │   │           ├── LibrarianService.java
 │   │           └── UserService.java
 │   └── resources/
 │       └── application.yml
 └── test/
     └── java/com/LBS/Library/Management/System/
         └── LibraryManagementSystemApplicationTests.java
```

## Steps to Contribute
Contribute if you want.
1. Open an issue if you're serious.
2. Fork it, clone it.
3. Make a branch:
   ```bash
    git checkout -b your-branch-name
4. Do your thing.
5. Commit and push.
6. Open a pull request.

## License
[MIT](https://choosealicense.com/licenses/mit/)
