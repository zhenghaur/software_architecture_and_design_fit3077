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

<h1>JAVA VERSION 17</h1>

*Running With Docker*
# Docker (After Building Individual Docker folders)
`docker compose build`
`docker compose up`

# Docker (Manual)
`cd server` <br>
`docker build -t 9mm-backend:0.0.1 .` <br> 
`docker run -it  -p9999:9999 backend-image-id` <br>

`cd client` <br>
`docker build -t 9mm-frontend:0.0.1 .` <br>
`docker run -it  -p5173:5173 frontend-image-id` <br>

*Running without Docker*
# Start Backend
`navigate to root`<br>
`cd server` <br>
`./gradlew build` <br>
`java -jar build/libs/backend-0.0.1-SNAPSHOT.jar` <br>

# Start Frontend
`navigate to root`<br>
`cd client` <br>
`npm install` <br>
`npm run dev` <br>

# Instructions & Important to note
1) Advised to start the Backend before the Frontend, as the population of the FE relies on the BE data <br>
2) To move, click, hold, drag and drop the pieces on the board display <br>
3) White player goes first <br>
4) The Backend is hosted on port 9999, and enables cors from all sources <br>
5) Advised to run FE & BE on seperate shell instances

## Project ##
Currently, the board initially starts with 9 pieces, 5 white 4 black. <br>
<img src="./client/public/first.PNG"><br>
Making a move as the first player (default to white), swaps to second player <br>
<img src="./client/public/second.PNG"><br>
Making a move when not the turn, pings as invalid move and prevents <br>
<img src="./client/public/third.PNG"><br>
Lastly attempting to move to blank spaces or ontop of opposite color also pings as invalid<br>

*Important to note, without the backend a default board is still loaded but without invalid checking or turn swapping*<br>