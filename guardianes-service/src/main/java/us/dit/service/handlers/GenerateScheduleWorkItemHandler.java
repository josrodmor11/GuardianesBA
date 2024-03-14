package us.dit.service.handlers;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("GenerarPlanificacion")
public class GenerateScheduleWorkItemHandler implements WorkItemHandler {

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager workItemManager) {
        String idCalendarioFestivos = (String) workItem.getParameter("IdCalendarioFestivos");
        //Representa los datos de base de datos que se deberían coger, los médicos,
        // elecciones de las preferencias de los médicos, etc...
        String dataBase = (String) workItem.getParameter("DataBase");

        //TODO integrar el scheduler de miguel angel aqui
        String idPlanificacionProvisional = "";
        Map<String, Object> results = new HashMap<>();
        results.put("IdPlanificacionProvisional", idPlanificacionProvisional);
        workItemManager.completeWorkItem(workItem.getId(), results);
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager workItemManager) {

    }
}
