import * as V from "victory";
import {
  VictoryBar,
  VictoryChart,
  VictoryAxis,
  VictoryTheme,
  VictoryPolarAxis,
  VictoryPie,
  VictoryLine,
} from "victory";
import { Card, CardBody } from "@material-tailwind/react";

const data = [
  { quarter: 1, earnings: 123 },
  { quarter: 2, earnings: 87 },
  { quarter: 3, earnings: 113 },
  { quarter: 4, earnings: 69 },
];

export default function TableFor() {
  return (
    <Card className="w-88 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-blue-600 mb-4">
            Ubicaciónes con mas Incidentes
          </h2>
        </section>
        <section>
          <ul class="list-disc text-left pl-8">
            <li>Las Alamedas</li>
            <li>Arboledas</li>
            <li>El Jaral</li>
            <li>Villas de la Hacienda</li>
            <li>Zona Esmeralda</li>
          </ul>
        </section>
      </CardBody>
    </Card>
  );
}
