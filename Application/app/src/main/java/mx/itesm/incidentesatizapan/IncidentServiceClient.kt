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