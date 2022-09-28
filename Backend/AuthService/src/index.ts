import * as jwt from "jsonwebtoken";
import * as argon from "argon2";
import * as express from "express";
import * as bodyparser from "body-parser";
import * as cors from "cors";
import axios from "axios";

//Secret for encoding the JWT (Json Web Token)
const privateData = require("../.secret/private.json");

//Database service uri
const databaseService = process.env.DATABASE_SERVICE || "http://localhost:5002";

//Configuration of express server
const app = express();
const port = process.env.PORT || 5001;

app.use(cors());
app.use(bodyparser.urlencoded({extended : true}));
app.use(bodyparser.json());

// Endpoint for getting JWT (Json Web Token)
app.post('/getToken', async (req ,res) => {
    let credentials = req.body.credentials;
    axios.get(databaseService + "/users/" + credentials.username)
        .then(async resp => {
            if (resp.data.docs.length > 0) {
                let userData = resp.data.docs[0];
                if (await argon.verify(userData.password, credentials.password)) {
                    let token = jwt.sign({id : userData._id, username : userData.username},privateData.secret);
                    res.send({code : 200, message : "Success", token})
                }
                else {
                    res.send({code : 401, message : "Wrong credentials"});
                }
            }
            else {
                res.send({code : 401, message : "Wrong credentials"});
            }
        })
        .catch(err => {
            res.send({code : 500, message : "Error with the databse service", err})
        });
});

// Endpoint for verifying JWT 
app.get('/verify', async (req,res) => {
    let token = req.headers["x-access-token"] as string;
    if (!token) {
        res.send({code : 400, message : "No auth-token provided"});
    }
    else {
        jwt.verify(token, privateData.secret,(err : any, decoded : any) => {
            if (err){
                res.send({code : 401, message : "Unauthorized"});
            }
            else {
                res.send({code : 200, message : "Authorized", decoded});
            }
        });
    }
});

// Enpoint for registering Users (Only for development)
app.post('/register', async (req, res) => {
    let credentials = req.body.credentials;
    const encriptedPswd = await argon.hash(credentials.password);
    res.send({encriptedPswd});
});

// Starting the express server
app.listen(port, () => console.log(`Authentication service is listening on http://localhost:${port}/`));