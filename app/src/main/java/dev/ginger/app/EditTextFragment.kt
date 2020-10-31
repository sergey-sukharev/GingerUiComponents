package dev.ginger.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import dev.ginger.ui.dialogs.DialogState
import dev.ginger.ui.dialogs.popup.OnStateListener
import dev.ginger.ui.dialogs.progress.ProgressDialog

class EditTextFragment : Fragment(), OnStateListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ginger_edit_dialog_template, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Toolbar?>(R.id.toolbar)?.apply {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                val dlg = ProgressDialog.Builder().apply {
                    delay = 300
                    message = "Обновление данных"
                }.build()

                dlg.show(childFragmentManager, "A")

//                    dlg.dismiss()

            }
        }
    }

    override fun onChangeState(dialog: DialogFragment, state: DialogState) {
        if (state == DialogState.ON_POSITIVE_CLICK) {
            dialog.dismiss()
            fragmentManager?.popBackStack()
        } else {
            dialog.dismiss()
        }
    }

}