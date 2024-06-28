/**
*  This file is part of GuardianesBA - Business Application for processes managing healthcare tasks planning and supervision.
*  Copyright (C) 2024  Universidad de Sevilla/Departamento de Ingeniería Telemática
*
*  GuardianesBA is free software: you can redistribute it and/or
*  modify it under the terms of the GNU General Public License as published
*  by the Free Software Foundation, either version 3 of the License, or (at
*  your option) any later version.
*
*  GuardianesBA is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
*  Public License for more details.
*
*  You should have received a copy of the GNU General Public License along
*  with GuardianesBA. If not, see <https://www.gnu.org/licenses/>.
**/
package us.dit.service.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.*;
import org.kie.server.client.admin.UserTaskAdminServicesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Esta clase debe contener los servicios que proporcionan capacidades para
 * interaccionar con el kie server Debe ser el único responsable de estos
 * aspectos, liberando a los demás de esta necesidad Debería ser un Bean, un
 * componente spring, para poder inyectarlo en todos aquellos que lo necesiten,
 * especialmente servicios
 *
 * @author Isabel Román
 * @version 1.0
 * @date Julio 2024
 */

@Service
public class KieUtil implements KieUtilService {
    private static final Logger logger = LogManager.getLogger();
    @Value("${kieserver.location}")
    private String URL;
    @Value("${org.kie.server.user}")
    private String USERNAME;
    @Value("${org.kie.server.pwd}")
    private String PASSWORD;
    @Value("${org.kie.server.timeout}")
    private long TIMEOUT;
    private KieServicesConfiguration config;

    public KieUtil() {
        logger.info("Creando el kieutil con los valores por defecto user " + USERNAME + "pwd: " + PASSWORD);
    }

    @Override
    public ProcessServicesClient getProcessServicesClient() {
        logger.info("getprocessservicesclient");
        KieServicesClient kieServicesClient = getKieServicesClient();
        ProcessServicesClient processServicesClient = kieServicesClient.getServicesClient(ProcessServicesClient.class);
        logger.info("salgo de getprocessservicesclient");
        return processServicesClient;
    }

    @Override
    public UserTaskServicesClient getUserTaskServicesClient() {
        logger.info("ENTRANDO EN USERTASKSERVICE");
        KieServicesClient kieServicesClient = getKieServicesClient();

        UserTaskServicesClient userClient = kieServicesClient.getServicesClient(UserTaskServicesClient.class);
        logger.info("Se ha obtenido el cliente para la gestión de tareas");
        return userClient;
    }

    @Override
    public QueryServicesClient getQueryServicesClient() {
        KieServicesClient kieServicesClient = getKieServicesClient();
        QueryServicesClient queryClient = kieServicesClient.getServicesClient(QueryServicesClient.class);

        return queryClient;
    }

    @Override
    public UserTaskAdminServicesClient getUserTaskAdminServicesClient() {
        KieServicesClient kieServicesClient = getKieServicesClient();
        UserTaskAdminServicesClient client = kieServicesClient.getServicesClient(UserTaskAdminServicesClient.class);

        return client;
    }

    @Override
    public UIServicesClient getUIServicesClient() {
        KieServicesClient kieServicesClient = getKieServicesClient();
        UIServicesClient client = kieServicesClient.getServicesClient(UIServicesClient.class);

        return client;
    }

    private KieServicesClient getKieServicesClient() {
        logger.info("entro en getkieservicesclient con url " + URL);
        config = KieServicesFactory.newRestConfiguration(URL, USERNAME, PASSWORD, TIMEOUT);
        logger.info("salgo de newrestconfigurarion");
        config.setMarshallingFormat(MarshallingFormat.JSON);

        return KieServicesFactory.newKieServicesClient(config);
    }

}
