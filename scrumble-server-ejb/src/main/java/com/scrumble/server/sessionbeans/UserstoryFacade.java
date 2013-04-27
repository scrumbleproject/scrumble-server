/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Project;
import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Task;
import com.scrumble.server.entities.Userstory;
import java.util.ArrayList;
import java.util.Collection;
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
public class UserstoryFacade extends AbstractFacade<Userstory> implements UserstoryFacadeLocal
{
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @EJB 
    private UserstorysprintFacadeLocal userstorySprintBean;
    @EJB
    private TaskFacadeLocal taskBean;
    
    
    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UserstoryFacade()
    {
        super(Userstory.class);
    }
    
    public List<Userstory> quickSearch(String pattern)
    {
        List<Userstory> results;
        
        //run named queries
        TypedQuery<Userstory> queryExact = getEntityManager().createNamedQuery("Userstory.quickSearchExact", Userstory.class);
        TypedQuery<Userstory> querySimple = getEntityManager().createNamedQuery("Userstory.quickSearchSimple", Userstory.class);
        
        //concatenate results
        results = queryExact.setParameter("pattern", pattern).getResultList();
        results.addAll(querySimple.setParameter("pattern", "%"+pattern+"%").getResultList());
        
        //supprimer les doublons
        Set set = new HashSet();
        set.addAll(results);
        
        return new ArrayList<Userstory>(set);
    }
    
