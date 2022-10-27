import { Card, CardBody } from "@material-tailwind/react";
import Map, {
  Marker,
  NavigationControl,
  FullscreenControl,
  ScaleControl,
  Popup,
  MapRef,
  useMap,
} from "react-map-gl";
import "mapbox-gl/dist/mapbox-gl.css";
import { useState, useCallback, useRef } from "react";
import { incidentIcons } from "../../constants";

const MAPBOX_TOKEN = "MAPBOX_API" || process.env.GEO_APIKEY;

export default function StateMap(props) {
  const incidents = props.incidents;
  const [popupInfo, setPopupInfo] = useState(null);
  const mapRef = useRef();

  const onSelectIncident = useCallback(({ coordinate }) => {
    mapRef.current?.flyTo({
      center: [coordinate.longitude, coordinate.latitude],
      duration: 200,
    });
  }, []);

  return (
    <Card className="w-[99%] shadow-lg mx-auto mg-4 lg:m-0">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-red-300 mb-2">Mapa</h2>
        </section>
        <Map
          ref={mapRef}
          reuseMaps
          initialViewState={{
            latitude: 19.56831,
            longitude: -99.2399,
            zoom: 12,
          }}
          style={{ width: "100%", height: 400 }}
          mapStyle="mapbox://styles/mapbox/streets-v9"
          mapboxAccessToken={MAPBOX_TOKEN}
        >
          <FullscreenControl position="top-left" />
          <NavigationControl position="top-left" />
          <ScaleControl />

          {incidents.map((incident, index) => (
            <Marker
              key={`marker-${index}`}
              latitude={incident.coordinate.latitude}
              longitude={incident.coordinate.longitude}
              anchor="bottom"
              color="red"
              onClick={(e) => {
                e.originalEvent.stopPropagation();
                onSelectIncident(incident);
                setPopupInfo(incident);
              }}
            ></Marker>
          ))}

          {popupInfo && (
            <Popup
              anchor="top"
              latitude={Number(popupInfo.coordinate.latitude)}
              longitude={Number(popupInfo.coordinate.longitude)}
              onClose={() => setPopupInfo(null)}
            >
              <div>{incidentIcons[popupInfo.incident_type]}</div>
              <ul>
                <li>Lugar : {popupInfo.reference_location}</li>
                <li>Hora : {popupInfo.timestamp}</li>
              </ul>
            </Popup>
          )}
        </Map>
      </CardBody>
    </Card>
  );
}
