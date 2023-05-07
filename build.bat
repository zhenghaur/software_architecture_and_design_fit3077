REM Navigate to client directory
cd client

REM Initialize npm
start "" npm install 

REM Start the client
start "" npm run dev

REM Navigate back to root directory
cd ..

REM Navigate to server directory
cd server

REM Build the server
start "" gradlew.bat build

REM Run the server
start "" java -jar build/libs/backend-0.0.1-SNAPSHOT.jar