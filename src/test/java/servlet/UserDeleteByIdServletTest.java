package servlet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import store.MemStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MemStore.class)

public class UserDeleteByIdServletTest {
    @Test
    public void whenDeleteUser() throws IOException, ServletException {
        MemStore memStore = MemStore.instOf();
        PowerMockito.mockStatic(MemStore.class);
        PowerMockito.when(MemStore.instOf()).thenReturn(memStore);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PowerMockito.when(req.getParameter("id")).thenReturn("1");
        new UserDeleteByIdServlet().doPost(req, resp);
        Assert.assertThat(memStore.findAllUsers().size(), is(0));
    }
}