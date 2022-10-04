package mx.itesm.incidentesatizapan.model

import io.grpc.ManagedChannel
import io.grpc.StatusRuntimeException
import mx.itesm.incidentesatizapan.IncidentServiceGrpc
import mx.itesm.incidentesatizapan.IncidentServiceRpcRequest
import mx.itesm.incidentesatizapan.Incidents
import java.io.Closeable
import java.util.concurrent.TimeUnit

/**
 * @author: David Damian
 * The IncidentServiceClient class communicates the gRPC server with the application.
 */
class IncidentServiceClient(private val channel: ManagedChannel) : Closeable {
    private var stub = IncidentServiceGrpc.newBlockingStub(channel)

    /**
     * Calls the GetIncidents service in the gRPC server to retrieve all the active incidents.
     * @return Incidents, the list of active incidents.
     */
    fun getIncidents(): Incidents {
        val request = IncidentServiceRpcRequest.newBuilder().build()
        try {
            val response = stub.withDeadlineAfter(2, TimeUnit.SECONDS).getIncidents(request)
            println("Received: ${response.incidentCount}")
            return response
        } catch (e: StatusRuntimeException) {
            println("gRPC failed: ${e.status}")
            return Incidents.newBuilder().build()
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}