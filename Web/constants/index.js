//Server API's Uris
export const authservice =
  process.env.AUTH_SERVICE || "https://auth.safeatizapan.lol"; //"http://localhost:5001"
export const databaseservice =
  process.env.DATABASE_SERVICE || "https://database.safeatizapan.lol"; //"http://localhost:5002";
export const notificationservice =
  process.env.NOTI_SERVICE || "https://notification.safeatizapan.lol"; //"http://localhost:5003";

//Array for incident displays

export const incidentIcons = {
  FIRE: (
    <svg
      className="h-8 w-8 text-red-500"
      fill="none"
      viewBox="0 0 24 24"
      stroke="currentColor"
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="2"
        d="M17.657 18.657A8 8 0 016.343 7.343S7 9 9 10c0-2 .5-5 2.986-7C14 5 16.09 5.777 17.656 7.343A7.975 7.975 0 0120 13a7.975 7.975 0 01-2.343 5.657z"
      />
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth="2"
        d="M9.879 16.121A3 3 0 1012.015 11L11 14H9c0 .768.293 1.536.879 2.121z"
      />
    </svg>
  ),
  FLOODING: (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      className="h-8 w-8"
    >
      <path
        fill="#064CFF"
        d="M24.009,22.632A3.97,3.97,0,0,1,18,22.618a3.947,3.947,0,0,1-6,0,3.947,3.947,0,0,1-6,0,3.97,3.97,0,0,1-6.009.014L1.5,21.316A2,2,0,0,0,5,20H7a2,2,0,0,0,4,0h2a2,2,0,0,0,4,0h2a2,2,0,0,0,3.5,1.316Z"
      />
      <g id="_01_align_center" data-name=" 01 align center">
        <path d="M12,11.682a5,5,0,0,0-5,5V18H9V16.682a3,3,0,0,1,6,0V18h2V16.682A5,5,0,0,0,12,11.682Z" />
        <path d="M2,18V11L12,2l10,9v7h2V10.111L13.338.515a2,2,0,0,0-2.676,0L0,10.111V18Z" />
      </g>
    </svg>
  ),
  ACCIDENT: (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      className="h-8 w-8"
    >
      <path
        fill="#f5c819"
        xmlns="http://www.w3.org/2000/svg"
        d="M23.956,14.354,23.52,9.262a2.984,2.984,0,0,0-1.891-2.535l-6.455-2.5a2.987,2.987,0,0,0-3.106.55L8.256,8.185A12.07,12.07,0,0,0,5.1,12.705L3.839,15.872,5.7,16.6,4.96,18.465l4.647,1.847.747-1.88,5.028,1.976-.731,1.839L19.3,24.092l.737-1.855,1.861.732,1.255-3.159A12.068,12.068,0,0,0,23.956,14.354ZM13.4,6.272a1,1,0,0,1,1.044-.18l6.455,2.5a.991.991,0,0,1,.628.843l.426,4.96L9.7,9.581Zm7.893,12.8-.519,1.307h0v0l-4.647-1.846-.008.02-9.681-3.8.517-1.3a10.036,10.036,0,0,1,1.28-2.287l1.54.6L9.4,12.7a1,1,0,1,0,1.859.738l.375-.944,6.89,2.7-.363.914a1,1,0,0,0,1.859.739l.366-.922,1.542.605A10,10,0,0,1,21.293,19.071ZM3.69,11H-.081l2.8-3.5L-.081,4H4V-.032L7.5,2.723,11-.081V3.053L6.923,6.693A14.064,14.064,0,0,0,3.69,11Z"
      />
    </svg>
  ),
  GAS_LEAK: (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      className="h-8 w-8"
      viewBox="0 0 24.000000 24.000000"
      preserveAspectRatio="xMidYMid meet"
    >
      <g
        transform="translate(0.000000,24.000000) scale(0.100000,-0.100000)"
        fill="#9cab07"
        stroke="none"
      >
        <path d="M87 222 c-23 -25 -21 -42 8 -75 l25 -28 25 28 c29 34 31 54 7 76 -24 22 -45 21 -65 -1z m69 -23 c3 -6 -3 -21 -15 -32 l-21 -21 -22 21 c-21 22 -21 23 -4 42 19 21 45 16 62 -10z" />
        <path d="M0 68 l0 -63 120 0 120 0 0 63 0 63 -47 -7 c-27 -3 -54 -11 -60 -16 -9 -7 -17 -7 -25 0 -7 5 -34 13 -60 16 l-48 7 0 -63z m30 -9 c0 -31 -4 -48 -10 -44 -13 8 -13 95 0 95 6 0 10 -23 10 -51z m67 39 c5 -7 8 -23 7 -35 -1 -16 4 -23 17 -23 17 0 18 3 7 21 -11 17 -10 22 7 35 11 8 29 13 40 12 16 -2 21 -12 23 -45 l3 -43 -80 0 -81 0 0 38 c0 21 3 42 7 45 11 11 40 8 50 -5z m133 -32 c0 -25 -4 -48 -10 -51 -6 -4 -10 13 -10 44 0 28 5 51 10 51 6 0 10 -20 10 -44z" />
      </g>
    </svg>
  ),
  WATER_LEAK: (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      className="h-8 w-8"
    >
      <path
        fill="#0693e3"
        d="M.332,17.978A4.185,4.185,0,0,0,6,17.774a4.334,4.334,0,0,0,6,0,4.334,4.334,0,0,0,6,0,4.185,4.185,0,0,0,5.668.2,1,1,0,1,0-1.335-1.489,2.2,2.2,0,0,1-3.39-.822,1.006,1.006,0,0,0-1.886,0,2.278,2.278,0,0,1-4.114,0,1.006,1.006,0,0,0-1.886,0,2.278,2.278,0,0,1-4.114,0,1.006,1.006,0,0,0-1.886,0,2.2,2.2,0,0,1-3.389.822A1,1,0,0,0,.332,17.978Zm22,3.511a2.2,2.2,0,0,1-3.39-.822,1.006,1.006,0,0,0-1.886,0,2.278,2.278,0,0,1-4.114,0,1.006,1.006,0,0,0-1.886,0,2.278,2.278,0,0,1-4.114,0,1.006,1.006,0,0,0-1.886,0,2.2,2.2,0,0,1-3.389.822A1,1,0,0,0,.332,22.978,4.185,4.185,0,0,0,6,22.774a4.334,4.334,0,0,0,6,0,4.334,4.334,0,0,0,6,0,4.185,4.185,0,0,0,5.668.2,1,1,0,1,0-1.335-1.489Zm-6.628-18.2-3-3a1,1,0,0,0-1.414,0l-3,3A1,1,0,0,0,9.705,4.708L11,3.415V11a1,1,0,0,0,2,0V3.415l1.3,1.293a1,1,0,0,0,1.413-1.415h0Z"
      />
    </svg>
  ),
  OTHER: (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      className="h-8 w-8"
    >
      <path d="M12,0A12,12,0,1,0,24,12,12.013,12.013,0,0,0,12,0Zm0,22A10,10,0,1,1,22,12,10.011,10.011,0,0,1,12,22Z" />
      <path d="M12.717,5.063A4,4,0,0,0,8,9a1,1,0,0,0,2,0,2,2,0,0,1,2.371-1.967,2.024,2.024,0,0,1,1.6,1.595,2,2,0,0,1-1,2.125A3.954,3.954,0,0,0,11,14.257V15a1,1,0,0,0,2,0v-.743a1.982,1.982,0,0,1,.93-1.752,4,4,0,0,0-1.213-7.442Z" />
      <rect x="11" y="17" width="2" height="2" rx="1" />
    </svg>
  ),
};
