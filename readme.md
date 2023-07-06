RENT CRUD APPLICATION

    Before Startig the application
    1. Create Database in MYSQL : rental
    2.Configure application.properties file as per local system

    spring.datasource.url=jdbc:mysql://localhost:3306/rental
    spring.datasource.username=root // Your username
    spring.datasource.password=root //Your password
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql = true
    spring.jpa.generate-ddl=true



    jpa.hibernate.ddl-auto=update
    jpa.hibernate.show-sql=true
    jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    
For Testing 

    1. Create Products First then test related API's