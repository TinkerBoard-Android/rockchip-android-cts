# Copyright 2013 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import its.image
import its.caps
import its.device
import its.objects
import its.target
import pylab
import os.path
import matplotlib
import matplotlib.pyplot

def main():
    """Test that the android.sensor.sensitivity parameter is applied.
    """
    NAME = os.path.basename(__file__).split(".")[0]

    NUM_STEPS = 5

    sensitivities = None
    r_means = []
    g_means = []
    b_means = []

    with its.device.ItsSession() as cam:
        props = cam.get_camera_properties()
        its.caps.skip_unless(its.caps.compute_target_exposure(props) and
                             its.caps.per_frame_control(props))

        debug = its.caps.debug_mode()
        largest_yuv = its.objects.get_largest_yuv_format(props)
        if debug:
            fmt = largest_yuv
        else:
            match_ar = (largest_yuv['width'], largest_yuv['height'])
            fmt = its.objects.get_smallest_yuv_format(props, match_ar=match_ar)

        expt,_ = its.target.get_target_exposure_combos(cam)["midSensitivity"]
        sens_range = props['android.sensor.info.sensitivityRange']
        sens_step = (sens_range[1] - sens_range[0]) / float(NUM_STEPS-1)
        sensitivities = [sens_range[0] + i * sens_step for i in range(NUM_STEPS)]

        for s in sensitivities:
            req = its.objects.manual_capture_request(s, expt)
            cap = cam.do_capture(req, fmt)
            img = its.image.convert_capture_to_rgb_image(cap)
            its.image.write_image(
                    img, "%s_iso=%04d.jpg" % (NAME, s))
            tile = its.image.get_image_patch(img, 0.45, 0.45, 0.1, 0.1)
            rgb_means = its.image.compute_image_means(tile)
            r_means.append(rgb_means[0])
            g_means.append(rgb_means[1])
            b_means.append(rgb_means[2])

    # Draw a plot.
    pylab.plot(sensitivities, r_means, 'r')
    pylab.plot(sensitivities, g_means, 'g')
    pylab.plot(sensitivities, b_means, 'b')
    pylab.ylim([0,1])
    matplotlib.pyplot.savefig("%s_plot_means.png" % (NAME))

    # Test for pass/fail: check that each shot is brighter than the previous.
    for means in [r_means, g_means, b_means]:
        for i in range(len(means)-1):
            assert(means[i+1] > means[i])

if __name__ == '__main__':
    main()

