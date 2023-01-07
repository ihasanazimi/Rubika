package com.example.rubika.repository.datasource.fake_data

import android.util.Log
import com.example.rubika.ApplicationLoader
import com.example.rubika.R
import com.example.rubika.repository.datasource.db.RoomDB
import com.example.rubika.model.Comment
import com.example.rubika.model.Post
import com.example.rubika.model.User
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
object FakeData {

    val posts = arrayListOf<Post>()

    val comments = arrayListOf<Comment>()
    val postCoverSample1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmPsObZKSlcr_yae5C5zJrNJcwLZzUZo5N3w&usqp=CAU"
    val postCoverSample2 = "https://www.androidauthority.com/wp-content/uploads/2022/04/Apple-iMessages-on-iPhone-stock-photo-1.jpg"
    val postCoverSample3 = "https://static.safetydetectives.com/wp-content/uploads/2021/09/Android.png"
    val postCoverSample4 = "https://emteria.com/hubfs/blog_header_prev/android-on-raspberry-pi-prev.webp"
    val postCoverSample5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwiXsbEYeM6U-VOKMrPva15P6tvBt2mJBbkA&usqp=CAU"
    val postCoverSample6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTd70GXuhM4ZWty7a7KPWAVif89KzhTRoWV4Hce6jb07RILd5YLHjlrPcKnBSsI-RlVAy0&usqp=CAU"
    val postCoverSample7 = "https://www.intel.com/content/dam/www/public/us/en/images/embedded/4x3/android_logo-blue-4x3.png.rendition.intel.web.864.486.png"
    val postCoverSample8 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTC98Sy6NNbgV4pQhMaq60J90v55hmCyBRVg&usqp=CAU"
    val postCoverSample9 = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Software_Freedom_Day_2016_in_Tehran_By_Behdad_Abedi_%28433%29.jpg/640px-Software_Freedom_Day_2016_in_Tehran_By_Behdad_Abedi_%28433%29.jpg"
    val postCoverSample10 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpeLrakYl5tIAaHMQB5BSV85mMKxuh_F3jmQ&usqp=CAU"
    val postCoverSample11 = "https://cdn.mos.cms.futurecdn.net/D5yzJidSNFiJizJT2u7HhG.jpg"
    val postCoverSample12 = "https://asset.kompas.com/crops/xuAqjhXLmlHyiCZl4NFN6nE2TWI=/13x0:798x523/750x500/data/photo/2017/05/18/3366487787.jpg"
    val postCoverSample13 = "https://9to5google.com/wp-content/uploads/sites/4/2022/04/switch_to_android_app_1.jpg?quality=82&strip=all"
    val postCoverSample14 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhlsYct1eTiFicunmbPhXn1zS7F36661He7g&usqp=CAU"
    val postCoverSample15 = "data:image/jpeg;base64,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"
    val postCoverSample16 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6SglSe6JQj5kSkbF7cBbP8rQQuxYZbboz7Q&usqp=CAU"
    val postCoverSample17 = "data:image/png;base64,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"
    val postCoverSample18 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRx0qLmXp-9sA6enh7RyZpAiTT5lrZbKmwdVLzY8ZCynIeWB5a1YXjG8XHeFPozkZSvyo0&usqp=CAU"
    val postCoverSample19 = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiC2X0sIY_AGvgi6jD8Eh_u8rOdZXKA6PP18tnJdA6jQxR-n4bF6vsIVI2D4FTOnHAlqSY5hJShEjHcRQr7P8QM-YyP3sM3Su_KxFRdBXhg8WUIoXr74luWfFvtgYGJHWdDe_gPnwpCsLR4YhE0U88QcSqrYs3LLjp7dGqQul_pRoerJr__-mD8lUPA/s1600/Android-IO22AndroidDevRecap_Social.png"
    val postCoverSample20 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdJKNx45f3xE_-RtPYmq7nV5_kNKQ7r8WnOg&usqp=CAU"
    val postCoverSample21 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbButugi36qGKkGUTlttIJLEWJqVMHpjqByw&usqp=CAU"
    val postCoverSample22 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgOypt76hAtQhgLoF70bDBLIVWi6XS2cHUcA&usqp=CAU"
    val postCoverSample23 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQ7CQy3o6gxqfUdFaRPNEB-ZClaNv44gNxwg&usqp=CAU"
    val postCoverSample24 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4Ejcn3EFZ--8-asxCj6wOsN-w6cb_YcYWr42txH3fT9YIT2flFHY1kOrdGixNDmDjAOc&usqp=CAU"
    val postCoverSample25 = "https://www.androidauthority.com/wp-content/uploads/2021/09/Apple-iPhone-13-Pro-Max-vs-Samsung-Galaxy-S21-Ultra-hero-image.jpg"
    val postCoverSample26 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcGC_AJHfet6KkosxDs-uhJk7QOT8Qh7F0_g&usqp=CAU"
    val postCoverSample27 = "https://www.softwaretestinghelp.com/wp-content/qa/uploads/2022/02/Best-Phone-Spying-Apps-for-Android-and-iPhone.png"
    val postCoverSample28 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUFzrQ58CQ9_OSdXkQt9jAH12QLIeBKZF1Dg&usqp=CAU"
    val postCoverSample29 = "https://beebom.com/wp-content/uploads/2020/03/12-Best-Custom-ROMs-for-Android-You-Can-Install.jpg?w=750&quality=75"
    val postCoverSample30 = "https://static1.anpoimages.com/wordpress/wp-content/uploads/2022/12/android-13-2023-ap-hero.jpg"


