package co.com.ceiba.mobile.pruebadeingreso.view.viewmodel;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PostViewModelTest {

    private PostViewModel postViewModel;

    @Before
    public void setUp () throws Exception {
        Application application =
                (Application) InstrumentationRegistry
                        .getTargetContext()
                        .getApplicationContext();

        postViewModel = new PostViewModel(application, 1);
    }

    @Test
    public void getListUserTest () throws Exception {
        assertNotNull(postViewModel.getListPost());
    }
}