import * as express from "express";
import * as argon from "argon2";
import User from "../models/User";

// User table request methods (Create, Find, FindByX)
const router = express.Router();

// FindAll Endpoint
router.get('/', (req, res) => {
    User.find({},(err : any, docs : any) => {
        if (!err) {
            res.send({code : 200, docs});
        }
        else {
            res.send({code : 500, err});
        }
    });
});

// FindByUsernaem Endpoint
router.get('/:username', (req, res) => {
    const username = req.params.username;
    User.find({username}, (err : any, docs : any) => {
        if (!err) {
            res.send({code : 200, docs});
        }
        else {
            res.send({code : 500, err});
        }
    });
});

// Create User Endpoint
router.post('/', async (req, res) => {
    let credentials = req.body.credentials;
    const hashPassword = await argon.hash(credentials.password);
    const NewUser = new User({username : credentials.username, password : hashPassword});
    NewUser.save();
    res.send({code : 200, message : "User created"})
});

export default router;