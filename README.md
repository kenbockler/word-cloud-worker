# Word Cloud Worker Microservice

This repository contains the Worker microservice for the Word Cloud application, 
which is a part of a microservices architecture designed for easy deployment using Docker.

The Worker microservice is responsible for processing text files, 
counting word occurrences, and saving the statistics to the PostgreSQL database.

## Features

* Text processing and word occurrence counting.
* Communication with RabbitMQ to receive text data for processing.
* Integration with PostgreSQL to save processed word occurrence statistics.

## Technologies

* Spring Boot: Java-based framework for building the microservice.
* PostgreSQL: Database service for storing word occurrence statistics.
* RabbitMQ: Message queue service for transferring messages from Core to Worker.

## Getting Started

### Prerequisites

* Java 17 or higher.
* Gradle for building the project.

### Building the project

1. Clone the repository:
    ```
    git clone https://github.com/kenbockler/word-cloud-worker.git
    ```
2. Navigate to the cloned repository folder:
    ```
    cd word-cloud-worker
    ```
3. Build the project:
    ```
    ./gradlew build
    ```
### Running the application

1. Ensure you have the Word Cloud Docker setup running (refer to the [Word Cloud Docker repository](https://github.com/yourusername/word-cloud-docker) for more information).

2. Run the application:
    ```
    java -jar build/libs/word-cloud-worker-1.0.0.jar
    ```
The Worker microservice should now be 
running and accessible on port 8081.

## Contributing

If you'd like to improve or extend the project, your contributions are welcome! Please create a new branch and make a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
