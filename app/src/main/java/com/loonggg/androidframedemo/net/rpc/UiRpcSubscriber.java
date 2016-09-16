package com.loonggg.androidframedemo.net.rpc;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import com.loonggg.androidframedemo.app.CustomApp;
import com.loonggg.androidframedemo.log.Logger;
import com.loonggg.androidframedemo.net.constant.NetConstant;
import com.loonggg.androidframedemo.net.rpc.model.ResponseModel;
import com.loonggg.androidframedemo.net.rpc.model.UserInfo;
import com.loonggg.androidframedemo.utils.preference.CustomAppPreferences;

import retrofit2.Response;
import rx.Subscriber;

/**
 * 简化的，供UI调用网络接口使用的RpcSubscriber，统一处理HttpError提示
 */
public abstract class UiRpcSubscriber<T> extends Subscriber<Response<ResponseModel<T>>> {
    private static final String TAG = "SERVER_ERROR";
    HttpErrorUiNotifier httpErrorUiNotifier;
    SessionNotifier sessionNotifier;
    private Context mContext;

    public UiRpcSubscriber(Context context) {
        mContext = context;
        httpErrorUiNotifier =
                ((CustomApp) context.getApplicationContext()).getGlobalComponent().httpErrorUiNotifier();
        //sessionNotifier = ((CustomApp) context.getApplicationContext()).getGlobalComponent()
        //    .sessionNotifier();
    }

    @Override
    public final void onCompleted() {
        onEnd();
    }

    @Override
    public final void onError(Throwable e) {
        onHttpError(new RpcHttpError(NetConstant.HttpCodeConstant.UNKNOWN_ERROR, e.getMessage()));
        Logger.e(TAG, e, e.getMessage());
        onCompleted();
    }


    @Override
    public final void onNext(Response<ResponseModel<T>> responseModelResponse) {
        if (null == responseModelResponse || null == responseModelResponse.body()) {
            onHttpError(new RpcHttpError(NetConstant.HttpCodeConstant.UNKNOWN_ERROR, ""));
            return;
        }

        if (responseModelResponse.body().getError_code() == NetConstant.HttpCodeConstant.SESSION_EXPIRED) {
            onSessionExpired();
            return;
        }

        if (responseModelResponse.code() == NetConstant.HttpCodeConstant.SUCCESS
                && responseModelResponse.body().getError_code() == 0) {
            // 存储token
            UserInfo mUserInfo = ((CustomApp) mContext.getApplicationContext()).getGlobalComponent().loginSession().getUserInfo();
            if (mUserInfo != null && mUserInfo.getToken() != null && mUserInfo.getToken().length() > 0) {
                ((CustomApp) mContext.getApplicationContext()).getGlobalComponent().appPreferences().put
                        (CustomAppPreferences.KEY_COOKIE_SESSION_ID,
                                mUserInfo.getToken());
            } else if (responseModelResponse.body().getResult() instanceof String && responseModelResponse.body().getResult().toString() != null && responseModelResponse.body().getResult().toString().length() > 0) {
                ((CustomApp) mContext.getApplicationContext()).getGlobalComponent().appPreferences().put
                        (CustomAppPreferences.KEY_COOKIE_SESSION_ID,
                                (String) responseModelResponse.body().getResult());
            }
            onSuccess(responseModelResponse.body().getResult());
            return;
        }
        if (responseModelResponse.code() == NetConstant.HttpCodeConstant.HTTP_ERROR_NOT_FOUND) {
            onHttpError(new RpcHttpError(responseModelResponse.code(), responseModelResponse.message()));
            return;
        }
        onApiError(new RpcApiError(responseModelResponse.body().getError_code(), responseModelResponse
                .body().getReason()));
        onCompleted();
    }

    public void onApiError(RpcApiError apiError) {
        if (!TextUtils.isEmpty(apiError.getMessage())) {
            Toast.makeText(mContext, apiError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void onSessionExpired() {
        sessionNotifier.notifySessionExpired();
        onCompleted();
    }

    public void onHttpError(RpcHttpError httpError) {
        httpErrorUiNotifier.notifyUi(httpError);
    }

    protected abstract void onSuccess(T t);

    protected abstract void onEnd();

}
