// Next.js API route support: https://nextjs.org/docs/api-routes/introduction
import axios from "axios"

export default async function handler (req, res) {
  const token = req.cookies.authCookie;
  const respon = await axios.get("http://localhost:5003/test", {withCredentials: true, credentials: 'include'})
  console.log(respon.data)
  res.status(200).json({token})
}
