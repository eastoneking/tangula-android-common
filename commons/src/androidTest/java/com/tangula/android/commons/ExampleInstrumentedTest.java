package com.tangula.android.commons;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.tangula.android.utils.ApplicationUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.tangula.android.commons.test", appContext.getPackageName());

    }

    @Test
    public void uniqueId(){
        String id1 = ApplicationUtils.fetchUniqueId();
        String id2 = ApplicationUtils.fetchUniqueId();
        assertEquals(id1, id2);
    }
}
