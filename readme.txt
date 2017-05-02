Hello,

In order to build the project we need to have java 8 on system path
 Go to project directory and execute gradlew.bat build or ./gradlew build
 You can start the application invoking
	java -jar build/libs/social.jar
In order to access the API please open the
    http://localhost:8070/v2/api-docs.json
    or http://ec2-54-191-66-237.us-west-2.compute.amazonaws.com:8070/v2/api-docs.json
in swagger UI or swagger chrome plugin

POST /user/{userName}/follow
GET  /user/{userName}/posts
POST /user/{userName}/posts
GET  /user/{userName}/posts/followed

The database console is accessible under
http://localhost:8070/h2-console

Best Regards,
Arkadiusz Zawisza

