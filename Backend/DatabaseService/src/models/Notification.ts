import {Schema, model} from "mongoose";

// Model of the Notification "Table"
const NotificationSchema = new Schema({
    title : String,
    body : String,
    datetime : String,
});

const Notification = model('Notification', NotificationSchema);
export default Notification;