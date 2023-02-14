package com.app.livrizon.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.app.livrizon.R
import com.app.livrizon.bottom_sheet.AppendBottomSheet
import com.app.livrizon.databinding.ActivityMainBinding
import com.app.livrizon.fragments.ContainerFragment
import com.app.livrizon.function.*
import com.app.livrizon.model.publication.Post
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.token
import com.app.livrizon.security.token.AccessToken
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var createBottomSheet: BottomSheetDialogFragment
    lateinit var posts: Array<Post>
    val fragments: Array<Fragment> = arrayOf(
        ContainerFragment(home),
        ContainerFragment(category),
        ContainerFragment(wall)
    )
    private var currentSelectItemId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        lightStatusBar(window)
        createBottomSheet = AppendBottomSheet()
        posts = intent.getSerializableExtra(Parameters.posts) as Array<Post>
        with(token as AccessToken){
            loadAvatar(
                this.name,
                binding.tvImage,
                binding.imgAvatar,
                this.avatar
            )
        }

        bottomNavigation()
    }

    private fun bottomNavigation() {
        swapFragments(home)
        binding.btnHome.setOnClickListener {
            swapFragments(home)
        }
        binding.btnCategory.setOnClickListener {
            swapFragments(category)
        }
        binding.btnCreate.setOnClickListener {
            val fragment =
                supportFragmentManager.findFragmentById(binding.frame.id) as ContainerFragment
            val nav = fragment.binding.frame.findNavController()
            nav.navigate(R.id.appendBottomSheet)
        }
        binding.btnMessenger.setOnClickListener {
            initSecondaryActivity(this) {
                it.putExtra(Parameters.key, SecondaryActivity.MESSENGER)
            }
        }
        binding.btnProfile.setOnClickListener {
            swapFragments(wall)
        }
    }

    private fun swapFragments(key: Int) {
        if (key != currentSelectItemId) {
            savedFragmentState(key)
            createFragment(key)
        }
    }

    private fun createFragment(actionId: Int) {
        this.replace(R.id.frame, fragments[actionId])
    }

    private fun savedFragmentState(key: Int) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame)
        if (currentFragment != null) fragments[currentSelectItemId!!] = currentFragment
        currentSelectItemId = key
    }

    override fun onBackPressed() {
        val fragment =
            supportFragmentManager.findFragmentById(binding.frame.id) as ContainerFragment
        val nav = fragment.binding.frame.findNavController()
        if (!nav.popBackStack())
            super.onBackPressed()
    }

    companion object {
        const val home = 0
        const val category = 1
        const val wall = 2
    }
}