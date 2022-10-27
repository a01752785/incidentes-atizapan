import * as V from "victory";
import {
  VictoryTheme,
  VictoryPolarAxis,
  VictoryChart,
  VictoryBar,
} from "victory";
import { Card, CardBody } from "@material-tailwind/react";

const data = [
  { quarter: 1, earnings: 123 },
  { quarter: 2, earnings: 87 },
  { quarter: 3, earnings: 113 },
  { quarter: 4, earnings: 69 },
];

export default function TableFiv() {
  return (
    <Card className="w-88 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-blue-600 mb-4">
            Radio Promedio por Incidente
          </h2>
        </section>
        <section className="float-left">
          <VictoryChart polar theme={VictoryTheme.material}>
            {[
              "incendio",
              "accidente",
              "indundación",
              "fuga de agua",
              "otro",
            ].map((d, i) => {
              return (
                <VictoryPolarAxis
                  dependentAxis
                  key={i}
                  label={d}
                  labelPlacement="perpendicular"
                  style={{ tickLabels: { fill: "none" } }}
                  axisValue={d}
                />
              );
            })}
            <VictoryPolarAxis
              dependentAxis
              width={400}
              height={400}
              domain={[0, 10]}
              axisAngle={45}
              theme={VictoryTheme.material}
              standalone={false}
            />
            <VictoryBar
              style={{ data: { fill: "tomato", width: 25 } }}
              data={[
                { x: "incendio", y: 10 },
                { x: "accidente", y: 25 },
                { x: "indundación", y: 40 },
                { x: "fuga de agua", y: 50 },
                { x: "otro", y: 50 },
              ]}
            />
          </VictoryChart>
        </section>
      </CardBody>
    </Card>
  );
}
