package com.tp.travelpakistan.ui.dialog_rate

import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.animation.addListener
import com.tp.travelpakistan.R
import com.tp.travelpakistan.databinding.LayoutRatingDialogBinding


class RateUsDialog(
    context: Context,
    val onDismissDialog: () -> Unit,
    val onRateUsDialog: (Float) -> Unit,
) : Dialog(context) {

    companion object {
        private val uiModels = listOf(
            RateUsUiModel(0, R.drawable.ic_emoji_sad),
            RateUsUiModel(1, R.drawable.ic_emoji_sad),
            RateUsUiModel(2, R.drawable.ic_emoji_confused),
            RateUsUiModel(3, R.drawable.ic_emoji_depressed),
            RateUsUiModel(4, R.drawable.ic_emoji_happy),
            RateUsUiModel(5, R.drawable.ic_emoji_in_love, R.string.rate_on_playstore),
        )
    }

    private var binding: LayoutRatingDialogBinding? = null
    private var currentRating = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(
            LayoutRatingDialogBinding.inflate(layoutInflater).apply { binding = this }.root
        )
        setCancelable(true)

        binding?.apply {
            setSmiley(currentRating)
            ratingBar.setOnRatingChangeListener { _, rating, _ ->
                if (rating<=0){
                    rateBtn.isEnabled = false
                    rateBtn.setTextColor(context.resources.getColor(R.color.grey_level_6,null))
                }else{
                    rateBtn.isEnabled = true
                    rateBtn.setTextColor(context.resources.getColor(R.color.white,null))
                }
                currentRating = rating
                setSmiley(currentRating)
            }
            btnNotNow.setOnClickListener {
                onDismissDialog()
                dismiss()
            }
            rateBtn.setOnClickListener {
                onRateUsDialog(currentRating)
                dismiss()
            }

            ratingBar.post {
                ValueAnimator.ofInt(0, ratingBar.numStars+1).apply {
                    addUpdateListener {
                        ratingBar.rating = (it.animatedValue as Int).toFloat()
                    }
                    addListener(onEnd = {
                        ratingBar.rating = 0f
                        smileyImageView.setImageResource(R.drawable.ic_emoji_happy)
                    })
                    duration = 1500
                    start()
                }
            }
        }
    }

    private fun setSmiley(rating: Float) {
        for (uiModel in uiModels) {
            if (rating <= uiModel.rating) {
                binding?.apply {
                    smileyImageView.setImageResource(uiModel.image)
                    rateBtn.text = context.getText(uiModel.text)
                }
                break
            }
        }
    }

    private data class RateUsUiModel(
        val rating: Int,
        @DrawableRes val image: Int,
        @StringRes val text: Int = R.string.sure
    )
}