import {Schema, model} from "mongoose";

// Model of the Incident "Table"
const IncidentSchema = new Schema({
    coordinate : Object,
    reference_location: String,
    description: String,
    incident_type: String,
    risk_radius: Number
});

const Incident = model('Incident', IncidentSchema);
export default Incident;