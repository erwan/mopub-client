/*
 * Copyright (c) 2010, MoPub Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'MoPub Inc.' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.mopub.mobileads;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.appbrain.AppBrainBanner;
import com.appbrain.BannerListener;

import java.util.Map;

public class AppbrainAppliftAdapter extends CustomEventBanner implements BannerListener {
    
    private AppBrainBanner mBanner;
    private Listener mListener;

    @Override
    public void loadAd(Context context, Listener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serverExtras) {
        Log.d("mopub", "Load AppBrain Ad.");
        mListener = customEventBannerListener;
        mBanner = new AppBrainBanner(context);
        mBanner.setBannerListener(this);
        mBanner.requestAd();
    }

    @Override
    public void onInvalidate() {
        if (mBanner != null) {
            mBanner.invalidate();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick() {
        Log.d("mopub", "Register click for AppBrain AppLift.");
        mListener.onClick();
    }

    @Override
    public void onAdRequestDone(boolean adAvailable) {
        if (adAvailable) {
            Log.d("mopub", "AppBrain AppLift loaded.");
            mListener.setAdContentView(mBanner);
            mListener.onAdLoaded();
        } else {
            Log.d("mopub", "AppBrain AppLift failed. Trying an other.");
            mListener.onAdFailed();
        }
    }

}
