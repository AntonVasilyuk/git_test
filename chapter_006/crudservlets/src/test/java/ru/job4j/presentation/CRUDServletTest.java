//package ru.job4j.presentation;
//
//import org.junit.Assert;
//import org.junit.Test;
//import ru.job4j.persistent.DBStore;
//import ru.job4j.persistent.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.atLeast;
//
///**.
// * Task 9.2.1.
// * Class servlet for working with country
// *
// * @author Anton Vasilyuk
// * @version 1.0.
// */
//public class CRUDServletTest {
//
//    /**.
//     * Test adding user to database
//     * @throws ServletException may be exception
//     * @throws IOException may be exception
//     */
//    @Test
//    public void createUser() throws ServletException, IOException {
//        UserCreateServlet userCreate = new UserCreateServlet();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        when(request.getParameter("name")).thenReturn("test");
//        when(request.getParameter("login")).thenReturn("test");
//        when(request.getParameter("password")).thenReturn("test");
//        when(request.getParameter("email")).thenReturn("test@test.te");
//        when(request.getParameter("role")).thenReturn("user");
//
//        userCreate.doPost(request, response);
//        DBStore db = DBStore.getInstance();
//        User user = db.findById(db.getId());
//
//        Assert.assertThat(user.getLogin(), is("test"));
//
//        verify(request, atLeast(1)).getParameter("name");
//        verify(request, atLeast(1)).getParameter("login");
//        verify(request, atLeast(1)).getParameter("password");
//        verify(request, atLeast(1)).getParameter("email");
//        verify(request, atLeast(1)).getParameter("role");
//    }
//
//    /**.
//     * Test updating user to database
//     * @throws ServletException may be exception
//     * @throws IOException may be exception
//     */
//    @Test
//    public void updateTest() throws ServletException, IOException {
//        UserUpdateServlet userUpdate = new UserUpdateServlet();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        DBStore db = DBStore.getInstance();
//        this.createUser();
//        when(request.getParameter("id")).thenReturn(String.valueOf(db.getId()));
//        when(request.getParameter("name")).thenReturn("newTest");
//        when(request.getParameter("login")).thenReturn("newTest");
//        when(request.getParameter("password")).thenReturn("newPassword");
//        when(request.getParameter("email")).thenReturn("newTest@test.te");
//        when(request.getParameter("role")).thenReturn("user");
//        userUpdate.doPost(request, response);
//        User user = db.findById(db.getId());
//        Assert.assertTrue(user.getLogin().equals("newTest")
//                & user.getPassword().equals("newPassword"));
//        verify(request, atLeast(1)).getParameter("id");
//        verify(request, atLeast(1)).getParameter("name");
//        verify(request, atLeast(1)).getParameter("login");
//        verify(request, atLeast(1)).getParameter("password");
//        verify(request, atLeast(1)).getParameter("email");
//        verify(request, atLeast(1)).getParameter("role");
//    }
//
//    /**.
//     * Test deleting user to database
//     * @throws ServletException may be exception
//     * @throws IOException may be exception
//     */
//    @Test
//    public void deleteTest() throws ServletException, IOException {
//        UserDeleteServlet deleteUser = new UserDeleteServlet();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        this.createUser();
//        DBStore db = DBStore.getInstance();
//        int currentId = db.getId();
//        when(request.getParameter("id")).thenReturn(String.valueOf(currentId));
//        deleteUser.doGet(request, response);
//        User user = db.findById(currentId);
//        Assert.assertNull(user);
//        verify(request, atLeast(1)).getParameter("id");
//    }
//}