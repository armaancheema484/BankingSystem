# Banking System Simulation

This is a Java-based Banking System Simulation project that demonstrates core Java concepts like OOP, collections, exception handling, streams, threads, executors, synchronization, and locks.

## Features
- **Account Management**: Create and manage bank accounts.
- **Transaction Processing**: Simulate concurrent transactions between accounts.
- **Thread Safety**: Use `ReentrantLock` for thread-safe account operations.
- **Logging**: Log transaction details using SLF4J.

## Technologies Used
- Java 21
- Maven
- SLF4J (for logging)
- JUnit (for testing)

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/BankSys.git
   ```
2. Navigate to project diectory
   ```bash
   cd BankSys
    ```
3. Compile and run
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="org.example.banking.BankingApp"
   ```
