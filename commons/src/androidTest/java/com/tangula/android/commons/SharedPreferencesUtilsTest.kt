package com.tangula.android.commons

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tangula.android.utils.SharedPreferencesUtils
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferencesUtilsTest {

    @Test
    fun testSharedPreferences() {
        // Context of the app under test.
        val ctx = InstrumentationRegistry.getTargetContext()
        val sp_name = "test shared preferences"
        val key_boolean = "key_boolean"
        val key_int = "key_int"
        val key_long = "key_long"
        val key_float = "key_float"
        val key_string = "key_string"
        val key_sgtrings = "key_sgtrings"

        val default_float = -1.0f
        val value_float = 1.0f
        val default_string = "This is a string."
        val value_string = "This is a shared preference test value."
        val strings = setOf<String>(default_string, value_string)

        var vl: Any?
        SharedPreferencesUtils.putBooleanPrivateA(ctx, sp_name, key_boolean,null)
        vl = SharedPreferencesUtils.getBooleanPrivate(ctx, sp_name, key_boolean, false)
        assertFalse(vl)
        SharedPreferencesUtils.putBooleanPrivateA(ctx, sp_name, key_boolean,true)
        vl = SharedPreferencesUtils.getBooleanPrivate(ctx, sp_name, key_boolean, false)
        assertTrue(vl)

        SharedPreferencesUtils.putIntPrivateA(ctx, sp_name, key_int,null)
        vl = SharedPreferencesUtils.getIntPrivate(ctx, sp_name, key_int, -1)
        assertEquals(vl, -1)
        SharedPreferencesUtils.putIntPrivateA(ctx, sp_name, key_int,1)
        vl = SharedPreferencesUtils.getIntPrivate(ctx, sp_name, key_int, -1)
        assertEquals(vl, 1)

        SharedPreferencesUtils.putLongPrivateA(ctx, sp_name, key_long,null)
        vl = SharedPreferencesUtils.getLongPrivate(ctx, sp_name, key_long, -1)
        assertEquals(vl, -1)
        SharedPreferencesUtils.putLongPrivateA(ctx, sp_name, key_long,1)
        vl = SharedPreferencesUtils.getLongPrivate(ctx, sp_name, key_long, -1)
        assertEquals(vl, 1)

        SharedPreferencesUtils.putFloatPrivateA(ctx, sp_name, key_float,null)
        vl = SharedPreferencesUtils.getFloatPrivate(ctx, sp_name, key_float, default_float)
        assertEquals(vl, default_float)
        SharedPreferencesUtils.putFloatPrivateA(ctx, sp_name, key_float,value_float)
        vl = SharedPreferencesUtils.getFloatPrivate(ctx, sp_name, key_float, default_float)
        assertEquals(vl, value_float)

        SharedPreferencesUtils.putStringPrivateA(ctx, sp_name, key_string,null)
        vl = SharedPreferencesUtils.getStringPrivate(ctx, sp_name, key_string, default_string)
        assertEquals(vl, default_string)
        SharedPreferencesUtils.putStringPrivateA(ctx, sp_name, key_string,value_string)
        vl = SharedPreferencesUtils.getStringPrivate(ctx, sp_name, key_string, default_string)
        assertEquals(vl, value_string)

        SharedPreferencesUtils.putStringSetPrivateA(ctx, sp_name, key_sgtrings,null)
        vl = SharedPreferencesUtils.getStringSetPrivate(ctx, sp_name, key_sgtrings)
        assertNotNull(vl)
        assertTrue(vl.isEmpty())
        SharedPreferencesUtils.putStringSetPrivateA(ctx, sp_name, key_sgtrings,strings)
        vl = SharedPreferencesUtils.getStringSetPrivate(ctx, sp_name, key_sgtrings)
        assertNotNull(vl)
        assertTrue(!vl.isEmpty())
        assertEquals(vl.size,2)
        assertTrue(vl.contains(default_string))
        assertTrue(vl.contains(value_string))

    }
}