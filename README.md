# SuggestionService

REST API que provee sugerencias para largas ciudades de acuerdo a ciertos parámetros.

## Consumo del API

Este es un ejemplo de cómo consumir el API:

curl -X GET "https://idyllic-nova-346502.wl.r.appspot.com/suggestions?q=London&latitude=42.86509&longitude=-71.3739"

## Consideraciones

- El parámetro de búsqueda necesario para obtener sugerencias es el término a buscar (q), por lo que la latitud (latitude) y longitud (longitude) son opcionales.

## Captura de pantalla

![image](https://user-images.githubusercontent.com/46584463/162331288-e686dfe3-2bc8-4b74-9cbb-70cf3156938b.png)

