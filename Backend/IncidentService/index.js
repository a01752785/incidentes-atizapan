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
      reference_location: "Calle Mariano Matamoros",
      description: "Incendio de casa. Mantenerse alejados."
    };
    console.log("call");
    return incident;
}

function getIncidents(call, callback) {
    callback(null, getIncidentsFromDb(call.request));
    // call.write(getIncidentsFromDb(call.request));
    // call.close();
}

/**
 * Starts an RPC server that receives requests for the Greeter service at the
 * sample server port
 */
function main() {
  var server = new grpc.Server();
  server.addService(incidentservice.IncidentService.service, {getIncidents: getIncidents});
  server.bindAsync('192.168.1.68:50051', grpc.ServerCredentials.createInsecure(), () => {
    console.log("server started");
    server.start();
    obj = getIncidentsFromDb(1);
    console.log(obj);
  });
}

main();
