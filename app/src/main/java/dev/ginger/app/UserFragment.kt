package dev.ginger.app

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.ginger.ui.components.composite.TextDetailCell
import dev.ginger.ui.components.dialog.*
import dev.ginger.ui.components.dialog.popup.*
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment(), EditDialogProvider, DialogStateListener {

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

        gingerCompositeTextView.setOnClickListener {
//            val alert = AlertDialog.Builder().apply {
//                hasCloseIcon = true
//                titleText = "AS"
//                messageText = "Проверьте интернет-соединение и повторите попытку"
//                positiveButtonText = "Повторить"
//                negativeButtonText = "Отмена"
//                onStateListener = this@UserFragment
//            }.build()
//
//            alert.isCancelable = false
//            alert.show(childFragmentManager, "")

            val alert = RadioGroupDialog.Builder().apply {
                titleText = "Смена пола"
                items.add(RadioGroupDialog.Item("132", "Мужской"))
                items.add(RadioGroupDialog.Item("2423", "Женский"))
//                positiveButtonText = "SAVE"
                onStateListener = this@UserFragment
            }.build()

//            alert.isCancelable = false
            alert.show(childFragmentManager, "")


        }


        val popup = PopupMenuDialog.Builder().apply {
            items.add(PopupMenuDialog.Item("1", "Hello"))
            items.add(PopupMenuDialog.Item("1", "Hello"))
            items.add(
                PopupMenuDialog.Item(
                    "2", "Мужской",
                    resources.getDrawable(R.drawable.ic_ginger, null)
                )
            )
            titleText = "Выберите пол"
        }.build()

        popup.show(childFragmentManager, "HH")


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

    // TODO add dialog id or dialog fragment
    override fun onChangeState(state: DialogButtonState) {
        println(state.name)
    }

}