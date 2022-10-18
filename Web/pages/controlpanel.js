import { Nav } from "../components/Nav";
import {
  NewNotification,
  NewIncident,
  IncidentTable,
  StateMap,
} from "../components/ControlPanel";
import { useEffect, useState } from "react";
import { databaseservice } from "../constants";
import axios from "axios";

export default function ControlPanel() {
  const [incidents, setIncidents] = useState([]);

  const getIncidents = async () => {
    const resp = await axios.get(databaseservice + "/incidents");
    setIncidents(resp.data.docs);
  };

  useEffect(() => {
    getIncidents();
  }, []);

  return (
    <section>
      <Nav />
      <h1 className="font-bold text-3xl lg:text-4xl pt-4 pl-6 lg:pl-8 lg:pt-4 ">
        Bienvenido al Panel de Control
      </h1>
      <section className="inline-block w-full md:w-[65%] align-top">
        <div className="ml-0 lg:ml-10 mt-15 mb-6">
          <StateMap incidents={incidents} />
        </div>
        <div className="ml-6">
          <IncidentTable incidents={incidents} getIncidents={getIncidents} />
        </div>
      </section>
      <section className="lg:inline-block align-top">
        <div className="w-full lg:w-auto">
          <NewNotification />
        </div>
        <div className="w-full lg:w-auto">
          <NewIncident getIncidents={getIncidents} />
        </div>
      </section>
    </section>
  );
}
