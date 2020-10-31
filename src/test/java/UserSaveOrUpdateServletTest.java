import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import servlet.UserSaveOrUpdateServlet;
import store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MemStore.class)

public class UserSaveOrUpdateServletTest {

    @Test
   public void whenSaveUser() throws IOException, ServletException {
        MemStore memStore = MemStore.instOf();
        PowerMockito.mockStatic(MemStore.class);
        PowerMockito.when(MemStore.instOf()).thenReturn(memStore);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PowerMockito.when(req.getParameter("id")).thenReturn("0");
        PowerMockito.when(req.getParameter("e-mail")).thenReturn("newemail@mail.com");
        PowerMockito.when(req.getParameter("password")).thenReturn("password");
        PowerMockito.when(req.getParameter("name")).thenReturn("Petr Arsentev");
        new UserSaveOrUpdateServlet().doPost(req, resp);
        Iterator<User> it = memStore.findAllUsers().iterator();
        User userOne = it.next();
        User userSec = it.next();
        Assert.assertThat(userSec.getName(), is("Petr Arsentev"));
    }

    @Test
    public void whenUpdateUser() throws IOException, ServletException {
        MemStore memStore = MemStore.instOf();
        PowerMockito.mockStatic(MemStore.class);
        PowerMockito.when(MemStore.instOf()).thenReturn(memStore);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PowerMockito.when(req.getParameter("id")).thenReturn("1");
        PowerMockito.when(req.getParameter("e-mail")).thenReturn("newemail@gmail.com");
        PowerMockito.when(req.getParameter("password")).thenReturn("newpassword");
        PowerMockito.when(req.getParameter("name")).thenReturn("Petr");
        new UserSaveOrUpdateServlet().doPost(req, resp);
        User user = memStore.findAllUsers().iterator().next();
        Assert.assertThat(user.getName(), is("Petr"));
    }

}