/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Processstatus;
import com.scrumble.server.entities.Task;
import com.scrumble.server.entities.Userstory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author cyril
 */
@Stateless
@LocalBean
public class TaskFacade extends AbstractFacade<Task> implements TaskFacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }
  
    @Override
     public List<Task> quickSearch(String pattern){
        List<Task> results;
        
        //run named queries
        TypedQuery<Task> queryExact = getEntityManager().createNamedQuery("Task.quickSearchExact", Task.class);
        TypedQuery<Task> querySimple = getEntityManager().createNamedQuery("Task.quickSearchSimple", Task.class);
        
        //concatenate results
        results = queryExact.setParameter("pattern", pattern).getResultList();
        results.addAll(querySimple.setParameter("pattern", "%"+pattern+"%").getResultList());
        
        //supprimer les doublons
        Set set = new HashSet();
        set.addAll(results);
        
        return new ArrayList<Task>(set);
    }
    
    public List<Task> findAllTaskUserstories(Integer idUserstory)
    {
        TypedQuery<Task> query = getEntityManager().createNamedQuery("Task.findByIdUserstory", Task.class);
        return query.setParameter("idUserstory", this.em.find(Userstory.class, idUserstory)).getResultList();
    }
    
    public void updateProcessStatusOfTask(Integer idTask, String codeStatus){
        
        Task task = this.em.find(Task.class, idTask);
        if (!codeStatus.equals(task.getIdProcessStatus().getCodeStatus())){
        
            TypedQuery<Processstatus> query = getEntityManager().createNamedQuery("Processstatus.findByCodeStatus", Processstatus.class);
            List<Processstatus> processStatus = query.setParameter("codeStatus", codeStatus).getResultList();
            if (processStatus.size()>0){ //if related processStatus object found
                task.setIdProcessStatus(processStatus.get(0)); //must be unique
            }
            
        }
        
    }
    
    public Task useDefaultProcessStatusIfNeededForTask(Task task){
        
        if (task.getIdProcessStatus()==null){
            //if no processStatus for this task, we use "To Do" process Status by default
            TypedQuery<Processstatus> query = getEntityManager().createNamedQuery("Processstatus.findByCodeStatus", Processstatus.class);
            List<Processstatus> processStatus = query.setParameter("codeStatus", "tod").getResultList();
            if (processStatus.size()>0){ //if related processStatus object found
                task.setIdProcessStatus(processStatus.get(0)); //must be unique
            }
        }
        
        return task;
    }
    
}