package dev.ginger.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ginger.ui.components.composite.TextDetailCell
import dev.ginger.ui.components.dialog.*
import dev.ginger.ui.components.dialog.factory.AlertDialog


class UserFragment: Fragment(), EditDialogProvider {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("user_fragment", "ViewCreated")
        val ss = TextDetailCell(context)
        ss.setTextAndValue("DSS", "SDS", false)


//        val a = GingerDialogA.Builder(childFragmentManager, "S")
//            .setTitle("HELLO")
//            .enableCloseIcon(true)
//            .build()
//            .show(childFragmentManager, "asds")


        val alert = AlertDialog.Builder().apply {
            titleText = "Ошибка"
            messageText = "Проверьте интернет-соединение и повторите попытку"
            positiveButtonText = "Повторить"
            negativeButtonText = "Отмена"
        }.build()

        alert.isCancelable = false
        alert.show(childFragmentManager, "")


//        val d = GingerDialog(childFragmentManager).get(GingerEditDialog::class.java)
//        d.show()


//        GingerRadioDialogFragment()
//                .show(childFragmentManager, "SS")
//        cont.addView(ss)
    }

    override fun postChangedValue(state: EditDialogState) {
        TODO("Not yet implemented")
    }

    override fun postSave(state: EditDialogState): Boolean {
        TODO("Not yet implemented")
    }

    override fun postDismiss(state: EditDialogState): Boolean {
        TODO("Not yet implemented")
    }

}