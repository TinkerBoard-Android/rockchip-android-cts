/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
#ifndef FULLPIPELINEPROGRAM_H
#define FULLPIPELINEPROGRAM_H

#include <graphics/Matrix.h>
#include <graphics/Program.h>

#include <EGL/egl.h>
#include <GLES2/gl2.h>
#include <GLES2/gl2ext.h>

class FullPipelineProgram: public Program {
public:
    FullPipelineProgram(GLuint programId);
    virtual ~FullPipelineProgram() {};
    Matrix mMVMatrix;
    Matrix mMVPMatrix;
    Matrix mLightModelMatrix;
    float mLightPosInModelSpace[4];
    float mLightPosInWorldSpace[4];
    float mLightPosInEyeSpace[4];

    int mMVMatrixHandle;
    int mMVPMatrixHandle;
    int mLightPosHandle;
    int mTexCoordHandle;
    int mPositionHandle;
    int mNormalHandle;
    int mTextureUniformHandle;
    virtual void before(Matrix& model, Matrix& view, Matrix& projection);
};

#endif
