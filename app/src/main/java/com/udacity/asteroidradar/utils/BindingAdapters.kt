package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        val imageUri = url.toUri().buildUpon().scheme("https").build()
        Picasso.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .into(imageView)
    }
}

@BindingAdapter("asteroidStatusIcon")
fun ImageView.bindAsteroidStatusImage(item: Asteroid?) {
    item?.let {
        if (item.isPotentiallyHazardous) {
            setImageResource(R.drawable.ic_status_potentially_hazardous)
        } else {
            setImageResource(R.drawable.ic_status_normal)
        }
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
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

@BindingAdapter("asteroidCodenameString")
fun TextView.bindCodenameString(item: Asteroid?) {
    item?.let {
        text = it.codename
    }
}

@BindingAdapter("asteroidDateString")
fun TextView.bindDateString(item: Asteroid?) {
    item?.let {
        text = it.closeApproachDate
    }
}
