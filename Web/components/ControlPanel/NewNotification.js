import {
  Card,
  CardBody,
  Input,
  Select,
  Option,
  Switch,
  Button,
  Dialog,
  DialogBody,
  DialogHeader,
  DialogFooter,
  Alert,
} from "@material-tailwind/react";
import axios from "axios";
import { useState } from "react";
import { useForm, Controller } from "react-hook-form";
import { notificationservice } from "../../constants";

export default function NewNotification() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    control,
  } = useForm();
  const [customNoti, setCustomNoti] = useState(false);
  const [open, setOpen] = useState(false);
  const [createError, setCreateError] = useState(false);
  const [createSuccess, setCreateSuccess] = useState(false);

  const onSubmit = async (data) => {
    try {
      const response = await axios.post(
        notificationservice + "/addNotification",
        { notification: data },
        { withCredentials: true, credentials: "include" }
      );
      if (response.status == 200) {
        setCreateSuccess(true);
      }
    } catch (err) {
      setCreateError(true);
      console.log("Error creating the notification");
    }
    handleOpen();
  };
  const handleOpen = () => setOpen(!open);

  return (
    <Card className="w-90 mx-auto md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-red-300">
            Nueva Notificacion
          </h2>
        </section>
        <section className="text-left pt-2">
          <span>Crear una nueva notificacion</span>
          <div className="ml-1 mt-2">
            <Switch
              onChange={() => {
                setCustomNoti(!customNoti);
                reset();
              }}
              color="indigo"
              id="auto-update"
              label="Notificacion Personalizada"
            />
          </div>
          <form>
            {customNoti && (
              <section className="py-2">
                <div className="mb-3">
                  <Input
                    {...register("title", { required: true })}
                    className="mx-auto"
                    color="purple"
                    variant="outlined"
                    label="Titulo"
                    size="lg"
                  />
                </div>
                <div className="mb-3">
                  <Input
                    {...register("body", { required: true })}
                    className="mx-auto"
                    color="purple"
                    variant="outlined"
                    label="Mensaje"
                    size="lg"
                  />
                </div>
              </section>
            )}
            {!customNoti && (
              <section className="py-2">
                <div className="mb-3">
                  <Controller
                    name="title"
                    control={control}
                    rules={{ required: true }}
                    render={({ field }) => (
                      <Select {...field} label="Titulo" color="indigo">
                        <Option value="Indundacion">Indundacion</Option>
                        <Option value="Terremoto">Terremoto</Option>
                        <Option value="Incendio">Incendio</Option>
                        <Option value="Choque">Choque</Option>
                      </Select>
                    )}
                  />
                </div>
                <div className="mb-3">
                  <Input
                    {...register("body", { required: true })}
                    className="mx-auto"
                    color="indigo"
                    variant="outlined"
                    label="Mensaje"
                    size="lg"
                  />
                </div>
              </section>
            )}
            <section className="text-right pr-1">
              <Button onClick={handleOpen} color="pink">
                Enviar
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
              Error enviando la notificacion ...
            </Alert>
            <Alert
              size="sm"
              className=""
              color="green"
              show={createSuccess}
              dismissible={{ onClose: () => setCreateSuccess(false) }}
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
              Al dar click confirmar, mandaras una notificacion a todos los
              usuarios de la aplicacion.
              <span className="text-bold text-pink-400">
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
