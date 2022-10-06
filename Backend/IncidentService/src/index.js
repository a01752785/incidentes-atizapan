var grpc = require('@grpc/grpc-js');
var protoLoader = require('@grpc/proto-loader');

const port = process.env.PORT || 5004;

var PROTO_PATH = __dirname + '/incident.proto';
var packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
     longs: String,
     enums: String,
     defaults: true,
     oneofs: true
    });

var incidentservice = grpc.loadPackageDefinition(packageDefinition).mx.itesm.incidentesatizapan;

function getIncidentsFromDb(incidentServiceRpcRequest) {
    var incident1 = {
        coordinate: {
            latitude: 19.589693,
            longitude: -99.229509
        },
        reference_location: "Calle Mariano Matamoros",
        description: "Inundación por 20mm de lluvia",
        incident_type: "FLOODING",
        risk_radius: 500
    };
    var incident2 = {
        coordinate: {
            latitude: 19.569871,
            longitude: -99.246799
        },
        reference_location: "Colonia Atizapán Centro",
        description: "Incendio de casa. Mantenerse alejados.",
        incident_type: "FIRE",
        risk_radius: 100
    };
    var incident3 = {
        coordinate: {
            latitude: 19.589861,
            longitude: -99.221233
        },
        reference_location: "Calle Azalea",
        description: "Fuga de gas. Elementos de protección civil aproximándose.",
        incident_type: "GAS_LEAK",
        risk_radius: 300
    };
    var incident3 = {
        coordinate: {
            latitude: 19.570737,
            longitude: -99.220498
        },
        reference_location: "Av. Paseo de los Gigantes",
        description: "Fuga de agua. En Reparación.",
        incident_type: "WATER_LEAK",
        risk_radius: 50
    };
    var incident4 = {
        coordinate: {
            latitude: 19.573718,
            longitude: -99.238168
        },
        reference_location: "Calle 25 de Diciembre",
        description: "Accidente de tránsito. Ambulancias aproximándose.",
        incident_type: "CAR_ACCIDENT",
        risk_radius: 50
    };
    var incident5 = {
        coordinate: {
            latitude: 19.592465,
            longitude: -99.256743
        },
        reference_location: "Autopista Lecheria - Chamapa",
        description: "Árbol caído. Elementos de protección civil en el lugar.",
        incident_type: "OTHER",
        risk_radius: 100
    };

    var incidents = [incident1, incident2, incident3, incident4, incident5];
    console.log("call");
    return { incident : incidents };
}

function getIncidents(call, callback) {
    callback(null, getIncidentsFromDb(call.request));
}

/**
 * Starts an RPC server that receives requests for the Incident service at the
 * sample server port
 */
function main() {
    var server = new grpc.Server();
    server.addService(incidentservice.IncidentService.service, {getIncidents: getIncidents});
    server.bindAsync(`0.0.0.0:${port}`, grpc.ServerCredentials.createInsecure(), () => {
        console.log(`gRPC server started on port ${port}, with services getIncidents`);
        server.start();
    });
}

main();
