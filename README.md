# SuggestionService

REST API que provee sugerencias para largas ciudades de acuerdo a ciertos parámetros.

## Consumo del servicio

Este es un ejemplo de cómo consumir el servicio:

https://idyllic-nova-346502.wl.r.appspot.com/suggestions?q=London&latitude=42.86509&longitude=-71.3739

## Consideraciones

- El parámetro de búsqueda necesario para obtener sugerencias es el término a buscar (q), por lo que la latitud (latitude) y longitud (longitude) son opcionales.
