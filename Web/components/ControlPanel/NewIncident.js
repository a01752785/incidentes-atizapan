import {
  Card,
  CardBody,
  Input,
  Textarea,
  Select,
  Option,
  Button,
  Dialog,
  DialogBody,
  DialogHeader,
  DialogFooter,
  Alert,
  Switch,
  Tooltip,
} from "@material-tailwind/react";
import { useState } from "react";
import { useForm, Controller } from "react-hook-form";
import { databaseservice, notificationservice } from "../../constants";
import axios from "axios";
import Geocoder from "./Geocoder";

const incidentsTypes = {
  FIRE: "Incendio",
  FLOODING: "Indundacion",
  ACCIDENT: "Accidente",
  GAS_LEAK: "Fuga de Gas",
  WATER_LEAK: "Fuga de Agua",
};

export default function NewIncident(props) {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    control,
  } = useForm();
  const [output, setOutput] = useState();
  const [withNoti, setWithNoti] = useState(false);
  const [open, setOpen] = useState(false);
  const [createError, setCreateError] = useState(false);
  const [createSuccess, setCreateSuccess] = useState(false);
  const [createNotiError, setCreateNotiError] = useState(false);
  const [createNotiSuccess, setCreateNotiSuccess] = useState(false);

  const onSubmit = async (data) => {
    if (!output) {
      setCreateError(true);
      setOpen(false);
      return;
    }
    let coordinate = {
      longitude: output.center[0],
      latitude: output.center[1],
    };
    let reference_location = output.place_name.split(",").slice(0, 1).join(" ");
    const incident = {
      coordinate,
      reference_location,
      description: data.description,
      incident_type: data.incident_type,
      risk_radius: data.risk_radius,
    };
    try {
      const response = await axios.post(
        databaseservice + "/incidents",
        { incident },
        { headers: { "x-access-token": localStorage.getItem("authToken") } }
      );
      if (response.status == 200) {
        console.log("Created!");
        setCreateSuccess(true);
      }
    } catch (err) {
      console.log("Failed!");
      setCreateError(true);
      console.log("Error creating the incident");
    }
    if (withNoti) {
      try {
        const notification = {
          title: "Alerta : " + incidentsTypes[incident.incident_type],
          body: "En " + incident.reference_location,
        };
        const response = await axios.post(
          notificationservice + "/addNotification",
          { notification },
          { withCredentials: true, credentials: "include" }
        );
        if (response.status == 200) {
          setCreateNotiSuccess(true);
        }
      } catch (error) {
        console.log("Error enviando la notifiacion");
        setCreateNotiError(true);
      }
    }
    setOpen(false);
    props.getIncidents();
  };

  const handleOpen = () => setOpen(!open);
  return (
    <Card className="w-88 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-red-300">
            Registrar Incidente
          </h2>
        </section>
        <section className="text-left pt-2">
          <span>Crear un nuevo incidente</span>
          <div className="ml-1 mt-2">
            <Tooltip
              placement="right-end"
              content="Si activas esta opcion, le llegara una notificacion a los usuarios"
              animate={{
                mount: { scale: 1, x: 0 },
                unmount: { scale: 0, x: 25 },
              }}
              className="bg-red-200"
            >
              <Switch
                label="Añadir notificación"
                color="red"
                onChange={() => setWithNoti(!withNoti)}
              />
            </Tooltip>
          </div>
          <form>
            <section className="py-2">
              <div className="mb-3">
                <Geocoder setOutput={setOutput} />
              </div>
              <div className="mb-2">
                <Textarea
                  {...register("description", { required: true })}
                  color="red"
                  label="Descripción"
                />
              </div>
              <div className="mb-3">
                <Controller
                  name="incident_type"
                  control={control}
                  rules={{ required: true }}
                  render={({ field }) => (
                    <Select {...field} color="red" label="Tipo">
                      <Option value="FIRE">Incendio</Option>
                      <Option value="FLOODING">Indundacion</Option>
                      <Option value="CAR_ACCIDENT">Accidente</Option>
                      <Option value="GAS_LEAK">Fuga de Gas</Option>
                      <Option value="WATER_LEAK">Fuga de Agua</Option>
                      <Option value="OTHER">Otro</Option>
                    </Select>
                  )}
                />
              </div>
              <div className="mb-3">
                <Input
                  {...register("risk_radius", { required: true })}
                  type="number"
                  label="Radio (metros)"
                  color="red"
                />
              </div>
            </section>
            <section className="text-right pr-1">
              <Button onClick={handleOpen} color="red">
                Crear
              </Button>
            </section>
          </form>
          <div className="mx-auto my-2">
            <Alert
              size="sm"
              className=""
              color="red"
              show={createError}
              dismissible={{ onClose: () => setCreateError(false) }}
            >
              Error registrando el incidente...
            </Alert>
            <Alert
              size="sm"
              className=""
              color="green"
              show={createSuccess}
              dismissible={{ onClose: () => setCreateSuccess(false) }}
            >
              Incidente registrado
            </Alert>
            <Alert
              size="sm"
              className=""
              color="red"
              show={createNotiError}
              dismissible={{ onClose: () => setCreateNotiError(false) }}
            >
              Error enviando la notificacion ...
            </Alert>
            <Alert
              size="sm"
              className=""
              color="green"
              show={createNotiSuccess}
              dismissible={{ onClose: () => setCreateNotiSuccess(false) }}
            >
              Notificacion enviada
            </Alert>
          </div>
        </section>
      </CardBody>
      <Dialog open={open} handler={handleOpen}>
        <DialogHeader className="font-bold text-2xl text-pink-400">
          Seguro?
        </DialogHeader>
        <DialogBody>
          <section className="text-xl">
            <p>
              Al dar click confirmar, se registrara un nuevo incidente y se le
              comunicara a todos los usuarios de la aplicacion.
              <span className="font-bold text-pink-400">
                {" "}
                Esta accion es irreversible
              </span>
            </p>
          </section>
        </DialogBody>
        <DialogFooter>
          <div className="sm:mx-auto sm:mt-4 md:mx-4">
            <Button onClick={handleOpen} variant="outlined" color="red">
              Cancelar
            </Button>
          </div>
          <div className="sm:mx-auto sm:mt-4 md:mx-4">
            <Button onClick={handleSubmit(onSubmit)} color="green">
              Confirmar
            </Button>
          </div>
        </DialogFooter>
      </Dialog>
    </Card>
  );
}
