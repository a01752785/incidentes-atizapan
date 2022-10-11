import { Card, CardBody } from "@material-tailwind/react";

export default function IncidentTable(props) {
  const incidents = props.incidents;

  return (
    <Card className="shadow-lg m-4 sm:mx-auto">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-red-300 mb-4">
            Fenomenos Activos
          </h2>
        </section>
        <section>
          <table class="table-auto">
            <thead>
              <tr>
                <th>Tipo</th>
                <th>Lugar</th>
                <th>Radio</th>
              </tr>
            </thead>
            <tbody>
              {incidents.map((incident) => (
                <tr>
                  <td>{incident.incident_type}</td>
                  <td>{incident.reference_location}</td>
                  <td>{incident.risk_radius}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </CardBody>
    </Card>
  );
}
