# Backend
This is where all the code that qualifies as the Backend of the project is housed.

## Technology We Use For Now
- Firebase FCM
- Node v18.7.0

## API


#### Installation 
If you just cloned the repository, run the following commands in the path :  _incidentes-atizapan/Backend/_

```
#Install the firebase tools globally
npm install -g firebase-tools

#Login with your firebase to authenticate
firebase login

#Install the dependencies
npm install
```

#### Running Local Tests
To test the API, use the command
```
#This command will compile the TS code into JS, take that into account
npm run serve 
```
If the feature you're developing requires other firebase services, you'll need to get a security key for testing. Once you have your key (keep it in a safe place) use the following command depending on your OS
```
#Unix
export GOOGLE_APPLICATION_CREDENTIALS="path/to/key.json"
#Windows
set GOOGLE_APPLICATION_CREDENTIALS=path\to\key.json

```