package dev.ginger.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import dev.ginger.ui.dialogs.DialogState
import dev.ginger.ui.dialogs.alert.AlertDialog
import dev.ginger.ui.dialogs.fullscreen.PopupDialogFragment
import dev.ginger.ui.dialogs.popup.*
import dev.ginger.ui.dialogs.radio.RadioDialogListener
import dev.ginger.ui.dialogs.radio.RadioGroupDialog
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment(), OnStateListener, RadioDialogListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        showEditDialog()
        show_radio_dialog.setOnClickListener {
//            showRadioDialog()
            showEditDialog()
        }
//        showMessageDialog()
    }


    private fun showMessageDialog() {
        val dll = AlertDialog.Builder().apply {
            title = "Clean code"
            message = "Мы всегда за чистый ко д. Мы всегда за чистый код.Мы всегда за чистый код.Мы всегда за чистый код.Мы всегда за чистый код.Мы всегда за чистый код.Мы всегда за чистый код.Мы всегда за чистый код.Мы всегда за чистый код."
            enableCloseIcon = true
        }.build()

        dll.isCancelable = false
        dll.show(childFragmentManager, "S")
    }

    private fun showEditDialog() {
        val sas = PopupDialogFragment.Builder().apply {
            fragment = EditTextFragment()
        }.build()

        sas.show(childFragmentManager, "SAD")
    }

    private fun showRadioDialog() {
        val gld = RadioGroupDialog.Builder().apply {
            items.add(RadioGroupDialog.Item("S", "R"))
            items.add(RadioGroupDialog.Item("2", "A"))
            checkedId = "S"
        }.build()

        gld.show(childFragmentManager, "ss")
    }

    override fun onChangeState(dialog: DialogFragment, state: DialogState) {
        if (dialog.tag == "S" || state == DialogState.ON_POSITIVE_CLICK)
            dialog.dismiss()
    }

    override fun onChecked(dialog: RadioGroupDialog, id: String) {
        Toast.makeText(requireContext(), "ID $id", Toast.LENGTH_SHORT).show()
    }

}