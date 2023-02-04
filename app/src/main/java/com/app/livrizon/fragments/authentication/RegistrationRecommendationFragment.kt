package com.app.livrizon.fragments.authentication

import android.view.View
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentRegistrationRecommendationBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.homeRequest
import com.app.livrizon.impl.Base
import com.app.livrizon.model.profile.Recommendation
import com.app.livrizon.model.response.Response
import com.app.livrizon.request.HttpListener
import com.app.livrizon.request.ProfileRequest
import com.app.livrizon.values.Parameters
import kotlinx.android.synthetic.main.item_profile_base_layout.view.btn_action
import kotlinx.android.synthetic.main.item_profile_layout.view.*


class RegistrationRecommendationFragment : CustomFragment() {
    lateinit var binding: FragmentRegistrationRecommendationBinding
    lateinit var profiles: Array<Recommendation>
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentRegistrationRecommendationBinding.inflate(layoutInflater)
        profiles = requireArguments().getSerializable(Parameters.profile) as Array<Recommendation>
        recyclerView = binding.rvProfile
    }

    override fun request() {
        httpListener = homeRequest(requireActivity())

    }

    override fun initAdapter() {
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                val profile = list[position] as Recommendation
                with(holder.itemView) {
                    if (profile.my_sub) {
                        tv_button.text = "Отписаться"
                        tv_button.setTextColor(resources.getColor(R.color.grey))
                        btn_action.setBackgroundResource(R.drawable.button_black_r8_without_s)
                    } else {
                        tv_button.text = "Подписаться"
                        tv_button.setTextColor(resources.getColor(R.color.black))
                        holder.itemView.btn_action.setBackgroundResource(R.drawable.button_light_r8_without_s)
                    }
                }
            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base, position: Int) {
                current as Recommendation
                val my_sub=current.my_sub
                object : HttpListener(requireContext()) {
                    override suspend fun body(): Response {
                        return ProfileRequest.sub(
                            current.profile_id
                        )
                    }

                    override fun onSuccess(item: Any?) {
                        current.my_sub = !my_sub
                        with(holder.itemView) {
                            if (my_sub) {
                                tv_button.text = "Отписаться"
                                tv_button.setTextColor(resources.getColor(R.color.white))
                                btn_action.setBackgroundResource(R.drawable.button_black_r8_without_s)
                            } else {
                                tv_button.text = "Подписаться"
                                tv_button.setTextColor(resources.getColor(R.color.grey))
                                holder.itemView.btn_action.setBackgroundResource(R.drawable.button_light_r8_without_s)
                            }
                        }
                    }
                }.request()
            }
        }
    }

    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.initList(*profiles)
    }

    override fun initButtons() {
        binding.btnNext.setOnClickListener {
            httpListener.request()
        }
    }
}