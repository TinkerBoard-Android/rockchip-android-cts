/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.jni.cts;

import junit.framework.TestCase;

/**
 * Basic native instance method tests. The "nonce" class being tested
 * by this class is a class defined in this package that declares the
 * bulk of its methods as native.
 */
public class JniInstanceTest extends TestCase {
    /** instance to use for all the tests */
    private InstanceNonce target;

    @Override
    protected void setUp() {
        target = new InstanceNonce();
    }

    /**
     * Test a simple no-op and void-returning method call.
     */
    public void test_nop() {
        target.nop();
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnBoolean() {
        assertEquals(false, target.returnBoolean());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnByte() {
        assertEquals(123, target.returnByte());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnShort() {
        assertEquals(-12345, target.returnShort());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnChar() {
        assertEquals(34567, target.returnChar());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnInt() {
        assertEquals(12345678, target.returnInt());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnLong() {
        assertEquals(-1098765432109876543L, target.returnLong());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnFloat() {
        assertEquals(-98765.4321F, target.returnFloat());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnDouble() {
        assertEquals(12345678.9, target.returnDouble());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnNull() {
        assertNull(target.returnNull());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnString() {
        assertEquals("blort", target.returnString());
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnShortArray() {
        short[] array = target.returnShortArray();
        assertSame(short[].class, array.getClass());
        assertEquals(3, array.length);
        assertEquals(10, array[0]);
        assertEquals(20, array[1]);
        assertEquals(30, array[2]);
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call.
     */
    public void test_returnStringArray() {
        String[] array = target.returnStringArray();
        assertSame(String[].class, array.getClass());
        assertEquals(100, array.length);
        assertEquals("blort", array[0]);
        assertEquals(null,    array[1]);
        assertEquals("zorch", array[50]);
        assertEquals("fizmo", array[99]);
    }

    /**
     * Test a simple value-returning (but otherwise no-op) method call,
     * that returns the implicit {@code this} argument.
     */
    public void test_returnThis() {
        assertSame(target, target.returnThis());
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeBoolean() {
        assertTrue(target.takeBoolean(false));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeByte() {
        assertTrue(target.takeByte((byte) -99));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeShort() {
        assertTrue(target.takeShort((short) 19991));
    }
    
    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeChar() {
        assertTrue(target.takeChar((char) 999));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeInt() {
        assertTrue(target.takeInt(-999888777));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeLong() {
        assertTrue(target.takeLong(999888777666555444L));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeFloat() {
        assertTrue(target.takeFloat(-9988.7766F));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeDouble() {
        assertTrue(target.takeDouble(999888777.666555));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeNull() {
        assertTrue(target.takeNull(null));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value.
     */
    public void test_takeString() {
        assertTrue(target.takeString("fuzzbot"));
    }

    /**
     * Test a simple value-taking method call, that returns whether it
     * got the expected value. In particular, this test passes the
     * instance the method is called on.
     */
    public void test_takeThis() {
        assertTrue(target.takeThis(target));
    }

    // TODO: Add more tests here. E.g:
    //    call to method taking (int, long), returning a "got expected" flag
    //    call to method taking (long, int), returning a "got expected" flag
    //    call to method taking one of each primitive type, an object, and
    //      an array
    //    call to method taking 50 arguments
}
