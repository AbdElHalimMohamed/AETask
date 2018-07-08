package com.halim.aetask.domain.entity


data class Car(val id: Long,
               val makeEng: String,
               val modelEng: String,
               val makeAr: String,
               val modelAr: String,
               val year: Int,
               var image: String,
               val auctionInfo: AuctionInfo?)