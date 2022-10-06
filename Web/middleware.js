import {NextResponse} from "next/server";
import ky from "ky";

export async function middleware(request) {
  const authToken = request.cookies.get("authCookie");
  if (!authToken) return NextResponse.redirect(new URL("/",request.url));
  try {
    await ky.get("http://localhost:5001/verify",  {headers : {"x-access-token" : authToken}});
  }
  catch(err){
    return NextResponse.redirect(new URL("/",request.url));
  }
}

export const config = {
  matcher: ["/dashboard", "/controlpanel"],
};