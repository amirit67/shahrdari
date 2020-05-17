package com.shahrdari.rest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAMAN on 3/17/2018.
 */

public interface IWebservice {
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    interface IBaseResponse {
       // void getResult(BaseResponse response) throws Exception;

        void getError(String error) throws Exception;
    }

    interface ProgressListener {
        void onProgress(long bytesWritten, long contentLength, boolean done);
    }

    interface setExpertPosition {
        void setPosition(String pos);
    }
}
