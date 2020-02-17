package com.lits.tests;

import com.lits.ws.IntegrationServicesFacade;
import com.lits.ws.models.BookModel;
import com.lits.ws.models.UserModel;
import org.testng.annotations.Test;

public class LoginTest {

    private static final String EMAIL = "";

    private IntegrationServicesFacade integrationServicesFacade = new IntegrationServicesFacade();


    @Test
    public void testGetBooks() {

        for (BookModel book : integrationServicesFacade.getBooks()) {
            System.out.println(book);
        }
    }

    @Test
    public void testGetBookByIsbn() {

        BookModel book = integrationServicesFacade.getBookForIsbn("9781444707861");

        System.out.println(book);
    }

    @Test
    public void testUserForEmail() {

        final UserModel userData = integrationServicesFacade.getUserForEmail("drolgmaks+402@gmail.com");

        System.out.println();
    }


}
