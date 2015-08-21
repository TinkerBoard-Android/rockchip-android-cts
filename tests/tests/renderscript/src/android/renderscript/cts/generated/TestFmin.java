/*
 * Copyright (C) 2015 The Android Open Source Project
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

// Don't edit this file!  It is auto-generated by frameworks/rs/api/generate.sh.

package android.renderscript.cts;

import android.renderscript.Allocation;
import android.renderscript.RSRuntimeException;
import android.renderscript.Element;

public class TestFmin extends RSBaseCompute {

    private ScriptC_TestFmin script;
    private ScriptC_TestFminRelaxed scriptRelaxed;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        script = new ScriptC_TestFmin(mRS);
        scriptRelaxed = new ScriptC_TestFminRelaxed(mRS);
    }

    public class ArgumentsFloatFloatFloat {
        public float inA;
        public float inB;
        public Target.Floaty out;
    }

    private void checkFminFloatFloatFloat() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x1d7b1058l, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x1d7b1059l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloatFloatFloat(inA, out);
            verifyResultsFminFloatFloatFloat(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloatFloatFloat: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 1), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloatFloatFloat(inA, out);
            verifyResultsFminFloatFloatFloat(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloatFloatFloat: " + e.toString());
        }
    }

    private void verifyResultsFminFloatFloatFloat(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 1];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 1];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 1];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 1 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i];
                args.inB = arrayInB[i];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 1 + j]);
                    if (!args.out.couldBe(arrayOut[i * 1 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloatFloatFloat" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkFminFloat2Float2Float2() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0xe75faa42l, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0xe75faa43l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloat2Float2Float2(inA, out);
            verifyResultsFminFloat2Float2Float2(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat2Float2Float2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloat2Float2Float2(inA, out);
            verifyResultsFminFloat2Float2Float2(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat2Float2Float2: " + e.toString());
        }
    }

    private void verifyResultsFminFloat2Float2Float2(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 2];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 2];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 2];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i * 2 + j];
                args.inB = arrayInB[i * 2 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 2 + j]);
                    if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloat2Float2Float2" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkFminFloat3Float3Float3() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0xe93dabe3l, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0xe93dabe4l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloat3Float3Float3(inA, out);
            verifyResultsFminFloat3Float3Float3(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat3Float3Float3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloat3Float3Float3(inA, out);
            verifyResultsFminFloat3Float3Float3(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat3Float3Float3: " + e.toString());
        }
    }

    private void verifyResultsFminFloat3Float3Float3(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 4];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 4];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 4];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i * 4 + j];
                args.inB = arrayInB[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 4 + j]);
                    if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloat3Float3Float3" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkFminFloat4Float4Float4() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0xeb1bad84l, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0xeb1bad85l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloat4Float4Float4(inA, out);
            verifyResultsFminFloat4Float4Float4(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat4Float4Float4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloat4Float4Float4(inA, out);
            verifyResultsFminFloat4Float4Float4(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat4Float4Float4: " + e.toString());
        }
    }

    private void verifyResultsFminFloat4Float4Float4(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 4];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 4];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 4];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i * 4 + j];
                args.inB = arrayInB[i * 4 + j];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 4 + j]);
                    if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloat4Float4Float4" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkFminFloat2FloatFloat2() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 2, 0x24457670l, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x24457671l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloat2FloatFloat2(inA, out);
            verifyResultsFminFloat2FloatFloat2(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat2FloatFloat2: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 2), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloat2FloatFloat2(inA, out);
            verifyResultsFminFloat2FloatFloat2(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat2FloatFloat2: " + e.toString());
        }
    }

    private void verifyResultsFminFloat2FloatFloat2(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 2];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 1];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 2];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 2 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i * 2 + j];
                args.inB = arrayInB[i];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 2 + j]);
                    if (!args.out.couldBe(arrayOut[i * 2 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloat2FloatFloat2" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkFminFloat3FloatFloat3() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 3, 0x12b06accl, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x12b06acdl, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloat3FloatFloat3(inA, out);
            verifyResultsFminFloat3FloatFloat3(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat3FloatFloat3: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 3), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloat3FloatFloat3(inA, out);
            verifyResultsFminFloat3FloatFloat3(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat3FloatFloat3: " + e.toString());
        }
    }

    private void verifyResultsFminFloat3FloatFloat3(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 4];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 1];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 4];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 3 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i * 4 + j];
                args.inB = arrayInB[i];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 4 + j]);
                    if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloat3FloatFloat3" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    private void checkFminFloat4FloatFloat4() {
        Allocation inA = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 4, 0x11b5f28l, false);
        Allocation inB = createRandomAllocation(mRS, Element.DataType.FLOAT_32, 1, 0x11b5f29l, false);
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            script.set_gAllocInB(inB);
            script.forEach_testFminFloat4FloatFloat4(inA, out);
            verifyResultsFminFloat4FloatFloat4(inA, inB, out, false);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat4FloatFloat4: " + e.toString());
        }
        try {
            Allocation out = Allocation.createSized(mRS, getElement(mRS, Element.DataType.FLOAT_32, 4), INPUTSIZE);
            scriptRelaxed.set_gAllocInB(inB);
            scriptRelaxed.forEach_testFminFloat4FloatFloat4(inA, out);
            verifyResultsFminFloat4FloatFloat4(inA, inB, out, true);
        } catch (Exception e) {
            throw new RSRuntimeException("RenderScript. Can't invoke forEach_testFminFloat4FloatFloat4: " + e.toString());
        }
    }

    private void verifyResultsFminFloat4FloatFloat4(Allocation inA, Allocation inB, Allocation out, boolean relaxed) {
        float[] arrayInA = new float[INPUTSIZE * 4];
        inA.copyTo(arrayInA);
        float[] arrayInB = new float[INPUTSIZE * 1];
        inB.copyTo(arrayInB);
        float[] arrayOut = new float[INPUTSIZE * 4];
        out.copyTo(arrayOut);
        for (int i = 0; i < INPUTSIZE; i++) {
            for (int j = 0; j < 4 ; j++) {
                // Extract the inputs.
                ArgumentsFloatFloatFloat args = new ArgumentsFloatFloatFloat();
                args.inA = arrayInA[i * 4 + j];
                args.inB = arrayInB[i];
                // Figure out what the outputs should have been.
                Target target = new Target(relaxed);
                CoreMathVerifier.computeFmin(args, target);
                // Validate the outputs.
                boolean valid = true;
                if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                    valid = false;
                }
                if (!valid) {
                    StringBuilder message = new StringBuilder();
                    message.append("Input inA: ");
                    appendVariableToMessage(message, args.inA);
                    message.append("\n");
                    message.append("Input inB: ");
                    appendVariableToMessage(message, args.inB);
                    message.append("\n");
                    message.append("Expected output out: ");
                    appendVariableToMessage(message, args.out);
                    message.append("\n");
                    message.append("Actual   output out: ");
                    appendVariableToMessage(message, arrayOut[i * 4 + j]);
                    if (!args.out.couldBe(arrayOut[i * 4 + j])) {
                        message.append(" FAIL");
                    }
                    message.append("\n");
                    assertTrue("Incorrect output for checkFminFloat4FloatFloat4" +
                            (relaxed ? "_relaxed" : "") + ":\n" + message.toString(), valid);
                }
            }
        }
    }

    public void testFmin() {
        checkFminFloatFloatFloat();
        checkFminFloat2Float2Float2();
        checkFminFloat3Float3Float3();
        checkFminFloat4Float4Float4();
        checkFminFloat2FloatFloat2();
        checkFminFloat3FloatFloat3();
        checkFminFloat4FloatFloat4();
    }
}
