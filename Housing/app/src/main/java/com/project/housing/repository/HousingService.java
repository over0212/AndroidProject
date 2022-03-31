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

    String encodingKey_J = "Wo%2BC71YgezijTGRULf0MaEy2DE6ebKcVUBj%2BBQtKC874MPOvmgcTWDPUT5KIkyptDBjbasusTCHco0XTHXotPw%3D%3D";
    String decodingKey_J = "Wo+C71YgezijTGRULf0MaEy2DE6ebKcVUBj+BQtKC874MPOvmgcTWDPUT5KIkyptDBjbasusTCHco0XTHXotPw==";

    /**
     * 아파트 분양정보 조회 : getLttotPblancList
     * 아파트 분양정보 상세 조회 : getAPTLttotPblancDetail
     * 아파트 분양정보 주택형별 상세 조회 : getAPTLttotPblancMdl
     *
     * 오피스텔 외 분양정보 조회 : getNotAPTLttotPblancList
     * 오피스텔 외 분양정보 상세 조회 : getUrbtyOfctlLttotPblancDetail
     * 오피스텔 외 분양정보 주택형별 상세 조회 : getUrbtyOfctlLttotPblancMdl
     *
     * 아파트 무순위 분양정보 조회 : getRemndrLttotPblancList
     * 아파트 무순위 분양정보 상세 조회 : getRemndrLttotPblancDetail
     * 아파트 무순위 분양정보 주택형별 상세 조회 : getRemndrLttotPblancMdl
     */

    // APT(아파트) 분양정보 조회
    @GET("getLttotPblancList")
    Call<Response> getHousingList(@Query("ServiceKey") String serviceKey,
                                  @Query("startmonth") String startMonth,
                                  @Query("endmonth") String endMonth,
                                  @Query("sido") String sido,
                                  @Query("pageNo") int pageNo);

    @GET("getAPTLttotPblancDetail")
            Call<Response> getAPTListDetail(@Query("ServiceKey") String serviceKey,
                                            @Query("houseManageNo") String houseManageNo,
                                            @Query("pblancNo") String noticeNumber);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Define.BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create(new TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .build();
}

