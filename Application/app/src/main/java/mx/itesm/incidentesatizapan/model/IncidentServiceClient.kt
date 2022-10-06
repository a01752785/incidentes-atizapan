package mx.itesm.incidentesatizapan.model

import io.grpc.ManagedChannel
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
     * Any exception when communicating to the server will be managed by the view.
     * @return Incidents, the list of active incidents.
     */
    fun getIncidents(): Incidents {
        val request = IncidentServiceRpcRequest.newBuilder().build()
        return stub.withDeadlineAfter(2, TimeUnit.SECONDS).getIncidents(request)
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}