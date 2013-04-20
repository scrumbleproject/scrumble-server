/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Member1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface Member1FacadeLocal {

    /** netbeans generated methods **/
    
    void create(Member1 member1);

    void edit(Member1 member1);

    void remove(Member1 member1);

    Member1 find(Object id);

    List<Member1> findAll();

    List<Member1> findRange(int[] range);

    int count();
    
    /** custom methods **/
    
    /**
     * QUICK SEARCH : search members within all Member1 object attributes
     * @param pattern a String to compare with any attributes content
     * @return a list with all matching Member1 object 
     */
    public List<Member1> quickSearch(String pattern);

    
    
    public boolean checkLoginAndPassword(String login, String password) throws Exception;
    
    /**
     * Add a role attached to a member
     * @param member the member which will be added or updated
     * @param idRole the id of a specified Role object
     */
    public void addRoleToMember(Member1 member, Integer idRole);
    
    /**
     * Update a role attached to a member
     * @param member the member which will be added or updated
     * @param idRole the id of a specified Role object
     */
    public void updateRoleToMember(Member1 member, Integer idRole);
    
    /**
     * Retrieve a member from his login
     * @param login login of member
     * @return the related member.
     */
    public Member1 findByLogin(String login);
    
    /**
     * Retrieve display name for a specified user login
     * @param login the login of the user
     * @return the display name as string.
     */
    public String getDisplayNameForLogin(String login);
    
    /**
     * Retrieve IdMember for a specified user login
     * @param login the login of the user
     * @return the idmember as string.
     */
    public String getIDMemberFromLogin(String login);
    
}