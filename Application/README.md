# Importante, Sacar API KEY de Google Cloud
Si el repositorio se clona y se intenta compilar la aplicación inmediatamente la compilación resultará en un error ya que la API KEY que se usa para la función de mapa no se encuentra como tal en el repositorio por tema de seguridad. Por eso es muy importante que al momento de clonar el repositorio se registre con una cuenta en Google Cloud. Una vez que cuente con una cuenta de Google Cloud haga acceso a la pestaña de Productos y seleccione SDK de Google para poder darse de alta y solicitar una API KEY. Una vez que cuente con una API KEY entre al archivo de local.properties y agregue una linea que contega lo siguiente:

MAPS_API_KEY = Agregue aquí la API KEY que solicito de Google Cloud



