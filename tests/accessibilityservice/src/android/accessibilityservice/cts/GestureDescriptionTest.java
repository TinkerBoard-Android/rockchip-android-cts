/**
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.accessibilityservice.cts;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.test.InstrumentationTestCase;

/**
 * Tests for creating gesture descriptions.
 */
public class GestureDescriptionTest extends InstrumentationTestCase {
    static final int NOMINAL_PATH_DURATION = 100;
    private Path mNominalPath;

    @Override
    public void setUp() {
        mNominalPath = new Path();
        mNominalPath.moveTo(0, 0);
        mNominalPath.lineTo(10, 10);
    }

    public void testCreateStroke_noDuration_shouldThrow() {
        try {
            new GestureDescription.StrokeDescription(mNominalPath, 0, 0);
            fail("Missing exception for stroke with no duration.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_negativeStartTime_shouldThrow() {
        try {
            new GestureDescription.StrokeDescription(mNominalPath, -1, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with negative start time.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_negativeStartX_shouldThrow() {
        Path negativeStartXPath = new Path();
        negativeStartXPath.moveTo(-1, 0);
        negativeStartXPath.lineTo(10, 10);
        try {
            new GestureDescription.StrokeDescription(negativeStartXPath, 0, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with negative start x coord.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_negativeStartY_shouldThrow() {
        Path negativeStartYPath = new Path();
        negativeStartYPath.moveTo(0, -1);
        negativeStartYPath.lineTo(10, 10);
        try {
            new GestureDescription.StrokeDescription(negativeStartYPath, 0, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with negative start y coord.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_negativeEndX_shouldThrow() {
        Path negativeEndXPath = new Path();
        negativeEndXPath.moveTo(0, 0);
        negativeEndXPath.lineTo(-10, 10);
        try {
            new GestureDescription.StrokeDescription(negativeEndXPath, 0, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with negative end x coord.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_negativeEndY_shouldThrow() {
        Path negativeEndYPath = new Path();
        negativeEndYPath.moveTo(0, 0);
        negativeEndYPath.lineTo(10, -10);
        try {
            new GestureDescription.StrokeDescription(negativeEndYPath, 0, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with negative end y coord.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_pathWithZeroLength_shouldThrow() {
        Path zeroLengthPath = new Path();
        zeroLengthPath.moveTo(0, 0);
        zeroLengthPath.lineTo(0, 0);
        try {
            new GestureDescription.StrokeDescription(zeroLengthPath, 0, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with path of zero length.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testCreateStroke_pathWithMultipleContours_shouldThrow() {
        Path multiContourPath = new Path();
        multiContourPath.moveTo(0, 0);
        multiContourPath.lineTo(10, 10);
        multiContourPath.moveTo(20, 0);
        multiContourPath.lineTo(20, 10);
        try {
            new GestureDescription.StrokeDescription(multiContourPath, 0, NOMINAL_PATH_DURATION);
            fail("Missing exception for stroke with multi-contour path.");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testAddStroke_allowUpToMaxPaths() {
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        for (int i = 0; i < GestureDescription.getMaxStrokeCount(); i++) {
            Path path = new Path();
            path.moveTo(i, i);
            path.lineTo(10 + i, 10 + i);
            gestureBuilder.addStroke(
                    new GestureDescription.StrokeDescription(path, 0, NOMINAL_PATH_DURATION));
        }
        Path path = new Path();
        path.moveTo(10, 10);
        path.lineTo(20, 20);
        try {
            gestureBuilder.addStroke(
                    new GestureDescription.StrokeDescription(path, 0, NOMINAL_PATH_DURATION));
            fail("Missing exception for adding too many strokes.");
        } catch (RuntimeException e) {
        }
    }

    public void testAddStroke_withDurationTooLong_shouldThrow() {
        Path path = new Path();
        path.moveTo(10, 10);
        path.lineTo(20, 20);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        try {
            gestureBuilder.addStroke(new GestureDescription.StrokeDescription(
                    path, 0, GestureDescription.getMaxGestureDuration() + 1));
            fail("Missing exception for adding stroke with duration too long.");
        } catch (RuntimeException e) {
        }
    }

    public void testEmptyDescription_shouldThrow() {
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        try {
            gestureBuilder.build();
            fail("Missing exception for building an empty gesture.");
        } catch (RuntimeException e) {
        }
    }

    public void testStrokeDescriptionGetters_workAsExpected() {
        int x = 100;
        int startY = 100;
        int endY = 150;
        int start = 50;
        int duration = 100;
        Path path = new Path();
        path.moveTo(x, startY);
        path.lineTo(x, endY);
        GestureDescription.StrokeDescription strokeDescription = new GestureDescription.StrokeDescription(path, start, duration);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(strokeDescription);

        GestureDescription gesture = builder.build();

        assertEquals(1, gesture.getStrokeCount());
        strokeDescription = gesture.getStroke(0);
        assertEquals(start, strokeDescription.getStartTime());
        assertEquals(duration, strokeDescription.getDuration());
        Path returnedPath = strokeDescription.getPath();
        PathMeasure measure = new PathMeasure(returnedPath, false);
        assertEquals(50, (int) measure.getLength());
    }
}
