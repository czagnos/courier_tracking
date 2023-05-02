# COURIER TRACKING

## PREREQUISITE

 - Springboot 3.0.6
 - JAVA 17.0.5
 - Maven
 
 ## SETUP
 
 - Git clone current repository and open project in Intellij.
 ( !! If you can not see source code after open project in local, you should check the maven homepath in Intellij.
 File -> Settings -> Maven -> Choose Maven Homepath from your local or if possible you can use maven which provide from Intellij.)
 
 - Compile the with "mvn clean install". The reason of doing that is, application is using swagger documentation for generate api model and interfaces. The generated files is only generated when compile the project and including in 

target -> generated-source -> swagger -> src

If you are using Intellij you can mark directory as source root for this src file.

 - After compile the code, you run the application directly.



## API DOCUMENTATION

- To see api services which is created, you can open "https://editor.swagger.io/" and copy-paste the courier-api.yaml to the this website.
Swagger.io will give you a clear api documentation with details.

## API Services
All json samples are shared on "Courier Tracking.postman_collection.json" file on the repository. You can easily use with postman application.


### POST /courier/position : 
   Giving store and time response if courier is near the any store. As request parameter, you should give courier id, position and time.
   As instructions is mention. Store response is returning only courier is closer than 100 meters. Also if coruier is come with same request below one minute store response is not updated.
   
   If courier is not near any store another response is returned like  "Courier is not near any store".
   
   
### GET /courier/totalDistance/{coruierId}
  Every "courier/position" request is saved to the application cache with courierId key. So after some of request with "courier/position" you can get total distance for every courier with courierId. If there is no movement for courier response is returned like "Courier has not been move yet".
 
## GET /courier/totalDistance
  Get every courier which is saved to the app, list to the response.
      
  



