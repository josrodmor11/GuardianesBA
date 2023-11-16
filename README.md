# Proyecto GuardianesBA
Este proyecto es una aplicación de negocios jBPM con Spring Boot

Vamos a incluir un proceso para generar la planificación del mes
## Módulos
1. kjar: base de conocimiento kie, procesos, reglas de negocio, etc...
2. service: la aplicación
3. model: POJOs compartidos entre kjar y service
## Dependencias
**En la versión actual utilizamos:**
* _spring boot starter_ [_2.6.15_](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter/2.6.15)
* _kie server_: [_7.74.1.Final_](https://mvnrepository.com/artifact/org.kie/kie-server-spring-boot-starter/7.74.1.Final)
