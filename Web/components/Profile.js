import {
  Menu,
  MenuHandler,
  MenuList,
  MenuItem,
  Button,
} from "@material-tailwind/react";
import { useRouter } from "next/router";

export const Profile = () => {
  const onCloseSesion = () => {
    localStorage.removeItem("authToken");
    router.push("/");
  };
  const router = useRouter();

  return (
    <Menu>
      <MenuHandler>
        <Button variant="gradient" color="indigo">
          <svg
            className="h-4 w-4 text-white inline-block mr-2"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z"
            />
          </svg>
          Perfil
        </Button>
      </MenuHandler>
      <MenuList>
        <MenuItem>
          <button onClick={onCloseSesion}>Cerrar Sesión</button>
        </MenuItem>
      </MenuList>
    </Menu>
  );
};
