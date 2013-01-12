/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Member1;
import com.scrumble.server.entities.Processstatus;
import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Sprinttaskassignation;
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
    
    @EJB
    private SprinttaskassignationFacadeLocal assignationBean;
    
    @EJB
    private Member1FacadeLocal memberBean;
    
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
    
    public void addAssignedMemberForTask(Integer idSprint, Integer idTask, String login) throws Exception{
        
        Member1 member = memberBean.findByLogin(login);

        if (member != null) {

            TypedQuery<Sprinttaskassignation> query = getEntityManager().createNamedQuery("Sprinttaskassignation.findByAssignation", Sprinttaskassignation.class);
            query.setParameter("idTask", idTask);
            query.setParameter("idSprint", idSprint);
            query.setParameter("idMember", member.getIdMember());
            List<Sprinttaskassignation> assignations = query.getResultList();
            if (assignations != null && assignations.size()<1){ //if related assignation object found
                Sprinttaskassignation assignation = new Sprinttaskassignation(idTask, idSprint, member.getIdMember());
                //assignationBean.create(assignation);
                member.getSprinttaskassignationCollection().add(assignation); //must be unique
            }

        }
        
    }
    
    public List<Member1> getAssignedMemberForTask(Integer idSprint, Integer idTask) throws Exception {
        
        TypedQuery<Sprinttaskassignation> query = getEntityManager().createNamedQuery("Sprinttaskassignation.findByIdSprintAndIdTask", Sprinttaskassignation.class);
        query.setParameter("idTask", idTask);
        query.setParameter("idSprint", idSprint);
        List<Sprinttaskassignation> assignations = query.getResultList();
        if (assignations!=null){ //if related assignation object found
            List<Member1> assignedMembers = new ArrayList<Member1>();
            for (Sprinttaskassignation assign : assignations){
                if (!assignedMembers.contains(assign.getMember1()) ) {
                    assignedMembers.add(assign.getMember1());
                }
            }
            return assignedMembers;            
        }
        
        return null;
    }
    
}