import type { NextPage } from 'next'
import Head from 'next/head'
import Image from 'next/image'

const Home: NextPage = () => {
  return (
    <div className="container">
      <Head>
        <title>Alerta Atizapan</title>
        <meta name="description" content="Aplicación para prevención de desastres"/>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main>
        <h1 className="text-black font-bold text-4xl text-center">
          Bienvenido a  <a href="https://nextjs.org" className="text-blue-500">Alerta Atizapan</a>
        </h1>
      </main>
    </div>
  )
}

export default Home
