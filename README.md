# Voting CI Demo

A demonstration project showcasing Continuous Integration (CI) implementation using Jenkins, Maven, and SonarQube for a Java-based voting application.

## Overview

This project demonstrates a complete CI/CD pipeline for a Java application, featuring automated builds, testing, and code quality analysis. It serves as an educational example of modern DevOps practices and continuous integration workflows.

## Technologies Used

- **Java** - Primary programming language
- **Maven** - Build automation and dependency management
- **Jenkins** - Continuous Integration/Continuous Deployment
- **SonarQube** - Code quality and security analysis
- **Git** - Version control

## Project Structure

```
voting-ci-demo/
├── .idea/              # IntelliJ IDEA configuration files
├── src/                # Source code directory
├── .gitignore          # Git ignore rules
├── Jenkinsfile         # Jenkins pipeline configuration
├── pom.xml             # Maven project configuration
└── sonar-project.properties  # SonarQube analysis configuration
```

## Prerequisites

Before running this project, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher
- Jenkins (for CI/CD pipeline)
- SonarQube server (for code analysis)
- Git

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/ily1s/voting-ci-demo.git
cd voting-ci-demo
```

### Build the Project

```bash
mvn clean install
```

### Run Tests

```bash
mvn test
```

## CI/CD Pipeline

This project includes a Jenkinsfile that defines the automated CI/CD pipeline. The pipeline typically includes:

1. **Checkout** - Pulls the latest code from the repository
2. **Build** - Compiles the Java code using Maven
3. **Test** - Runs unit tests
4. **Code Analysis** - Performs static code analysis with SonarQube
5. **Package** - Creates deployable artifacts

### Setting Up Jenkins

1. Install Jenkins on your server or use a cloud-based solution
2. Install required plugins:
   - Maven Integration Plugin
   - SonarQube Scanner Plugin
   - Git Plugin
3. Configure Maven and JDK in Jenkins global tools
4. Create a new pipeline job pointing to this repository
5. Configure SonarQube server connection in Jenkins

## SonarQube Integration

The project includes a `sonar-project.properties` file for SonarQube configuration. To run SonarQube analysis locally:

```bash
mvn sonar:sonar \
  -Dsonar.host.url=http://your-sonarqube-server:9000 \
  -Dsonar.login=your-sonarqube-token
```

## Development Workflow

1. Create a new branch for your feature/bugfix
2. Make your changes and commit them
3. Push to the repository
4. Jenkins will automatically trigger the pipeline
5. Review the build results and SonarQube analysis
6. Create a pull request for code review

## Code Quality

This project maintains code quality through:

- Automated testing with Maven
- Static code analysis with SonarQube
- Code reviews via pull requests
- Continuous integration checks

## Acknowledgments

This project was created as a demonstration of CI/CD best practices and serves as a learning resource for implementing automated build and deployment pipelines.
