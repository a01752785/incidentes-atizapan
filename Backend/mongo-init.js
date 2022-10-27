db.createUser({
  user: "",
  pwd: "",
  roles: [
    {
      role: "readWrite",
      db: "alerta_atizapan",
    },
  ],
});
//DESTROY THIS FILE IN PRODUCTION
