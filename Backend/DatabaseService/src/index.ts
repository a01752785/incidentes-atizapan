import * as express from "express";
import * as bodyparser from "body-parser";
import * as cors from "cors";
import * as mongoose from "mongoose";

import UserRoutes from "./routes/UserRoutes";

// Configuration of express server
const app = express();
const port = process.env.PORT || 5002;

app.use(cors());
app.use(bodyparser.urlencoded({extended : true}));
app.use(bodyparser.json());

// Database connection
const mongoUri = process.env.MONGODB_CONNSTRING || 'mongodb://127.0.0.1:27017/alerta_atizapan';
mongoose.connect(mongoUri);

app.use('/users', UserRoutes);

// Starting the express server
app.listen(port, () => console.log(`Database service is listening on http://localhost:${port}/`));