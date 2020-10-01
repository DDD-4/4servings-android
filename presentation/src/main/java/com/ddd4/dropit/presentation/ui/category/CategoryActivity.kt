package com.ddd4.dropit.presentation.ui.category

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityCategoryBinding
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.detailfolder.FolderItemDetailActivity
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderActivity
import com.ddd4.dropit.presentation.util.Constants
import com.ddd4.dropit.presentation.util.hideButton
import com.ddd4.dropit.presentation.util.showButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : BaseActivity<ActivityCategoryBinding>(R.layout.activity_category) {

    private val viewModel: CategoryViewModel by viewModels()
    private var categoryId = -1L


    override fun setBind() {
        binding.apply{
            categoryVM = viewModel
        }
        categoryId = getId(intent)
    }

    override fun setObserve() {

        viewModel.floatingButton.observe(this, Observer {
            startActivity(Intent(this, AddActivity::class.java))
            overridePendingTransition(R.anim.slide_down, R.anim.slide_up)
        })

        viewModel.nextButton.observe(this, Observer {
            startActivity(Intent(this, MoveFolderActivity::class.java))
        })

        viewModel.item.observe(this, Observer { item ->
            startActivity(Intent(this, FolderItemDetailActivity::class.java)
                .putExtra(Constants.EXTRA_NAME_ITEM_ID, item))
        })

        viewModel.backButton.observe(this, Observer {
            finish()
        })

        viewModel.selectImageMode.observe(this, Observer {
            when(it) {
                true -> {
                    binding.folderRectangleButton.visibility = View.GONE
                    binding.folderFloatingButton.showButton()
                    binding.folderRectangleButton.hideButton()
                }
                false -> {
                    binding.folderRectangleButton.visibility = View.VISIBLE
                    binding.folderFloatingButton.hideButton()
                    binding.folderRectangleButton.showButton()
                }
            }
        })
    }

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_CATEGORY_ID, -1)
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(categoryId)
        binding.rvDetailFolder.adapter?.notifyDataSetChanged()
    }
}