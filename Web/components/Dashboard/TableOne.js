import * as V from "victory";
import { VictoryBar, VictoryChart, VictoryAxis, VictoryTheme } from "victory";
import { Card, CardBody } from "@material-tailwind/react";

const data = [
  { quarter: 1, earnings: 123 },
  { quarter: 2, earnings: 87 },
  { quarter: 3, earnings: 113 },
  { quarter: 4, earnings: 69 },
  { quarter: 5, earnings: 21 },
];

export default function TableOne() {
  return (
    <Card className="w-90 md:w-96 shadow-lg m-4">
      <CardBody className="text-center">
        <section className="text-left">
          <h2 className="font-bold text-2xl text-blue-600 mb-4">
            Incidentes por Mes
          </h2>
        </section>
        <VictoryChart
          domainPadding={20}
          theme={VictoryTheme.material}
          height={300}
          width={400}
        >
          <VictoryAxis
            tickValues={[1, 2, 3, 4, 5]}
            tickFormat={["Junio", "Julio", "Agosto", "Septiembre", "Octubre"]}
          />
          <VictoryAxis
            dependentAxis
            // tickFormat specifies how ticks should be displayed
            tickFormat={(x) => `${x}`}
          />
          <VictoryBar data={data} x="quarter" y="earnings" />
        </VictoryChart>
      </CardBody>
    </Card>
  );
}
