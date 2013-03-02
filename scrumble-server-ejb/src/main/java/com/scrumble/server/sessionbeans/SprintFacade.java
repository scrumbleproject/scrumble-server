/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Processstatus;
import com.scrumble.server.entities.Project;
import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Task;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.entities.Userstorysprint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class SprintFacade extends AbstractFacade<Sprint> implements SprintFacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @EJB 
    private UserstorysprintFacadeLocal userstorysprintBean;
    
    @EJB 
    private UserstoryFacadeLocal userstoryBean;
    
    @EJB 
    private TaskFacadeLocal taskBean;
    
    @EJB 
    private TasksprintFacadeLocal tasksprintBean;
    
    @EJB 
    private ProcessstatusFacadeLocal processStatusBean;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintFacade() {
        super(Sprint.class);
    }
 
    public List<Userstory> findAllSprintUserstories(Integer idSprint) throws Exception{
        List<Userstory> results;
        try {
            results = userstorysprintBean.findUserstoriesForSprint(idSprint);
        }
        catch(Exception e){
            throw e;
        }
        return results;
    }
    
    public List<Sprint> findAllProjectSprints(Integer idProject) throws Exception{
        TypedQuery<Sprint> query = getEntityManager().createNamedQuery("Sprint.findByIdProject", Sprint.class);
        return query.setParameter("idProject", this.em.find(Project.class, idProject)).getResultList();
    }
    
    public void add_updateSprintToProject(Sprint sprint, Integer idProject){
        sprint.setIdProject(this.em.find(Project.class, idProject));
        this.edit(sprint);
    }
    
    public List<Userstory> findAllNotSprintUserstories(Integer idSprint) throws Exception{
        
        Sprint sprint = em.find(Sprint.class, idSprint);
        List<Userstory> userstories = userstoryBean.findAllProjectUserstories(sprint.getIdProject().getIdProject());

        //Get the list of userstories which mustn't be displayed
        List<Sprint> sprints = this.findAllProjectSprints(sprint.getIdProject().getIdProject());
        
        List<Userstory> userstorysprints;
        ArrayList<Userstory> result=new ArrayList<Userstory>();
        int j=0;
        while(j<sprints.size())
        {
            userstorysprints = userstorysprintBean.findUserstoriesForSprint(sprints.get(j).getIdSprint());
            
            int i=0;
            while(i<userstorysprints.size())
            {
                result.add(em.find(Userstory.class, userstorysprints.get(i).getIdUserstory()));
                i++;
            }
            j++;
        }
        
        userstories.removeAll((Collection) result);
        
        return userstories;
    }
    
    public void addListUserstoriesToSprint(List<Integer> array, Integer idSprint) throws Exception
    {
        List<Userstory> list = null;
        Userstory us=null;
        
        try 
        {
            list = userstorysprintBean.findUserstoriesForSprint(idSprint);
            int i = 0;
        
            while(i<array.size())
            {
                //System.out.println("array["+i+"]=>"+array.get(i));
                us = this.em.find(Userstory.class, array.get(i));
                
                //If this us isn't in the database, we will create it
                if(!list.contains(us))
                {
                    Userstorysprint userstorysprint = new Userstorysprint(idSprint, array.get(i));
                    userstorysprint.setSprint(this.find(idSprint));
                    userstorysprint.setUserstory(userstoryBean.find(array.get(i)));
                    userstorysprintBean.create(userstorysprint);
                    //System.out.println("userstory "+array.get(i)+" ajoutee dans le sprint "+idSprint);
                }
                else
                {
                    list.remove(us);
                    //System.out.println("userstory "+array.get(i)+" ne doit pas etre supprimee du sprint "+idSprint);
                }
                
                i++;
            }
            
            //Remove object which are not in the new list anymore
            i=0;
            while(i<list.size())
            {
                userstorysprintBean.remove(new Userstorysprint(idSprint, list.get(i).getIdUserstory()));
                //System.out.println("userstory "+list.get(i).getIdUserstory()+" supprimee du sprint "+idSprint);
                i++;
            }
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public void addUserstoriesToSprint(List<String> array, Integer idSprint) throws Exception
    {
        List<Userstory> list = null;
        try 
        {
            list =this.findAllSprintUserstories(idSprint);
            int i = 0;
        
            while(i<array.size())
            {
                
                i++;
            }
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public String findSprintBurndownChartInformations(Integer idSprint) throws Exception{
        List<Userstory> listUserstories = this.userstorysprintBean.findUserstoriesForSprint(idSprint);
        List<Task> listTasks = null;
        Sprint sprint = em.find(Sprint.class, idSprint);
        int i=0, j=0, est=0, nbdays=1;
        
        if(sprint.getDateStart()!= null && sprint.getDateEnd()!= null && !sprint.getDateStart().equals(null) && !sprint.getDateEnd().equals(null))
        {
            Date dateStart = sprint.getDateStart();
            Date dateEnd = sprint.getDateEnd();

            long dat=dateStart.getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String listDates = "{\"listDates\": [";
            listDates +="\""+dateFormat.format(new Date(dat))+"\"";
            
            //Get the list of days between the beginning and the end of the sprint
            while(dat<dateEnd.getTime())
            {
                dat+=1000*60*60*24;
                if(dat<dateEnd.getTime())
                {
                    listDates +=", \""+dateFormat.format(new Date(dat))+"\"";
                    nbdays++;
                }
            }
            listDates +="]";

            //System.out.println(nbdays);

            //Count the sum of estimation of all tasks of this sprint
            while(i<listUserstories.size())
            {
                listTasks = this.taskBean.findAllTaskUserstories(listUserstories.get(i).getIdUserstory());

                j=0;
                while(j<listTasks.size())
                {
                    //System.out.println("Task:"+listTasks.get(j).getIdTask()+";Estimation:"+listTasks.get(j).getEstimation());
                    est+=listTasks.get(j).getEstimation();
                    j++;
                }

                i++;
            }
            //System.out.println("Somme estimation du sprint:"+est);

            //Get data for the ideal chart
            i=1;
            float l ;
            String idealChart = ", \"idealChart\": [";
            while(i<=nbdays)
            {
                l = (float) est - (float) i*est/nbdays;

                if(i==1)
                    idealChart +=""+l+"";
                else
                    idealChart +=", "+l+"";
                //est-i*(est/nbdays)
                i++;
            }
            idealChart +="]";
            //System.out.println(listDates+idealChart);
            return listDates+idealChart+", \"actualChart\": []}";
        }
        
        return "{\"listDates\": [], \"idealChart\": [], \"actualChart\": []}";
    }
    
    public Processstatus getProcessStatusOfSprint(Integer idSprint) throws Exception{
        
        Sprint sprint = this.find(idSprint);
        if (sprint!=null){
            return sprint.getIdProcessStatus();
        }
        else { 
            throw new Exception("Sprint with id["+idSprint+"] not found !");
        }
    }
    
    public void updateProcessStatusOfSprint(Integer idSprint, String codeStatus) {
        Sprint sprint = this.find(idSprint);
        if (!codeStatus.equals(sprint.getIdProcessStatus().getCodeStatus())){
        
            TypedQuery<Processstatus> query = getEntityManager().createNamedQuery("Processstatus.findByCodeStatus", Processstatus.class);
            List<Processstatus> processStatus = query.setParameter("codeStatus", codeStatus).getResultList();
            if (processStatus.size()>0){ //if related processStatus object found
                sprint.setIdProcessStatus(processStatus.get(0)); //must be unique
            }
            
        }
    }
    
    @Override
    public void create(Sprint s){
        
        TypedQuery<Processstatus> query = getEntityManager().createNamedQuery("Processstatus.findByCodeStatus", Processstatus.class);
        List<Processstatus> processStatus = query.setParameter("codeStatus", "tod").getResultList();
        if (processStatus.size()>0){ //if related processStatus object found
            s.setIdProcessStatus(processStatus.get(0)); //must be unique
        }
        super.create(s);
        
    }
    
}
