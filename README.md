# FIT3077_project 

# Contact Team Cabbage
Euan Lim | GrassyAirplane - elim0062@student.monash.edu <br>
Zoe Tay | NiftyCoffee - ztay0013@student.monash.edu <br>
Lim Zheng Haur | Zach - zlim0038@student.monash.edu <br>

# About Application
The Application is built in Two parts. <br>
The Client Frontend in React Vite TS <br>
The Server Backend in Java Springboot <br>
<br>
The Restful API the Client interacts with can be found in `server/src/main/java/com/team8/backend/WebController.java`
<br>
The main Project files for Nine Man Morris can be found in `server/src/main/java/com/team8/backend/ninemanmorris`
<br>

# Start Frontend
`cd client` <br>
`npm install` <br>
`npm run dev` <br>

# Start Backend
`cd server` <br>
`./gradlew build` <br>
`java -jar build/libs/backend-0.0.1-SNAPSHOT.jar` <br>

## Project ##
Currently, the board initially starts with 9 pieces, 5 white 4 black. <br>
<img src="./client/public/first.PNG"><br>
Making a move as the first player (default to white), swaps to second player <br>
<img src="./client/public/second.PNG"><br>
Making a move when not the turn, pings as invalid move and prevents <br>
<img src="./client/public/third.PNG"><br>
Lastly attempting to move to blank spaces or ontop of opposite color also pings as invalid<br>

*Important to note, without the backend a default board is still loaded but without invalid checking or turn swapping*<br>