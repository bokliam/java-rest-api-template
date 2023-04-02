# java-rest-api-template
Java Rest API Template modeled after a simple user service
- Author: Liam Bok

### MySQL instance
Install using:
```shell
$ brew install mysql
```
Connect by running:
```shell
$ mysql -u root
```
or using GUI like SequelAce with the following credentials:
- Host: localhost
- Username: root
- Port: 3306

### Build
```shell
$ mvn clean package
```

### Database Migrations
```shell
$ java -jar target/interview-api-1.0-SNAPSHOT.jar db migrate config.yml
```

### Run the Application
```shell
$ java -jar target/interview-api-1.0-SNAPSHOT.jar server config.yml
```

### Hitting Endpoints
```shell
$ curl --location --request GET 'http://localhost:9000/hello'
```
