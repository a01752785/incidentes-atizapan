import {
  Card,
  CardBody,
  Input,
  Textarea,
  Select,
  Option
} from "@material-tailwind/react";
import Geocoder from "./Geocoder";


export default function NewIncident(){
  return(
    <Card className="w-88 md:w-96 shadow-lg m-4">
        <CardBody className="text-center">
            <section className="text-left">
            <h2 className="font-bold text-2xl text-red-300">Nuevo Incidente</h2>
            </section>
            <section className="text-left pt-2">
                <span>Crear un nuevo incidente</span>
                <form>
                <section className="py-2">
                    <div className="mb-3">
                        <Geocoder />
                    </div>
                    <div className="mb-3">
                       <Textarea color="red" label="Descripcion" />
                    </div>
                    <div className="w-[30%]">
                        <Select color="red" label="Tipo">
                            <Option value="FIRE">Incendio</Option>
                            <Option value="FLOODING">Indundacion</Option>
                            <Option value="ACCIDENT">Accidente</Option>
                            <Option value="GAS_LEAK">Fuga de Gas</Option>
                            <Option value="WATER_LEAK">Fuga de Agua</Option>
                            <Option value="OTHER">Otro</Option>
                        </Select>
                    </div>
                </section>
                </form>
            </section>
        </CardBody>
    </Card>
);
}