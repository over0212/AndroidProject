package com.project.housing.repository;

import com.project.housing.models.response.Response;
import com.project.housing.utils.Define;
import com.tickaroo.tikxml.TikXml;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HousingService {

    // DB 에 넣으면 보안 더 좋음
    // public static final 생략
    String encodingKey = "yV2kSoCwfTpXgup8rjKQTTIByUrOy5CjyDekDJpcrE0XZMUBS7EKjA0IYVspvleEaUdmka6Jqnhi6Tu%2Fp5RNKA%3D%3D";
    String decodingKey = "yV2kSoCwfTpXgup8rjKQTTIByUrOy5CjyDekDJpcrE0XZMUBS7EKjA0IYVspvleEaUdmka6Jqnhi6Tu/p5RNKA==";


    @GET("getLttotPblancList")
    Call<Response> getHousingList(@Query("serviceKey") String serviceKey,
                                  @Query("startmonth") String startMonth,
                                  @Query("endmonth") String endMonth,
                                  @Query("sido") String sido);



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Define.BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .build();
}

