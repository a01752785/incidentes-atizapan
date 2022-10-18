import { Input, Button, Card, Alert } from "@material-tailwind/react";
import { useForm } from "react-hook-form";
import { useRouter } from "next/router";
import { useState, Fragment } from "react";
import { authservice } from "../constants";
import axios from "axios";

export default function Home() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const router = useRouter();
  const [loginError, setLoginError] = useState(false);
  const onSubmit = async (data) => {
    try {
      const res = await axios.post(
        authservice + "/getToken",
        { credentials: data },
        { withCredentials: true, credentials: "include" }
      );
      if (res.status == 200) {
        localStorage.setItem("authToken", res.data.token);
        router.push("/controlpanel");
      }
      console.log("Miau : ", res);
    } catch (err) {
      console.log("Error con su autenticacion");
      console.log(err);
      setLoginError(true);
    }
  };

  return (
    <section className="h-screen bg-gradient-to-r from-purple-50 to-purple-100">
      <div className="container px-6 py-12 h-full">
        <div className="flex justify-center items-center flex-wrap h-full g-6 text-gray-800">
          <div className="md:w-6/12 lg:w-6/12 mb-12 md:mb-0">
            <img
              src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fobjetivocastillalamancha.es%2Fsites%2Fdefault%2Ffiles%2Fstyles%2Fnodonoticia%2Fpublic%2FNOTICIAS%2Fjuanantonio%2FIMAGENES%2Flogo-proteccion-civil.png%3Fitok%3DcTTRsor8&f=1&nofb=1&ipt=2d971f5724865bf4cda755882f622c5b26cd1425ecd78da0df953c9ef386ccd7&ipo=images"
              className="w-[70%] mx-auto"
              alt="Logo Proteccion Civil"
            />
          </div>
          <div className="md:w-8/12 lg:w-5/12 lg:ml-20 container bg-slate-300 px-4 py-6 rounded-xl ">
            <Card className="px-8 py-10">
              <h1 className="font-bold text-4xl text-center my-4">
                Bienvenido
              </h1>
              <p className="text-slate-100 mb-2">Ingresa tus credenciales.</p>
              <form onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-4">
                  <Input
                    {...register("username", { required: true })}
                    className="mx-auto"
                    color="purple"
                    variant="outlined"
                    label="Usuario"
                    size="lg"
                  />
                  {errors.username && (
                    <span className="text-red-400">Usuario requerido</span>
                  )}
                </div>
                <div className="mb-6">
                  <Input
                    {...register("password", { required: true })}
                    type="password"
                    variant="outlined"
                    color="purple"
                    label="Contraseña"
                    size="lg"
                  />
                  {errors.password && (
                    <span className="text-red-400">Contraseña requerida</span>
                  )}
                </div>
                <div className="mx-auto w-max">
                  <Button
                    onClick={handleSubmit(onSubmit)}
                    size="lg"
                    color="purple"
                  >
                    Iniciar Sesión
                  </Button>
                </div>
                <div className="mx-auto my-2">
                  <Fragment>
                    <Alert
                      size="sm"
                      className=""
                      color="red"
                      show={loginError}
                      dismissible={{ onClose: () => setLoginError(false) }}
                    >
                      Credenciales incorrectas
                    </Alert>
                  </Fragment>
                </div>
              </form>
            </Card>
          </div>
        </div>
      </div>
    </section>
  );
}
