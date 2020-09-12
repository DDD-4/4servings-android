package com.ddd4.dropit.presentation.ui.category

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseActivity
import com.ddd4.dropit.presentation.databinding.ActivityCategoryBinding
import com.ddd4.dropit.presentation.ui.add.AddActivity
import com.ddd4.dropit.presentation.ui.detailFolder.FolderItemDetailActivity
import com.ddd4.dropit.presentation.ui.folder.FolderAdapter
import com.ddd4.dropit.presentation.ui.folder.FolderViewModel
import com.ddd4.dropit.presentation.ui.moveFolder.MoveFolderActivity
import com.ddd4.dropit.presentation.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CategoryActivity : BaseActivity<ActivityCategoryBinding>(R.layout.activity_category) {

    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var listAdapter: CategoryAdapter
    private var categoryId = -1L


    override fun setBind() {
        binding.apply{
            categoryVM = viewModel
        }
        categoryId = getId(intent)
        setupAdapter()
    }

    override fun setObserve() {

        viewModel.sortByLatestButton.observe(this, Observer {
            Timber.e("sort by latest button clicked")
        })

        viewModel.sortByExpirationButton.observe(this, Observer {
            Timber.e("sort by expiration button clicked")
        })

        viewModel.floatingButton.observe(this, Observer {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        })

        viewModel.nextButton.observe(this, Observer {
            Toast.makeText(this, "${it.size}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MoveFolderActivity::class.java)
            startActivity(intent)
        })

        viewModel.item.observe(this, Observer { item ->
            startActivity(Intent(this, FolderItemDetailActivity::class.java)
                .putExtra(Constants.EXTRA_NAME_ITEM_ID, item))
        })

        viewModel.backButton.observe(this, Observer {
            finish()
        })

        viewModel.selectImageButton.observe(this, Observer {
            binding.ibSelectImage.text = it
            when(it) {
                resources.getString(R.string.select) -> {
                    binding.folderFloatingButton.animate().translationY(0f)
                    binding.folderRectangleButton.animate().translationY(300f)
                }
                resources.getString(R.string.cancel) -> {
                    binding.folderFloatingButton.animate().translationY(300f)
                    binding.folderRectangleButton.animate().translationY(0f)
                }
            }
        })
    }

    private fun getId(intent: Intent): Long {
        return intent.getLongExtra(Constants.EXTRA_NAME_CATEGORY_ID, -1)
    }

    //TODO FIX
    private fun setupAdapter() {
        listAdapter = CategoryAdapter(viewModel, viewModel.onItemClickListener)
        binding.rvDetailFolder.layoutManager = GridLayoutManager(this, 3)
        binding.rvDetailFolder.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(categoryId)
        listAdapter.notifyDataSetChanged()
    }
}