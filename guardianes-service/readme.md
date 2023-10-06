# Servicio Guardianes
=============================
Guardianes es la aplicación de negocio del proyecto Guardianes
## Tecnologías
Utiliza Spring boot 2 y jBPM (kie server 7.74.1.Final
## Generación
Se ha generado con el arquetipo mvn con las opciones:
* _mvn archetype:generate -B "-DarchetypeGroupId=org.kie" "-DarchetypeArtifactId=kie-service-spring-boot-archetype" "-DarchetypeVersion=7.74.1.Final" "-DgroupId=us.dit" "-DartifactId=guardianes-service" "-Dversion=1.0-SNAPSHOT" "-Dpackage=us.dit.service" "-DappType=bpm"_
Pero posteriormente se han hecho los siguientes cambios a la configuración por defecto:
1. En pom.xml se ha añadido, para que utilice Spring boot 2.6.15
2. Se ha cambiado la configuración de seguridad para que sea conforme a los nuevos mecanismos de Spring
3. Se ha cambiado el banner por defecto. Se ha usado la web: https://manytools.org/hacker-tools/ascii-banner/
4. Se ha añadido el fichero guardianes-services.xml para incluir la configuración del servidor kie