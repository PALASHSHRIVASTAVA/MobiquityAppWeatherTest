package com.palash.mobiquityappweathertest.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.palash.mobiquityappweathertest.R

fun initViewInGlide(imageView:ImageView,imgUrl:String)
{

        Glide.with(imageView)
        .load(imgUrl)
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
                .listener( object : RequestListener<Drawable> {
                        override fun onLoadFailed(p0: GlideException?, p1: Any?,  target: Target<Drawable>?, p3: Boolean): Boolean {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                        override fun onResourceReady(
                                p0: Drawable?,
                                p1: Any?,
                                target: Target<Drawable>?,
                                p3: DataSource?,
                                p4: Boolean
                        ): Boolean {
                                // Log.d(TAG, "OnResourceReady")
                                //do something when picture already loaded
                                return false
                        }
                })
        .into(imageView);

    /*
    *  .listener( object : RequestListener<Drawable> {
            override fun onLoadFailed(p0: GlideException?, p1: Any?,  target: Target<Drawable>?, p3: Boolean): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onResourceReady(
                p0: Drawable?,
                p1: Any?,
                target: Target<Drawable>?,
                p3: DataSource?,
                p4: Boolean
            ): Boolean {
               // Log.d(TAG, "OnResourceReady")
                //do something when picture already loaded
                return false
            }
        })
    *
    * */
}