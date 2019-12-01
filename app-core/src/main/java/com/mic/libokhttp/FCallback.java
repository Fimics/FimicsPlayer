package com.mic.libokhttp;

import java.io.IOException;

public interface FCallback {

    void onFailure(FCall call, IOException e);

    void onResponse(FCall call,FResponse response);
}
