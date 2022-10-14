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
import Pin from "./pin";
import { useState, useCallback, useRef } from "react";
import { incidentIcons } from "../../constants";

const MAPBOX_TOKEN =
  "pk.eyJ1IjoiYmFzdGlhbjAxMTEiLCJhIjoiY2w4eGFjeDdhMDRvdzNyb2I1eHduMXlvNCJ9.Xf5BxwKhwxvriBruuj_mlw";

export default function StateMap(props) {
  const incidents = props.incidents;
  const [popupInfo, setPopupInfo] = useState(null);
  const mapRef = useRef();

  const onSelectIncident = useCallback(({ coordinate }) => {
    mapRef.current?.flyTo({
      center: [coordinate.latitude, coordinate.longitude],
      duration: 200,
    });
    //Make a zoom on click to display more info
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
            latitude: 19.59387,
            longitude: -99.25025,
            zoom: 11,
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
              latitude={incident.coordinate.longitude}
              longitude={incident.coordinate.latitude}
              anchor="bottom"
              onClick={(e) => {
                e.originalEvent.stopPropagation();
                onSelectIncident(incident);
                setPopupInfo(incident);
              }}
            >
              <Pin />
            </Marker>
          ))}

          {popupInfo && (
            <Popup
              anchor="top"
              latitude={Number(popupInfo.coordinate.longitude)}
              longitude={Number(popupInfo.coordinate.latitude)}
              onClose={() => setPopupInfo(null)}
            >
              <div>{incidentIcons[popupInfo.incident_type]}</div>
              //Put more info about theincident
            </Popup>
          )}
        </Map>
      </CardBody>
    </Card>
  );
}
