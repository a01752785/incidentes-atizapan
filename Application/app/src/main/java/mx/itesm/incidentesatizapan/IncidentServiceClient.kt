package mx.itesm.incidentesatizapan

import io.grpc.ManagedChannel
import io.grpc.StatusRuntimeException
import java.io.Closeable
import java.util.concurrent.TimeUnit

/**
 * @author: David Damian
 * The IncidentServiceClient class communicates the gRPC server with the application.
 */
class IncidentServiceClient(private val channel: ManagedChannel) : Closeable {
    private var stub = IncidentServiceGrpc.newBlockingStub(channel)

    fun getIncidents(): Incidents {
        val request = IncidentServiceRpcRequest.newBuilder().build()
        try {
            val response = stub.getIncidents(request)
            val incidents = Incidents.newBuilder()
            incidents.addIncident(response)
            println("Received:\n" +
                    "${response.coordinate.latitude},\n" +
                    "${response.coordinate.longitude},\n" +
                    "${response.incidentType},\n" +
                    "${response.referenceLocation},\n" +
                    "${response.description}\n")
            return incidents.build()
        } catch (e: StatusRuntimeException) {
            println("gRPC failed: ${e.status}")
            return Incidents.newBuilder().build()
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}