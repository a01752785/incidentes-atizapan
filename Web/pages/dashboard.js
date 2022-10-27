import { Nav } from "../components/Nav";
import { useEffect } from "react";
import { useRouter } from "next/router";
import { authservice } from "../constants";
import {
  TableOne,
  TableTwo,
  TableTre,
  TableFor,
  TableFiv,
  TableSix,
} from "../components/Dashboard";
import axios from "axios";

export default function Dashboard() {
  const router = useRouter();
  const checkForUser = async () => {
    try {
      const token = localStorage.getItem("authToken");
      if (!token) {
        router.push("/");
        return;
      }
      const response = await axios.get(authservice + "/verify", {
        headers: { "x-access-token": token },
      });
      if (response.status != 200) {
        router.push("/");
        return;
      }
    } catch (error) {
      router.push("/");
      return;
    }
  };

  useEffect(() => {
    checkForUser();
  }, []);

  return (
    <section>
      <Nav />
      <h1 className="font-bold text-3xl lg:text-4xl pt-4 pl-6 lg:pl-8 lg:pt-4 ">
        Bienvenido al Dashboard
      </h1>
      <div className="inline-block w-full lg:w-auto">
        <TableOne />
      </div>
      <div className="inline-block w-full lg:w-auto">
        <TableTwo />
      </div>
      <div className="inline-block w-full lg:w-auto">
        <TableTre />
      </div>
      <div className="inline-block w-full lg:w-auto">
        <TableFor />
      </div>
      <div className="inline-block w-full lg:w-auto">
        <TableFiv />
      </div>
      <div className="inline-block w-full lg:w-auto">
        <TableSix />
      </div>
    </section>
  );
}
