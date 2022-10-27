import * as V from "victory";
import { VictoryChart, VictoryBar, VictoryGroup } from "victory";
import { Card, CardBody } from "@material-tailwind/react";

const data = [
  { quarter: 1, earnings: 123 },
  { quarter: 2, earnings: 87 },
  { quarter: 3, earnings: 113 },
  { quarter: 4, earnings: 69 },
];

export default function TableSix() {
  return (
    <Card className="w-88 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-blue-600 mb-4">
            Tipos de Incidente por Mes
          </h2>
        </section>
        <section>
          <VictoryChart>
            <VictoryGroup offset={20} colorScale={"qualitative"}>
              <VictoryBar
                data={[
                  { x: 1, y: 16 },
                  { x: 2, y: 10 },
                  { x: 3, y: 10 },
                ]}
              />
              <VictoryBar
                data={[
                  { x: 1, y: 8 },
                  { x: 2, y: 17 },
                  { x: 3, y: 7 },
                ]}
              />
              <VictoryBar
                data={[
                  { x: 1, y: 14 },
                  { x: 2, y: 12 },
                  { x: 3, y: 9 },
                ]}
              />
            </VictoryGroup>
          </VictoryChart>
        </section>
      </CardBody>
    </Card>
  );
}