    val userCoverSample = "https://dl.limoonad.com/ProfileImage_vftyhgfvv43ef/jadi.jpg"
    val captions = "Test the Message · Click Email > Email Campaigns. · Click the Campaigns tab. · In the Actions column for the appropriate campaign, click Manage Test the Message · Click Email > Email Campaigns. · Click the Campaigns tab. · In the Actions column for the appropriate campaign, click Manage Test the Message · Click Email > Email Campaigns. · Click the Campaigns tab. · In the Actions column for the appropriate campaign, click Manage"

    fun generateFakePost (){
        val post1 = Post(1,User(1,"Hsn","HasanAzimi", userCoverSample), captions, postCoverSample29,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post2 = Post(2,User(2,"omid","Omid alishahi", userCoverSample), captions, postCoverSample1,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post3 = Post(3,User(3,"ahad","ahad moslemi", userCoverSample), captions, postCoverSample2,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post4 = Post(4,User(4,"zahra","zahra khodabande", userCoverSample), captions, postCoverSample3,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post5 = Post(5,User(5,"mahsa","mahsa amini", userCoverSample), captions, postCoverSample4,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post6 = Post(6,User(6,"nika","nika kini", userCoverSample), captions, postCoverSample5,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post7 = Post(7,User(7,"kian","kian pirfalak", userCoverSample), captions, postCoverSample6,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post8 = Post(8,User(8,"hosein","hosein azimi", userCoverSample), captions, postCoverSample7,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post9 = Post(9,User(9,"kobra","kobra asqari", userCoverSample), captions, postCoverSample8,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post10 = Post(10,User(10,"hadi","hadi saei", userCoverSample), captions, postCoverSample9,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post11 = Post(11,User(11,"sina","sina saei", userCoverSample), captions, postCoverSample10,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post12 = Post(12,User(12,"sinaDLV","sina dalvand", userCoverSample), captions, postCoverSample11,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post13 = Post(13,User(13,"ehsan","ehsan khajeamiri", userCoverSample), captions, postCoverSample12,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post14 = Post(14,User(14,"homayun","hamyun shajariyan", userCoverSample), captions, postCoverSample13,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post15 = Post(15,User(15,"human","human seyedi", userCoverSample), captions, postCoverSample14,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post16 = Post(16,User(16,"mehran","mehran modiri", userCoverSample), captions, postCoverSample15,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post17 = Post(17,User(17,"2030","2030", userCoverSample), captions, postCoverSample16,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post18 = Post(18,User(18,"ahmad","ahmad pashaei", userCoverSample), captions, postCoverSample17,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post19 = Post(19,User(19,"narges","narges nezami", userCoverSample), captions, postCoverSample18,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post20 = Post(20,User(20,"mina","mina pulchi", userCoverSample), captions, postCoverSample19,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post21 = Post(21,User(21,"shamsi","shamsi shamsaei", userCoverSample), captions, postCoverSample20,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post22 = Post(22,User(22,"behzad","behzad motallebi", userCoverSample), captions, postCoverSample21,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post23 = Post(23,User(23,"alireza","alireza rabei", userCoverSample), captions, postCoverSample22,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post24 = Post(24,User(24,"shayan","shayan shirmahomad", userCoverSample), captions, postCoverSample23,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post25 = Post(25,User(25,"amirhosein","amirhosein pirmohamadi", userCoverSample), captions, postCoverSample24,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post26 = Post(26,User(26,"newsha","newsha hashemi", userCoverSample), captions, postCoverSample25,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post27 = Post(27,User(27,"taranom","tarannom hosseini", userCoverSample), captions, postCoverSample26,0,false, arrayListOf(),"2023/05/01","14:30 PM")
        val post28 = Post(28,User(28,"amirAli","amir ali aghei", userCoverSample), captions, postCoverSample27,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post29 = Post(29,User(29,"ali","ali agaei", userCoverSample), captions, postCoverSample28,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        val post30 = Post(30,User(30,"rahman","rahman azimi", userCoverSample), captions, postCoverSample30,0, false,arrayListOf(),"2023/05/01","14:30 PM")
        posts.add(post1)
        posts.add(post2)
        posts.add(post3)
        posts.add(post4)
        posts.add(post5)
        posts.add(post6)
        posts.add(post7)
        posts.add(post8)
        posts.add(post9)
        posts.add(post10)
        posts.add(post11)
        posts.add(post12)
        posts.add(post13)
        posts.add(post14)
        posts.add(post15)
        posts.add(post16)
        posts.add(post17)
        posts.add(post18)
        posts.add(post19)
        posts.add(post20)
        posts.add(post21)
        posts.add(post22)
        posts.add(post23)
        posts.add(post24)
        posts.add(post25)
        posts.add(post26)
        posts.add(post27)
        posts.add(post28)
        posts.add(post29)
        posts.add(post30)
        posts.forEach{
            RoomDB.database!!.postDao().insertPost(it)
        }
    }



    private fun generateFakeComments(){
        for (i in 1..10){
            val comment = Comment(
                i,
                User(i, "Hasan_$i" ,"$i" + "Hasan_$i", userCoverSample),
                "this is test comment",
                "",
                ""
                )
            comments.add(comment)
        }
    }
}