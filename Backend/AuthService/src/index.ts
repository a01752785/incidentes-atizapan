//import * as jwt from "jsonwebtoken";
import * as bcrypt from "bcrypt";
import * as express from "express";
import * as bodyparser from "body-parser";
import * as cors from "cors";

//Testing (in reality this would be a Databse API)
const database = require("../database.json")

//Configuration of express server
const app = express();
const port = process.env.PORT || 5001;

app.use(cors());
app.use(bodyparser.urlencoded({extended : true}));
app.use(bodyparser.json());

app.post('/login', async (req ,res) => {
    let credentials = req.body.credentials;
    let userFound = true;
    for (let i = 0; i < database.users.length; i++){
        if (database.users[i].username === credentials.username) {
            userFound = false;
            let userData = database.users[i];
           if (bcrypt.compareSync(credentials.password,userData.password)) {
            res.send({code : "200", message : "Success"});
           }
           else {
            res.send({code : "401", message : "Incorrect Password"});
           }
        }
    }
    if (userFound) {
        res.send({code : "401", message : "User not found"});
    }
});


app.post('/register', async (req, res) => {
    let credentials = req.body.credentials;
    const encriptedPswd = bcrypt.hashSync(credentials.password, 10);
    res.send({encriptedPswd});
})


//Starting the express server
app.listen(port, () => console.log(`Authentication service is listening on http://localhost:${port}/`));