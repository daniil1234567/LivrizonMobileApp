package com.app.livrizon.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.livrizon.R
import com.app.livrizon.activities.SecondaryActivity.Companion.CREATE_ARTICLE
import com.app.livrizon.activities.SecondaryActivity.Companion.CREATE_POST
import com.app.livrizon.adapter.AppendPublicationAdapter
import com.app.livrizon.databinding.BottomSheetAppendPublicationBinding
import com.app.livrizon.function.initSecondaryActivity
import com.app.livrizon.values.Parameters
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AppendBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetAppendPublicationBinding
    lateinit var adapter: AppendPublicationAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var navController: NavController
    //val items = mutableListOf<AppendPublication>()
    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    binding = BottomSheetAppendPublicationBinding.inflate(layoutInflater)
    //    navController = findNavController()
    //    adapter = object : AppendPublicationAdapter(requireContext()) {
    //        override fun onClick(item: AppendPublication) {
    //            navController.popBackStack()
    //            initSecondaryActivity(requireContext()) {
    //                it.putExtra(Parameters.key, item.key)
    //            }
    //        }
//
    //    }
    //    recyclerView = binding.rvItems
    //    recyclerView.adapter = adapter
    //    items.add(AppendPublication("Новая запись", R.drawable.ic_image, CREATE_POST))
    //    items.add(AppendPublication("Добавить услугу", R.drawable.ic_edit, CREATE_POST))
    //    items.add(AppendPublication("Расказать о событие", R.drawable.ic_map, CREATE_POST))
    //    items.add(AppendPublication("Опубликовать статью", R.drawable.ic_new, CREATE_ARTICLE))
    //    items.add(AppendPublication("Разместить вакансию", R.drawable.ic_vacancy, CREATE_POST))
    //    adapter.setList(*items.toTypedArray())
    //}
//
    //override fun onCreateView(
    //    inflater: LayoutInflater, container: ViewGroup?,
    //    savedInstanceState: Bundle?
    //): View {
    //    return binding.root
    //}
//
}//