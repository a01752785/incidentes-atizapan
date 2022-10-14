import { NextResponse } from "next/server";
import { authservice } from "./constants";
import ky from "ky";

export async function middleware(request) {
  const authToken = request.cookies.get("authCookie");
  if (!authToken) return NextResponse.redirect(new URL("/", request.url));
  try {
    await ky.get(authservice + "/verify", {
      headers: { "x-access-token": authToken },
    });
  } catch (err) {
    console.log("Aqui : ", err);
    return NextResponse.redirect(new URL("/", request.url));
  }
}

export const config = {
  matcher: [],
};
