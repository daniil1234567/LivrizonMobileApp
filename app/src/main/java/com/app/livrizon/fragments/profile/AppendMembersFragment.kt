package com.app.livrizon.fragments.profile

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.R
import com.app.livrizon.adapter.CustomViewHolder
import com.app.livrizon.adapter.ProfileAdapter
import com.app.livrizon.databinding.FragmentAppendMembersBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.log
import com.app.livrizon.function.toDp
import com.app.livrizon.impl.Base
import com.app.livrizon.model.edit.profile.save.TeamSave
import com.app.livrizon.model.profile.Append
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.response.Response
import com.app.livrizon.request.*
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token
import kotlinx.android.synthetic.main.item_append_profile_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class AppendMembersFragment : CustomFragment() {
    lateinit var save: TeamSave
    lateinit var binding: FragmentAppendMembersBinding
    lateinit var chooseRecyclerView: RecyclerView
    lateinit var chooseAdapter: ProfileAdapter

    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.choose.value = null
    }

    override fun initListener() {
        viewModel.choose.observe(this) {
            if (it != null) {
                if (save.members.size == 0) {
                    binding.tvNoBodyChoosen.visibility =
                        View.VISIBLE
                    binding.btnCreate.animate().translationY(100f.toDp(requireContext())).duration =
                        200
                } else {
                    binding.tvNoBodyChoosen.visibility = View.GONE
                    binding.btnCreate.animate().translationY(0f.toDp(requireContext())).duration =
                        200
                }
            }
        }
    }

    override fun initAdapter() {
        chooseAdapter = object : ProfileAdapter(requireContext()) {
            override fun setButton(holder: CustomViewHolder, current: Base) {

            }

            override fun onButtonClick(holder: CustomViewHolder, current: Base) {
                current as ProfileBase
                removeItem {
                    it.equals() == current.equals()
                }
                viewModel.choose.value = recyclerViewAdapter.list.find {
                    it.id() == current.id()
                }?.id() ?: 0
            }
        }
        recyclerViewAdapter = object : ProfileAdapter(requireContext()) {
            override fun onBodyShortClick(holder: CustomViewHolder, current: Base, position: Int) {
                val append = list[position] as Append
                if (!append.choose) {
                    chooseAdapter.addListToBottom(ProfileBase(append))
                    save.members.add(append.id())
                } else {
                    save.members.remove(append.id())
                    chooseAdapter.removeItem {
                        it.id() == append.id()
                    }
                }
                viewModel.choose.value = append.id()
            }

            override fun setButton(holder: CustomViewHolder, current: Base) {

            }

            override fun setBody(
                holder: CustomViewHolder,
                position: Int,
                previous: Base?,
                current: Base,
                next: Base?
            ) {
                previous as Append?
                with(current as Append) {
                    with(holder.itemView) {
                        viewModel.choose.observe(this@AppendMembersFragment) {
                            if (it == id()) {
                                choose = !choose
                                if (choose) img_choose.visibility = View.VISIBLE
                                else img_choose.visibility = View.GONE
                            }
                        }
                        if (choose) img_choose.visibility = View.VISIBLE
                        else img_choose.visibility = View.GONE
                        if (position == 0) {
                            tv_header.visibility = View.VISIBLE
                            tv_header.text = context.getString(R.string.Important)
                        } else if (important) tv_header.visibility = View.GONE
                        else if (previous != null) {
                            if (previous.important != important || previous.name[0] != name[0]) {
                                tv_header.visibility = View.VISIBLE
                                tv_header.text = name[0].toString()
                            } else tv_header.visibility = View.GONE
                        }
                    }
                }
            }

        }
    }

    override fun initButtons() {
        binding.btnCreate.setOnClickListener {
            log(save.members, save)
            object : HttpListener(requireContext()) {
                override suspend fun body(block: CoroutineScope): Response {
                    return TeamRequest.saveTeam(save)
                }

                override fun onSuccess(item: Any?) {
                    //item as Chat
                    //requireContext().startActivity(
                    //    Intent(context, ChatActivity::class.java)
                    //        .putExtra(Parameters.profile, item.profile)
                    //        .putExtra(Parameters.message, item.message)
                    //)
                    requireActivity().finish()
                }
            }.request()
        }
    }

    override fun request() {
        initRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Array<Append> {
                val list = mutableListOf<Append>()
                val important = withContext(block.coroutineContext) {
                    InitRequest.profiles(
                        Selection.connections,
                        token.id(),
                        Filter.append,
                        false,
                        Sort.important,
                        5,
                    ) as Array<Append>
                }
                important.map { it.important=true }
                val profiles = withContext(block.coroutineContext) {
                    InitRequest.profiles(
                        Selection.connections,
                        token.id(),
                        Filter.append,
                        false,
                        Sort.name,
                        25,
                    ) as Array<Append>
                }
                list.addAll(important)
                list.addAll(profiles)
                return list.toTypedArray()
            }

            override fun onSuccess(item: Any?) {
                item as Array<Append>
                recyclerViewAdapter.initList(*item)
            }
        }
    }

    override fun initVariable() {
        binding = FragmentAppendMembersBinding.inflate(layoutInflater)
        save = requireArguments().getSerializable(Parameters.save) as TeamSave
        recyclerView = binding.rvProfile
        chooseRecyclerView = binding.rvChoose
    }


    override fun init() {
        recyclerView.adapter = recyclerViewAdapter
        chooseRecyclerView.adapter = chooseAdapter
    }
}