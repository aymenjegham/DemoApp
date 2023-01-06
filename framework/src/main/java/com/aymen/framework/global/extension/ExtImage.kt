package com.aymen.framework.global.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aymen.framework.global.utils.PicassoCircularTransformation
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

@BindingAdapter(value = ["imagePath", "holder"], requireAll = true)
fun setImageUri(imageView: ImageView, imagePath: String?, placeHolder: Drawable) {
    if (imagePath.isNullOrEmpty())
        imageView.setImageDrawable(placeHolder)
    else
        Picasso.get().load(imagePath).into(imageView)
}


@BindingAdapter(
    value = ["roundedImageUrl", "roundedImagePlaceholder", "picasso", "borderWidth", "borderColor"],
    requireAll = true
)
fun setRoundedImageUri(
    imageView: ImageView,
    imageUrl: String?,
    placeHolder: Drawable,
    picasso: Picasso,
    borderWidth: Float,
    borderColor: Int
) {
    setImage(
        imageView,
        imageUrl,
        placeHolder,
        picasso,
        PicassoCircularTransformation(
            borderColor,
            imageView.context.dpToPx(borderWidth)
        )
    )
}

private fun setImage(
    imageView: ImageView,
    imageUrl: String?,
    placeHolder: Drawable,
    picasso: Picasso,
    transformation: Transformation? = null
) {

    imageUrl?.let {
        if (imageUrl.isEmpty()) {
            imageView.setImageDrawable(placeHolder)
        } else {

            var rc = picasso.load(it).fit().placeholder(placeHolder)

            rc = when (imageView.scaleType) {
                ImageView.ScaleType.CENTER_CROP -> rc.centerCrop()
                ImageView.ScaleType.CENTER_INSIDE -> rc.centerInside()
                else -> rc
            }

            transformation?.let { rc.transform(transformation) }

            rc.into(imageView)
        }
    }

}
