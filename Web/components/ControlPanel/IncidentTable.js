import { Card, CardBody } from "@material-tailwind/react";
import { incidentIcons } from "../../constants";

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
        <section className="overflow-auto relative">
          <table className="table-fixed md:table-auto">
            <thead>
              <tr>
                <th className="font-bold p-2 border-b text-left">Tipo</th>
                <th className="font-bold p-2 border-b text-left">Lugar</th>
                <th className="font-bold p-2 border-b text-left">Radio</th>
                {/*Put a button to delete selected incident*/}
              </tr>
            </thead>
            <tbody>
              {incidents.map((incident) => (
                <tr className="hover:bg-red-100">
                  <td className="p-2 text-left">
                    {incidentIcons[incident.incident_type]}
                  </td>
                  <td className="p-2 text-left">
                    {incident.reference_location}
                  </td>
                  <td className="p-2 text-left">{incident.risk_radius}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </CardBody>
    </Card>
  );
}
