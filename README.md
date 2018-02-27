# API REST JPA
#
#### Quick start :
   - Download the API with zip or clone this repository with : 
``` sh
$ git clone https://github.com/quentin22800/jpasir.git
```
- Import the project into eclipse (or other IDE) as a Maven project.
- Change the property (YOUR_DATABASE_URL) in the persistence.xml :
``` sh
<property name="hibernate.connection.url" value="jdbc:mysql://YOUR_DATABASE_URL" />
```
- Run JPA Test as Java Application, in order to create the database and insert data.
- Create a new Maven Build with "tomcat7:run" as Goals value.
- Run the project with your new Maven Build

#### Base URL
```sh
http://localhost:8181/rest/domain/
```
#### People methods :
  - GET : ```/people ``` (no parameters). Returns a list of people.
  - GET : ```/friends ``` needs a "idPerson" parameter. The idPerson parameter is the id of the person from whom you want his friends.
  - POST : ```/people ``` needs a "name" parameter. The name parameter is the name of the person you want to add.
  - DELETE : ```/people ``` needs a "idPerson" parameter. The idPerson parameter is the id of the person you want to delete

#### Home methods :
  - GET : ```/homes ``` (no parameters). Returns a list of homes.
  - POST : ```/home ``` needs a "adresse" parameter and a "idOwner" parameter. The adresse parameter is the adresse of the home you want to create and the idOwner parameter is the owner of the home (the owner needs to already exist).
  - PUT : ```/home ``` needs a "idHome" parameter and a "adresse" parameter. The idHome parameter is the id of the home you want to update and the adresse parameter is the new adresse of the home.

#### ElectronicDevices methods :
  - GET : ```/electronicDevices ``` (no parameters). Returns a list of electronice devices.

#### Heaters methods 
  - GET : ```/heaters ``` (no parameters). Returns a list of heaters.

#### SmartDevices methods 
  - GET : ```/smartDevices ``` (no parameters). Returns a list of smart devices.