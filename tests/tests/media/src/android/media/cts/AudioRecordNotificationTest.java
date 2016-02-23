/*
 * Copyright (C) 2016 The Android Open Source Project
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

package android.media.cts;

import android.content.pm.PackageManager;
import android.cts.util.CtsAndroidTestCase;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordConfiguration;
import android.media.MediaRecorder;
import android.os.Looper;
import android.util.Log;

public class AudioRecordNotificationTest extends CtsAndroidTestCase {
    private final static String TAG = "AudioRecordNotificationTest";

    private final static int TEST_SAMPLE_RATE = 16000;
    private final static int TEST_AUDIO_SOURCE = MediaRecorder.AudioSource.VOICE_RECOGNITION;

    private final static int TEST_TIMING_TOLERANCE_MS = 70;

    private AudioRecord mAudioRecord;
    private Looper mLooper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (!hasMicrophone()) {
            return;
        }

        /*
         * InstrumentationTestRunner.onStart() calls Looper.prepare(), which creates a looper
         * for the current thread. However, since we don't actually call loop() in the test,
         * any messages queued with that looper will never be consumed. Therefore, we must
         * create the instance in another thread, either without a looper, so the main looper is
         * used, or with an active looper.
         */
        Thread t = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mLooper = Looper.myLooper();
                synchronized(this) {
                    mAudioRecord = new AudioRecord.Builder()
                                     .setAudioSource(TEST_AUDIO_SOURCE)
                                     .setAudioFormat(new AudioFormat.Builder()
                                             .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                                             .setSampleRate(TEST_SAMPLE_RATE)
                                             .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
                                             .build())
                                     .build();
                    this.notify();
                }
                Looper.loop();
            }
        };
        synchronized(t) {
            t.start(); // will block until we wait
            t.wait();
        }
        assertNotNull(mAudioRecord);
        assertNotNull(mLooper);
    }

    @Override
    protected void tearDown() throws Exception {
        if (hasMicrophone()) {
            mAudioRecord.stop();
            mAudioRecord.release();
            mLooper.quit();
        }
        super.tearDown();
    }

    // start a recording and verify it is seen as an active recording
    public void testAudioManagerGetActiveRecordConfigurations() throws Exception {
        if (!hasMicrophone()) {
            return;
        }
        AudioManager am = new AudioManager(getContext());
        assertNotNull("Could not create AudioManager", am);

        AudioRecordConfiguration[] configs = am.getActiveRecordConfigurations();
        assertNotNull("Invalid null array of record configurations before recording", configs);

        assertEquals(AudioRecord.STATE_INITIALIZED, mAudioRecord.getState());
        mAudioRecord.startRecording();
        assertEquals(AudioRecord.RECORDSTATE_RECORDING, mAudioRecord.getRecordingState());
        Thread.sleep(TEST_TIMING_TOLERANCE_MS);

        // recording is active, verify there is an active record configuration
        configs = am.getActiveRecordConfigurations();
        assertNotNull("Invalid null array of record configurations during recording", configs);
        assertTrue("no active record configurations (empty array) during recording",
                configs.length > 0);
        final int nbConfigsDuringRecording = configs.length;

        // verify our recording shows as one of the recording configs
        assertTrue("Test source/session not amongst active record configurations",
                verifyAudioSourceSession(TEST_AUDIO_SOURCE, mAudioRecord.getAudioSessionId(),
                        configs));

        // stopping recording: verify there are less active record configurations
        mAudioRecord.stop();
        Thread.sleep(TEST_TIMING_TOLERANCE_MS);
        configs = am.getActiveRecordConfigurations();
        assertTrue("end of recording not reported in record configs",
                configs.length < nbConfigsDuringRecording);
    }

    public void testCallback() throws Exception {
        if (!hasMicrophone()) {
            return;
        }
        AudioManager am = new AudioManager(getContext());
        assertNotNull("Could not create AudioManager", am);

        MyAudioRecordingCallback callback = new MyAudioRecordingCallback(
                mAudioRecord.getAudioSessionId(), TEST_AUDIO_SOURCE);
        am.registerAudioRecordingCallback(callback, null /*handler*/);

        assertEquals(AudioRecord.STATE_INITIALIZED, mAudioRecord.getState());
        mAudioRecord.startRecording();
        assertEquals(AudioRecord.RECORDSTATE_RECORDING, mAudioRecord.getRecordingState());
        Thread.sleep(TEST_TIMING_TOLERANCE_MS);

        assertTrue("AudioRecordingCallback not called", callback.mCalled);
        assertTrue("Expected record configuration was not found", callback.mParamMatch);

        // stopping recording: callback is called with no match
        callback.reset();
        mAudioRecord.stop();
        Thread.sleep(TEST_TIMING_TOLERANCE_MS);
        assertTrue("AudioRecordingCallback not called", callback.mCalled);
        assertFalse("Should not have found test record configuration", callback.mParamMatch);

        // unregister callback and start recording again
        am.unregisterAudioRecordingCallback(callback);
        callback.reset();
        mAudioRecord.startRecording();
        Thread.sleep(TEST_TIMING_TOLERANCE_MS);
        assertFalse("Unregistered callback was called", callback.mCalled);
    }

    class MyAudioRecordingCallback extends AudioManager.AudioRecordingCallback {
        boolean mCalled = false;
        boolean mParamMatch = false;
        final AudioManager mAM;
        final int mTestSource;
        final int mTestSession;

        void reset() {
            mCalled = false;
            mParamMatch = false;
        }

        MyAudioRecordingCallback(int session, int source) {
            mAM = new AudioManager(getContext());
            mTestSource = source;
            mTestSession = session;
        }

        @Override
        public void onRecordConfigChanged() {
            mCalled = true;
            mParamMatch = verifyAudioSourceSession(mTestSource, mTestSession,
                    mAM.getActiveRecordConfigurations());
        }
    }

    private static boolean verifyAudioSourceSession(int source, int session,
            AudioRecordConfiguration[] configs) {
        for (int i = 0 ; i < configs.length ; i++) {
            if ((configs[i].getClientAudioSource() == source) &&
                    (configs[i].getClientAudioSessionId() == session)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMicrophone() {
        return getContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_MICROPHONE);
    }
}