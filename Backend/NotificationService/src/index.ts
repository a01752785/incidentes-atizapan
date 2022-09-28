// Import the Firebase Admin to interact with FCM
import * as admin from "firebase-admin";
import * as express from "express";
import * as bodyparser from "body-parser";
import * as cors from "cors";

import * as AuthService from "./middleware/auth";

// Initialization of Firebase admin with secret 
let serviceAccount = require("../.secret/adminsdk.json");
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://alerta-atizapan-default-rtdb.firebaseio.com"
});

// Configuration of express server
const app = express();
const port = process.env.PORT || 5003;

app.use(cors());
app.use(bodyparser.urlencoded({extended : true}));
app.use(bodyparser.json());

// Main stream of notification (all devices)
const topic = "all";

app.get('/test', AuthService.verifyToken, (req, res) => {
    res.send({message : "All ok"});
});

// Endpoint "addNotification which expects a notification body to be sent to app"
app.post('/addNotification', AuthService.verifyToken, async (req,res) => {
    const notification = req.body.notification;
    const message = {notification, topic};
    admin.messaging().send(message)
        .then(response => {
            console.log("Successfully sent message:", response);
            res.send({code : 200, message : "Success!", messageId : response})
        })
        .catch(error => {
            console.log("Error sending message:", error);
            res.send({code : 500, message : "Panic!", error})  
        })
});

// Starting the express server
app.listen(port, () => console.log(`Notification service is listening on http://localhost:${port}/`));