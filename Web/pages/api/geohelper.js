// Next.js API route support: https://nextjs.org/docs/api-routes/introduction

export default async function handler(req, res) {
  try {
    const input = req.body;
    const endpoint = `https://api.mapbox.com/geocoding/v5/mapbox.places/${input}.json?access_token=${process.env.GEO_APIKEY}&autocomplete=true&country=MX&proximity=-99.267471,19.5562275&limit=3`;
    const response = await fetch(endpoint);
    const results = await response.json();
    res.status(200).json(results);
  } catch (error) {
    res.status(500).json({ message: "failed" });
  }
}
