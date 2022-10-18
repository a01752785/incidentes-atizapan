import { Nav } from "../components/Nav";
import Image from "next/image";

export default function Dashboard() {
  return (
    <section>
      <Nav />
      <h1 className="font-bold text-3xl lg:text-4xl pt-4 pl-6 lg:pl-8 lg:pt-4 ">
        Bienvenido al Dashboard
      </h1>
      <section>
        <Image
          src="/images/dashboardProto.jpeg"
          height={800}
          width={1600}
          alt="logo"
          className="w-[100%] mx-4 my-8"
        />
      </section>
    </section>
  );
}
