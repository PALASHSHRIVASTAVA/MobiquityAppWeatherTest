package com.palash.mobiquityappweathertest.utils

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions



@GlideModule
class GlideAppModule : AppGlideModule() {


    /*override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.apply { RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).signature(ObjectKey(System.currentTimeMillis().toShort())) }
    }*/
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // Default empty impl.
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
    }
}