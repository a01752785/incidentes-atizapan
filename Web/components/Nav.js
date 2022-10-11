import { useState, useEffect } from "react";
import Link from "next/link";
import {
  Navbar,
  MobileNav,
  Button,
  IconButton,
} from "@material-tailwind/react";

export const Nav = () => {
  const [openNav, setOpenNav] = useState(false);

  useEffect(() => {
    window.addEventListener(
      "resize",
      () => window.innerWidth >= 960 && setOpenNav(false)
    );
  }, []);

  const navList = (
    <ul className="mb-4 mt-2 flex flex-col gap-2 lg:mb-0 lg:mt-0 lg:flex-row lg:gap-6">
      <Link href="/controlpanel" className="flex items-center">
        <div className="hover:bg-deep-purple-50 hover:rounded-md px-2">
          <svg
            class="h-9 w-9 text-deep-purple-300 inline-block mx-auto my-auto pr-1 pb-1"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            fill="none"
            stroke-linecap="round"
            stroke-linejoin="round"
          >
            {" "}
            <path stroke="none" d="M0 0h24v24H0z" />{" "}
            <rect x="4" y="4" width="16" height="4" rx="1" />{" "}
            <rect x="4" y="12" width="6" height="8" rx="1" />{" "}
            <line x1="14" y1="12" x2="20" y2="12" />{" "}
            <line x1="14" y1="16" x2="20" y2="16" />{" "}
            <line x1="14" y1="20" x2="20" y2="20" />
          </svg>
          <span className="text-blue-gray-900">Panel de Control</span>
        </div>
      </Link>
      <Link href="/dashboard" className="flex items-center">
        <div className="hover:bg-deep-purple-50 hover:rounded-md px-2">
          <svg
            class="h-9 w-9 text-deep-purple-300 inline-block mx-auto my-auto pr-1 pb-1"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            stroke-width="2"
            stroke="currentColor"
            fill="none"
            stroke-linecap="round"
            stroke-linejoin="round"
          >
            {" "}
            <path stroke="none" d="M0 0h24v24H0z" />{" "}
            <circle cx="12" cy="13" r="2" />{" "}
            <line x1="13.45" y1="11.55" x2="15.5" y2="9.5" />{" "}
            <path d="M6.4 20a9 9 0 1 1 11.2 0Z" />
          </svg>
          <span className="text-blue-gray-900">Dashboard</span>
        </div>
      </Link>
    </ul>
  );

  return (
    <Navbar className="mx-auto w-full py-3 px-4 my-1 lg:px-8 lg:py-4 lg:my-2 shadow-md">
      <div className="container mx-auto flex items-center justify-between text-blue-gray-900">
        <h1 className="font-bold text-xl">Alerta Atizapan</h1>
        <div className="hidden lg:block">{navList}</div>
        <Button
          color="indigo"
          variant="gradient"
          size="md"
          className="hidden lg:inline-block"
        >
          <span>Cerrar Sesion</span>
        </Button>
        <IconButton
          variant="text"
          className="ml-auto h-6 w-6 text-inherit hover:bg-transparent focus:bg-transparent active:bg-transparent lg:hidden"
          ripple={false}
          onClick={() => setOpenNav(!openNav)}
        >
          {openNav ? (
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              className="h-6 w-6"
              viewBox="0 0 24 24"
              stroke="currentColor"
              strokeWidth={2}
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M6 18L18 6M6 6l12 12"
              />
            </svg>
          ) : (
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="h-6 w-6"
              fill="none"
              stroke="currentColor"
              strokeWidth={2}
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                d="M4 6h16M4 12h16M4 18h16"
              />
            </svg>
          )}
        </IconButton>
      </div>
      <MobileNav open={openNav}>
        {navList}
        <Button
          color="indigo"
          variant="gradient"
          size="md"
          fullWidth
          className="mb-2"
        >
          <span>Cerrar Sesion</span>
        </Button>
      </MobileNav>
    </Navbar>
  );
};
