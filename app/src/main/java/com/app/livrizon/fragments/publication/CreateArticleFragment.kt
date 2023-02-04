package com.app.livrizon.fragments.publication

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.EditText
import androidx.core.text.toHtml
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentCreateArticleBinding
import com.app.livrizon.fragments.CustomFragment
import kotlin.math.min

class CreateArticleFragment : CustomFragment() {
    lateinit var binding: FragmentCreateArticleBinding
    lateinit var edDescription: EditText

    @SuppressLint("ResourceAsColor")
    override fun init() {
        binding.edTitle.setLinkTextColor(R.color.blue_1)
    }

    override fun initVariable() {
        binding = FragmentCreateArticleBinding.inflate(layoutInflater)
        edDescription = binding.edDescription
    }

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initButtons() {
        binding.btnBold.setOnClickListener {
            setBold()
        }
    }

    fun setBold() {
        val spannable = SpannableStringBuilder(edDescription.text)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            edDescription.selectionStart, min(1000, edDescription.text.length), 0
        )
        edDescription.text = spannable
        edDescription.setText(Html.fromHtml(spannable.toHtml(), Html.FROM_HTML_MODE_LEGACY))
    }
}