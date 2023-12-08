# Prueba Técnica - Api Rick And Morty Spring Boot y Java

---
## Tecnologias usadas:
![ alt text ](https://img.shields.io/badge/java-11-517A9E?style=for-the-badge&logo=oracle)
![ alt text ](https://img.shields.io/badge/spring_boot-2.7.13-6DB33F?style=for-the-badge&logo=springboot)
![ alt text ](https://img.shields.io/badge/apache_maven-4.0.0-C71A36?style=for-the-badge&logo=apachemaven)
![ alt text ](https://img.shields.io/badge/swagger-2.9.2-85EA2D?style=for-the-badge&logo=swagger)
![ alt text ](https://img.shields.io/badge/h2_database--004E81?style=for-the-badge&logo=)

### Requerimientos
1. Character API: Implementar un servicio que obtenga una lista de caracteres de la serie "Rick and Morty. 
Cada elemento de la lista debe tener la imagen, el nombre, el género y el estado del personaje (vivo o muerto).


2. Implemente un servicio de registro de caracteres, si el carácter que se guarda y se obtiene por su nombre 
es el mismo de la API "Rick and Morty", debe devolver un error que no se puede guardar. De lo contrario, 
debe registrarlo en una base de datos. 
    En un tercer caso, si el carácter que está intentando registrar no se encuentra en la API de rick y morty y 
    se encuentra en la base de datos, debe rechazar la solicitud.
    
    Registre en una tabla las llamadas que se realizan al api, tanto GET, POST (necesita la fecha, la información 
    de entrada y si se produjo un error, regístrelo)


3. Filtrado y Clasificación: Añadir opciones para filtrar y ordenar la lista de caracteres por estado, género, nombre, etc.


4. Paginación: Implementar paginación para la lista de caracteres

### Accesso a swagger
Para poder acceder al swagger-ui vamos a tener que ingresas al navegador y poner 
el siguiente url y podemos ver los servicios del ToDo: http://localhost:8080/swagger-ui.html#/

---

## Referencias

**Desarrollador**: Santiago Posso

**GitHub**: https://github.com/posinhodev

**LinkedIn**: https://www.linkedin.com/in/jose-santiago-posso-parra/