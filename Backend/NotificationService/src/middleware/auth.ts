import axios from "axios";
import { NextFunction, Request, Response } from "express";

//Auth-service URI for authenticating tokens
const authService = process.env.AUTH_SERVICE || "http://localhost:5001";

//Middlewarte for checking tokens
const verifyToken = async (req : Request, res : Response, next : NextFunction) => {
    const token = req.body.token || req.query.token || req.headers["x-access-token"];
    if (!token) {
        return res.send({code : "403" , message : "A token is required for this action"})
    }
    const response : any = await axios.get(authService + "/verify", {headers : {"x-access-token" : req.headers["x-access-token"] as string}})
        .catch(err => {
            console.log({code : 500, message : "Error with the auth service", err});
        });

    if (response.data.code !== 200 && response.data.message !== "Authorized") {
        return res.send({code : "403" , message : "Not authorized for the notification service"})
    }
    return next();
};

export {verifyToken};