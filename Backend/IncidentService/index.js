var PROTO_PATH = __dirname + '/incident.proto';

var grpc = require('@grpc/grpc-js');
var protoLoader = require('@grpc/proto-loader');
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
    var incident = {
      coordinate: {
        latitude: 19.589693,
        longitude: -99.229509
      },
      reference_location: "Calle Mariano Matamoros hola",
      description: "Incendio de casa. Mantenerse alejados.",
      incident_type: "WATER_LEAK",
      risk_radius: 5
    };
    var incident2 = {
      coordinate: {
        latitude: 19.589693,
        longitude: -99.229509
      },
      reference_location: "Calle Mariano Matamoros",
      description: "Incendio de casa. Mantenerse alejados.",
      incident_type: "CAR_ACCIDENT",
      risk_radius: 7
    };
    var incidents = [incident, incident2];
    console.log("call");
    return { incident : incidents };
}

function getIncidents(call, callback) {
    callback(null, getIncidentsFromDb(call.request));
}

/**
 * Starts an RPC server that receives requests for the Greeter service at the
 * sample server port
 */
function main() {
  var server = new grpc.Server();
  server.addService(incidentservice.IncidentService.service, {getIncidents: getIncidents});
  server.bindAsync('0.0.0.0:50051', grpc.ServerCredentials.createInsecure(), () => {
    console.log("gRPC server started on port 50051, with services getIncidents");
    server.start();
  });
}

main();
