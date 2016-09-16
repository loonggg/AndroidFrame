package com.loonggg.androidframedemo.net.rpc.service;



import com.loonggg.androidframedemo.net.rpc.model.ResponseModel;
import com.loonggg.androidframedemo.net.rpc.model.TestInfoModel;


import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface AppService {

  @Multipart
  @POST("file/upload")
  Observable<Response<ResponseModel<String>>> upload(@Part MultipartBody.Part file);
  @GET("sms/verify/{mobileNo}/{code}")
  Observable<Response<ResponseModel<String>>> verifyCode(@Path("mobileNo") String mobileNo, @Path("code") String code);
  @DELETE("member/mycar/{userId}/{token}")
  Observable<Response<ResponseModel<String>>> deleteMyLoveCar(@Path("userId") String userId, @Path("token") String token, @Query("serialIds") String serialIds);
  @GET("weixin/query")
  Observable<Response<ResponseModel<TestInfoModel>>> getTestInfo(@Query("key") String key, @Query("pno")
          int page, @Query("ps") int pagesize, @Query("dtype") String type);
}
