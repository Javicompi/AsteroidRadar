package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfTheDay
import com.udacity.asteroidradar.utils.dateToString

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, picture: PictureOfTheDay?) {
    picture?.let {
        val imageUri = picture.url.toUri().buildUpon().scheme("https").build()
        Picasso.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .into(imageView)
        val context = imageView.context
        imageView.contentDescription =
            String.format(context
                .getString(R.string.nasa_picture_of_day_content_description_format), picture.title)
    }
}

@BindingAdapter("asteroidStatusIcon")
fun ImageView.bindAsteroidStatusImage(item: Asteroid?) {
    item?.let {
        val context = this.context
        val contentDescription: String
        if (item.isPotentiallyHazardous) {
            setImageResource(R.drawable.ic_status_potentially_hazardous)
            contentDescription = context.getString(R.string.is_potentially_hazardous_image)
        } else {
            setImageResource(R.drawable.ic_status_normal)
            contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
        }
        this.contentDescription = contentDescription
    }
}

@BindingAdapter("asteroidStatusImage")
fun ImageView.bindDetailsStatusImage(isHazardous: Boolean) {
    val context = this.context
    val contentDescription: String
    if (isHazardous) {
        setImageResource(R.drawable.asteroid_hazardous)
        contentDescription = context.getString(R.string.is_potentially_hazardous_image)
    } else {
        setImageResource(R.drawable.asteroid_safe)
        contentDescription = context.getString(R.string.not_hazardous_asteroid_image)
    }
    this.contentDescription = contentDescription
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("viewVisibility")
fun setViewVisibility(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("approachDateString")
fun TextView.bindApproachDateString(item: Asteroid?) {
    item?.let {
        text = dateToString(it.closeApproachDate)
    }
}

@BindingAdapter("asteroidCodenameString")
fun TextView.bindCodenameString(item: Asteroid?) {
    item?.let {
        text = it.codename
    }
}