    public void updateImportance(Integer id, int position)
    {
        //System.out.println("updateImportance");
        //get all objects (ordered by importance DESC)
        List<Userstory> list = this.findAllOrderByImportance();
        
        //get index in the result list of suitable userstory object
        int index = -1;
        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).getIdUserstory()==id)
            {
                index = i;
            }
        }
        
        //System.out.println("index = "+index);
        
        //insert this object at position
        insertObjAtDestination(list, index, position);
    }
    
    private void insertObjAtDestination(List<Userstory> list, int currentIndex, int destinationIndex)
    {
        //System.out.println("insertObjAtDestination");
        
        //debug
        //System.out.println("debug afficher list");
        for (Userstory us : list)
        {
            //System.out.println("us.importance = "+us.getImportance());
        }
        
        Userstory userstory = list.get(currentIndex);
        
        if(currentIndex<destinationIndex)
        {
            //System.out.println("decrement priority");
            if(destinationIndex >= list.size())
            {
                System.out.println("blindage");
                return; //blindage
            }
            else if(destinationIndex == list.size()-1)
            { //dernier élément
                //System.out.println("dernier element");
                int importanceMin = list.get(destinationIndex).getImportance();
                //System.out.println("importanceMin = "+importanceMin);
                if (importanceMin <= 1){
                    incrementImportance(list.get(destinationIndex));
                }
                userstory.setImportance(importanceMin-1);
            
            }
            else
            {
                int importanceDestination = list.get(destinationIndex).getImportance();
                //System.out.println("importanceDestination = "+importanceDestination);
                int importanceJusteEnDessous = list.get(destinationIndex+1).getImportance();
                //System.out.println("importanceJusteEnDessous = "+importanceJusteEnDessous);
                //Si place libre :
                if(importanceDestination - importanceJusteEnDessous > 1)
                {
                    //System.out.println("place libre");
                    userstory.setImportance((int)((importanceDestination + importanceJusteEnDessous)/2));
                }
                else//place pas libre
                {   //System.out.println("place pas libre");
                    incrementImportance(list.get(destinationIndex));
                    userstory.setImportance(list.get(destinationIndex).getImportance()-1);
                    //System.out.println("new importance = "+String.valueOf(list.get(destinationIndex).getImportance()-1));
                }
            }
        }
        else if(currentIndex>destinationIndex)
        {
            //System.out.println("increment priority");
            if(destinationIndex < 0)
            {
                //System.out.println("blindage");
                return; //blindage
            }
            else if(destinationIndex == 0)
            {   //premier élément
                //System.out.println("premier element");
                int importanceMax = list.get(destinationIndex).getImportance();
                //System.out.println("importanceMax = "+importanceMax);
                userstory.setImportance(importanceMax+10);
            }
            else 
            {
                int importanceDestination = list.get(destinationIndex).getImportance();
                //System.out.println("importanceDestination = "+importanceDestination);
                int importanceJusteAuDessus = list.get(destinationIndex-1).getImportance();
                //System.out.println("importanceJusteAuDessus = "+importanceJusteAuDessus);
                //Si place libre :
                if(importanceJusteAuDessus - importanceDestination > 1)
                {
                    //System.out.println("place libre");
                    userstory.setImportance((int)((importanceDestination + importanceJusteAuDessus)/2));
                }
                else//place pas libre
                {   //System.out.println("place pas libre");
                    decrementImportance(list.get(destinationIndex));
                    userstory.setImportance(list.get(destinationIndex).getImportance()+1);
                }
            }
        }
        
        //save
        this.edit(userstory);
        //System.out.println("save");
    }
    
    
   private void decrementImportance(Userstory userstory)
   {
        //get the importance we want to set to the userstory
        int decrementedImportance = userstory.getImportance()-1;
        //System.out.println("decrementedImportance = "+decrementedImportance);
        //check that there is not already another userstory with this importance
        TypedQuery<Userstory> query = getEntityManager().createNamedQuery("Userstory.findByImportance", Userstory.class);
        List<Userstory> results = query.setParameter("importance", decrementedImportance).getResultList();
        if(results.size()>0)
        {
            //if userstories with the same importance are found, decremente their importance too
            for(Userstory us : results)
            {
                decrementImportance(us);
            }
        }
        
        //set the new importance
        userstory.setImportance(decrementedImportance);
        this.edit(userstory);
   }
   
   private void incrementImportance(Userstory userstory)
   {
        //get the importance we want to set to the userstory
        int incrementedImportance = userstory.getImportance()+1;
        //System.out.println("incrementedImportance = "+incrementedImportance);
        //check that there is not already another userstory with this importance
        TypedQuery<Userstory> query = getEntityManager().createNamedQuery("Userstory.findByImportance", Userstory.class);
        List<Userstory> results = query.setParameter("importance", incrementedImportance).getResultList();
        if (results.size()>0)
        {
            //if userstories with the same importance are found, incremente their importance too
            for (Userstory us : results)
            {
                incrementImportance(us);
            }
        }
        
        //set the new importance
        userstory.setImportance(incrementedImportance);
        this.edit(userstory);
   }

    public List<Userstory> findAllOrderByImportance()
    {
        return this.em.createNamedQuery("Userstory.findAllOrderByImportance").getResultList();
    }
    
    public List<Userstory> findAllProjectUserstories(Integer idProject) throws Exception
    {
        TypedQuery<Userstory> query = getEntityManager().createNamedQuery("Userstory.findByIdProject", Userstory.class);
        return query.setParameter("idProject", this.em.find(Project.class, idProject)).getResultList();
    }
    
    public void add_updateUserstoryToProject(Userstory userstory, Integer idProject)
    {
        userstory.setIdProject(this.em.find(Project.class, idProject));
        this.edit(userstory);
    }
    
    
    public void updateUserstoryTaskCollection(Userstory userstory)
    {
        TypedQuery<Task> query = getEntityManager().createNamedQuery("Task.findByIdUserstory", Task.class);
        userstory.setTaskCollection(query.setParameter("idUserstory", userstory).getResultList());
    }
    
    
    public boolean isUserstoryEditable(Integer idUserstory)
    {
        //check if userstory is assigned to a sprint
        List<Sprint> sprints = userstorySprintBean.findSprintAssignationForUserstory(idUserstory);
        for(Sprint s : sprints)
        {//if yes check sprint status
            if(s.getIdProcessStatus().getCodeStatus().equals("inp") ||
                s.getIdProcessStatus().getCodeStatus().equals("don"))
            {
                return false;
            }
        }
        return true;
    }
    
    
    public void updateEstimation(Integer idUserstory) throws Exception
    {
        List<Task> listTasks = this.taskBean.findAllTaskUserstories(idUserstory);
        int i=0,est=0;

        while(i < listTasks.size())
        {
            est += listTasks.get(i).getEstimation();
            i++;
        }
        Userstory us=em.find(Userstory.class, idUserstory);
        us.setEstimation(est);
        this.edit(us);
    }
}
