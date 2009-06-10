/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.snoofing.ejb.session;

import com.reverseFlat.ejb.session.UserFacade;
import com.reverseFlat.ejb.persistence.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis Felipe Giraldo
 */
public class UserFacadeTest {

    public UserFacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class UserFacade.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        User user = null;
        UserFacade instance = new UserFacade();
        instance.create(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class UserFacade.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        User user = null;
        UserFacade instance = new UserFacade();
        instance.edit(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class UserFacade.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        User User = null;
        UserFacade instance = new UserFacade();
        instance.remove(User);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class UserFacade.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        Object id = null;
        UserFacade instance = new UserFacade();
        User expResult = null;
        User result = instance.find(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class UserFacade.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        UserFacade instance = new UserFacade();
        List<User> expResult = null;
        List<User> result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existNickname method, of class UserFacade.
     */
    @Test
    public void testExistNickname() {
        System.out.println("existNickname");
        String nickname = "";
        UserFacade instance = new UserFacade();
        Boolean expResult = null;
        Boolean result = instance.existNickname(nickname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserFacade.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        String nickname = "";
        String password = "";
        UserFacade instance = new UserFacade();
        User expResult = null;
        User result = instance.login(nickname, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}