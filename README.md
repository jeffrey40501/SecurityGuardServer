## Running the Server Application

__Read this First__
To run this application, you will need to download, install, and launch MongoDB
on your local machine: http://www.mongodb.org/

To run the application:

1. Right-click on the Application class in the com.beiluoshimen.securityguard.account.Application.java
package, Run As->Java Application 

To stop the application:

Open the Eclipse Debug Perspective (Window->Open Perspective->Debug), right-click on
the application in the "Debug" view (if it isn't open, Window->Show View->Debug) and
select Terminate

## Accessing the Service

To view a list of the videos that have been added, open your browser to the following
URL:

http://localhost:8080/

you’will be directed to http://localhost:8080/login

To add a test account, run the AccountSvcClientApiTest by right-clicking on it in 
Eclipse->Run As->JUnit Test (make sure that you run the application first!)

## What to Pay Attention to

See the src/main/resources/application.properties file for configuration
options if you want to connect to a remote MongoDB instance.

## License 

  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

##Ownership

Hsieh Yu-Hua, hereby disclaims all copyright interest in the program “SecurityGuard” (which makes passes at compilers).




