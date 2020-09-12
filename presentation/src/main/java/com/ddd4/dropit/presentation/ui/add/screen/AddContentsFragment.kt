package com.ddd4.dropit.presentation.ui.add.screen

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ddd4.dropit.presentation.R
import com.ddd4.dropit.presentation.base.ui.BaseFragment
import com.ddd4.dropit.presentation.databinding.FragmentAddContentsBinding
import com.ddd4.dropit.presentation.ui.add.AddSharedViewModel
import com.ddd4.dropit.presentation.ui.capture.CaptureActivity
import com.ddd4.dropit.presentation.util.Constants
import com.ddd4.dropit.presentation.util.toast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AddContentsFragment : BaseFragment<FragmentAddContentsBinding>(R.layout.fragment_add_contents) {

    private val addSharedViewModel: AddSharedViewModel by activityViewModels()

    override fun setBind() {
        binding.apply {
            addVM = addSharedViewModel
        }
    }

    override fun setObserve() {
        addSharedViewModel.nextClick.observe(this, Observer {
            findNavController().navigate(AddContentsFragmentDirections.actionAddContentsFragmentToAddDateFragment())
        })
        addSharedViewModel.captureClick.observe(this, Observer {
            val intent = Intent(requireContext(), CaptureActivity::class.java)
            startActivityForResult(intent, Constants.INTENT_CODE_IMAGE)
        })
        addSharedViewModel.isPermissionState.observe(this, Observer { isEnabled ->
            if (!isEnabled) {
                requestPermissions(
                    Constants.PERMISSION_MANIFEST_CAPTURE.toTypedArray(),
                    Constants.PERMISSION_CODE_CAPTURE)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        addSharedViewModel.setProgressValue(66)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.INTENT_CODE_IMAGE && resultCode == RESULT_OK) {
            addSharedViewModel.setSelectedImage(data!!.extras!!["imagePath"].toString())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constants.PERMISSION_CODE_CAPTURE && permissions.size == Constants.PERMISSION_MANIFEST_CAPTURE.size) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                addSharedViewModel.captureClick.call()
            } else {
                requireContext().toast(getString(R.string.permission_denied))
            }
        }
    }
}