package us.dit.service.services;

import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;

public interface KieUtilService {

	ProcessServicesClient getProcessServicesClient();

	UserTaskServicesClient getUserTaskServicesClient();

	QueryServicesClient getQueryServicesClient();

}