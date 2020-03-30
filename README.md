# teai_pracadomowatydzien9

This is simple application that load 1000 records from file (CSV format). Save these records into different databases 
and display time in console in ms using Aspect - how long takes to save these records into each database.
In this application I used : 
* Spring Boot
* JPA 
* Open CSV
* AOP
* H2 Database
* MongoDB Database
* RemoteMySQL Database

How to change database and run program? 
1. **Choose Database. Change application.properties file.** 
Setting for different databases you can find in folder src/main/resources/DBconfig. In This folder you can find settings for H2, MongoDB and RemoteMySQL Database. Just simple copy+paste in application.properties file.
1. After when you choose database next step is to **move Annotations @AspectServiceAnnotation and @EventListener(ApplicationReadyEvent.class) for specific method in Start.java.** 
Use following table

Your Database | init Method
--------- | ---------------
H2 | initForSql() // default
MongoDB | initForNoSql()
RemoteMySQL | initForRemoteMySql()


Things to do:
- [ ] Connect to all databases in single program run and load 1000 records and display results
