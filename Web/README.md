# Web Frontend
This is the web application for our solution, it´s developed in Next and styled with TailwindCSS

## Developing
First you will have to configure the API's URL in the _constants/index.js_ with the URL's of 
your environment.

Then you have to generate your own MapBox API key to use the Gecoding service thats implemented 
for the webpage, you can set you key in the _.env.local_ file.

```
# .env.local example

GEO_APIKEY=Eskereeeee <= Your API KEY
```

Run the development server:
```
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `pages/index.tsx`. The page auto-updates as you edit the file.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.