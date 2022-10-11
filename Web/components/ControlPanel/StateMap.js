import { Card, CardBody } from "@material-tailwind/react";
import Map, { Marker } from "react-map-gl";
import "mapbox-gl/dist/mapbox-gl.css";

const MAPBOX_TOKEN = process.env.GEO_APIKEY;

export default function StateMap(props) {
  const incidents = props.incidents;

  return (
    <Card className="w-[99%] shadow-lg mx-auto mg-4 lg:m-0">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-red-300 mb-2">Mapa</h2>
        </section>
        <Map
          reuseMaps
          initialViewState={{
            latitude: 19.59387,
            longitude: -99.25025,
            zoom: 11,
          }}
          style={{ width: "100%", height: 400 }}
          mapStyle="mapbox://styles/mapbox/streets-v9"
          mapboxAccessToken={MAPBOX_TOKEN}
        >
          <Marker longitude={-99.25025} latitude={19.59387} color="red" />
          {incidents.map((incident) => (
            <Marker
              latitude={incident.coordinate.longitude}
              longitude={incident.coordinate.latitude}
              color="red"
            ></Marker>
          ))}
        </Map>
      </CardBody>
    </Card>
  );
}
