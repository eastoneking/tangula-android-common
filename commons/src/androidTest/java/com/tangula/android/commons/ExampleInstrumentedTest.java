package com.tangula.android.commons;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.tangula.android.utils.ApplicationUtils;
import com.tangula.utils.function.Supplier;

import org.apache.commons.lang3.StringUtils;
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
    public void tesetUniqueId(){

        Context ctx = InstrumentationRegistry.getTargetContext();

        assertNotNull(ApplicationUtils.Companion.getAndroidId(ctx));

        String id1 = ApplicationUtils.fetchUniqueId(ctx);
        String id2 = ApplicationUtils.fetchUniqueId(ctx);
        assertEquals(id1, id2);

        ApplicationUtils.clearUniqueId(ctx);
        String id3 = ApplicationUtils.loadUniqueId(ctx);
        assertTrue(StringUtils.isNotBlank(id3));
        assertEquals(id1, id3);
    }


}
