<!-- 
This file contains the README documentation for BankingSystem of the Banking System application.
-->
# BankingSystem

## Getting Started

### Prerequisites
- Docker
- Docker Compose
- Java Development Kit (JDK)
- Apache Maven

### Starting a Kafka Cluster Locally
1. Create a `docker-compose.yml` file with the following content:
    ```yaml
    version: '2'
    services:
      zookeeper:
        image: wurstmeister/zookeeper:3.4.6
        ports:
         - "2181:2181"
      kafka:
        image: wurstmeister/kafka:2.12-2.1.1
        ports:
         - "9092:9092"
        environment:
          KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
        volumes:
         - /var/run/docker.sock:/var/run/docker.sock
    ```
2. Run the following command to start the Kafka cluster:
    ```sh
    docker-compose up -d
    ```

### Starting a MySQL Database for Hibernate
1. Create a `docker-compose.yml` file with the following content:
    ```yaml
    version: '3.1'
    services:
      db:
        image: mysql:5.7
        restart: always
        environment:
          MYSQL_ROOT_PASSWORD: rootpassword
          MYSQL_DATABASE: banking_system
          MYSQL_USER: user
          MYSQL_PASSWORD: password
        ports:
          - "3306:3306"
    ```
2. Run the following command to start the MySQL database:
    ```sh
    docker-compose up -d
    ```

### Configuring Hibernate
1. Update your `hibernate.cfg.xml` file with the following configuration:
    ```xml
    <hibernate-configuration>
        <session-factory>
            <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
            <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/banking_system</property>
            <property name="hibernate.connection.username">user</property>
            <property name="hibernate.connection.password">password</property>
            <property name="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</property>
            <property name="hibernate.hbm2ddl.auto">update</property>
        </session-factory>
    </hibernate-configuration>
    ```

### Building and Running the Application
1. Build the application using Maven:
    ```sh
    mvn clean install
    ```
2. Run the application:
    ```sh
    java -jar target/your-application.jar
    ```

