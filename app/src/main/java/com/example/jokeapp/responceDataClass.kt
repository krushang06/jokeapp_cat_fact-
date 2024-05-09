package com.example.jokeapp

import com.google.gson.annotations.SerializedName

data class responceDataClass(
    @SerializedName("data"  ) var datas: Datas = Datas(),
    @SerializedName("count" ) var count: Int?  = null
)
data class Datas(
    @SerializedName("_id"              ) var Id               : String?                 = null,
    @SerializedName("name"             ) var name             : String?                 = null,
    @SerializedName("createdBy"        ) var createdBy        : String?                 = null,
    @SerializedName("duration"         ) var duration         : Int?                    = null,
    @SerializedName("questionType"     ) var questionType     : ArrayList<String>       = arrayListOf(),
    @SerializedName("questions"        ) var questions        : ArrayList<Questions>    = arrayListOf(),
    @SerializedName("totalMarks"       ) var totalMarks       : Int?                    = null,
    @SerializedName("totalQuestions"   ) var totalQuestions   : Int?                    = null,
    @SerializedName("releaseDate"      ) var releaseDate      : String?                 = null,
    @SerializedName("isActive"         ) var isActive         : Boolean?                = null,
    @SerializedName("createdAt"        ) var createdAt        : String?                 = null,
    @SerializedName("updatedAt"        ) var updatedAt        : String?                 = null,
    @SerializedName("__v"              ) var _v               : Int?                    = null

)
data class Questions (

    @SerializedName("questionId" ) var questionId : QuestionId? = QuestionId(),
    @SerializedName("marks"      ) var marks      : Int?        = null
)
data class QuestionId (

    @SerializedName("_id"          ) var Id           : String?            = null,
    @SerializedName("qrCodeId"     ) var qrCodeId     : String?            = null,
    @SerializedName("questionNo"   ) var questionNo   : Int?               = null,
    @SerializedName("question"     ) var question     : String?            = null,
    @SerializedName("options"      ) var options      : ArrayList<Options> = arrayListOf(),
    @SerializedName("answer"       ) var answer       : Int?               = null,
    @SerializedName("solution"     ) var solution     : String?            = null,
    @SerializedName("deleted"      ) var deleted      : Boolean?           = null,
    @SerializedName("processingId" ) var processingId : String?            = null,
    @SerializedName("__v"          ) var _v           : Int?               = null,
    @SerializedName("subQuestions" ) var subQuestions : ArrayList<String>  = arrayListOf()
)

data class Options (

    @SerializedName("option" ) var option : String? = null,
    @SerializedName("number" ) var number : Int?    = null

)


