// Import the Firebase SDK from google cloud
import * as functions from "firebase-functions";

// Import the Firebase Admin to interact with FCM & initialization
import * as admin from "firebase-admin";
admin.initializeApp();

//Main stream of notification (all devices)
const topic = "all";

//Endpoint "addNotification which expects a notification body to be sent to app"
exports.addNotification = functions.https.onRequest(async (req, res) => {
  const notification = req.body.notification;
  const message =  {notification , topic};
  admin.messaging().send(message)
        .then(response => {
          console.log("Successfully sent message:", response);
          res.send({code : 200, message : "Success!", messageId : response})
        }) 
        .catch(error => {
          console.log("Error sending message:", error);
          res.send({code : 500, message : "Panic!", error})
        });
});