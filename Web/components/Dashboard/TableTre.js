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

export default function TableTre() {
  return (
    <Card className="w-88 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-blue-600 mb-4">
            Notificaciónes por Hora
          </h2>
        </section>
        <VictoryChart theme={VictoryTheme.material}>
          <VictoryLine
            style={{
              data: { stroke: "#c43a31" },
              parent: { border: "1px solid #ccc" },
            }}
            data={[
              { x: 1, y: 2 },
              { x: 2, y: 3 },
              { x: 3, y: 5 },
              { x: 4, y: 4 },
              { x: 5, y: 7 },
              { x: 6, y: 2 },
              { x: 7, y: 3 },
              { x: 8, y: 5 },
              { x: 9, y: 4 },
              { x: 10, y: 7 },
              { x: 11, y: 2 },
              { x: 12, y: 3 },
            ]}
          />
        </VictoryChart>
      </CardBody>
    </Card>
  );
}
