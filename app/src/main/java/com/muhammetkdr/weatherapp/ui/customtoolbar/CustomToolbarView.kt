package com.muhammetkdr.weatherapp.ui.customtoolbar

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.databinding.CustomToolbarViewBinding


class CustomToolbarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    private val binding = CustomToolbarViewBinding
        .inflate(LayoutInflater.from(context), this, true)

    init {
        binding.root.id= View.generateViewId()
        context.obtainStyledAttributes(attributeSet, R.styleable.CustomToolbarView).apply {
            val title: String? = getString(R.styleable.CustomToolbarView_ctv_title)
            val showFindIcon = getBoolean(R.styleable.CustomToolbarView_ctv_showEndIcon, true)
            val showBackPressIcon = getBoolean(R.styleable.CustomToolbarView_ctv_showBackPressIcon, true)
            val textStyle = getInt(R.styleable.CustomToolbarView_ctv_textStyle, CustomToolbarViewTextStyle.Normal.type)

            recycle()
            updateTitle(title)
            showFindIcon(showFindIcon)
            showBackPressIcon(showBackPressIcon)
            updateTitleStyle(textStyle)
        }
    }

    private fun updateTitleStyle(type: Int) {
        val style = when (CustomToolbarViewTextStyle.fromValue(type)) {
            CustomToolbarViewTextStyle.Normal -> {
                Typeface.NORMAL
            }
            CustomToolbarViewTextStyle.Bold -> {
                Typeface.BOLD
            }
        }
        binding.tvTitle.setTypeface(null, style)
    }

    fun updateTitleStyle(style: CustomToolbarViewTextStyle) {
        updateTitleStyle(style.type)
    }

    fun updateTitle(title: String?) {
        binding.tvTitle.text = title
    }

    fun showBackPressIcon(isVisible: Boolean) {
        binding.imgBackPress.isVisible = isVisible
    }

    fun showFindIcon(isVisible: Boolean) {
        binding.imgSearchPress.isVisible = isVisible
    }

    fun onBackPressed(onClickListener: OnClickListener){
        binding.imgBackPress.setOnClickListener(onClickListener)
    }

    fun onSearchButtonPressed(onClickListener: OnClickListener){
        binding.imgSearchPress.setOnClickListener(onClickListener)
    }

}

enum class CustomToolbarViewTextStyle(val type: Int) {
    Normal(0),
    Bold(1);

    companion object {
        fun fromValue(type: Int): CustomToolbarViewTextStyle {
            return values().firstOrNull { it.type == type } ?: Normal
        }
    }
}