import { Card, CardBody, Button } from "@material-tailwind/react";
import axios from "axios";
import { databaseservice, incidentIcons } from "../../constants";

export default function IncidentTable(props) {
  const incidents = props.incidents;

  const onResolve = async (incident) => {
    try {
      await axios.delete(databaseservice + "/incidents/" + incident._id, {
        headers: { "x-access-token": localStorage.getItem("authToken") },
      });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <Card className="shadow-lg m-4 sm:mx-auto">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-red-300 mb-4">
            Fenómenos Activos
          </h2>
        </section>
        <section className="overflow-auto relative">
          <table className="table-fixed md:table-auto w-full">
            <thead>
              <tr>
                <th className="font-bold p-2 border-b text-left">Tipo</th>
                <th className="font-bold p-2 border-b text-left">Lugar</th>
                <th className="font-bold p-2 border-b text-left">Radio</th>
                <th className="font-bold p-2 border-b text-left"></th>
              </tr>
            </thead>
            <tbody>
              {incidents.map((incident) => (
                <tr className="hover:bg-red-50 rounded-lg">
                  <td className="p-2 text-left">
                    {incidentIcons[incident.incident_type]}
                  </td>
                  <td className="p-2 text-left">
                    {incident.reference_location}
                  </td>
                  <td className="p-2 text-left">{incident.risk_radius}</td>
                  <td className="hover:bg-white">
                    <Button
                      variant="outlined"
                      size="sm"
                      color="green"
                      onClick={() => {
                        onResolve(incident);
                        props.getIncidents();
                      }}
                    >
                      Resolver
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </CardBody>
    </Card>
  );
}
