import * as V from "victory";
import {
  VictoryBar,
  VictoryChart,
  VictoryAxis,
  VictoryTheme,
  VictoryPolarAxis,
  VictoryPie,
} from "victory";
import { Card, CardBody } from "@material-tailwind/react";

const data = [
  { quarter: 1, earnings: 123 },
  { quarter: 2, earnings: 87 },
  { quarter: 3, earnings: 113 },
  { quarter: 4, earnings: 69 },
];

export default function TableTwo() {
  return (
    <Card className="w-88 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-blue-600 mb-4">
            Tipos de Incidentes
          </h2>
        </section>
        <VictoryPie
          data={[
            { x: "Accidente", y: 55 },
            { x: "Indundación", y: 23 },
            { x: "Incendio", y: 25 },
            { x: "Fuga de Agua", y: 10 },
          ]}
          colorScale={"cool"}
          height={300}
        />
      </CardBody>
    </Card>
  );
}
