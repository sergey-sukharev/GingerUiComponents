package dev.ginger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.components.dialog.*
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), EditDialogProvider {

    var dialogState = EditDialogState("no_state",inputType = InputType.TYPE_CLASS_NUMBER)

    var editDialog : GingerDialog? = null
    var toolbarState : DialogToolbarState = DialogToolbarState("State 1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myItem = my_item.getActionView()

        if (myItem is CheckBox) {
            myItem.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            })
        }

        if (myItem is Switch) {
            myItem.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            })
        }

        my_item2.setOnClickListener {
            println()
        }


        my_item3.setOnClickListener {
            editDialog = GingerEditDialogFragment.create(supportFragmentManager, this)
            editDialog?.show()

            dialogState = EditDialogState(UUID.randomUUID().toString(),inputType = InputType.TYPE_CLASS_TEXT)
            dialogState.text = my_item3.getTitleText()
            dialogState.helperText = my_item3.getSubtitleText()
            dialogState.hint = "John Doe"

//            valueSubject.onNext(EditDialogState("dialog", "Hi"))

            (editDialog as GingerEditDialog).setState(dialogState)
            (editDialog as GingerEditDialog).setToolbarState(DialogToolbarState("Edit username",
                style = R.style.GingerTheme_GingerToolbar))

        }
    }

    override fun postSave(value: EditDialogState): Boolean {
        my_item3.setTitleText(value.text)
        if (value.text.isEmpty()) return false
        return true
    }

    override fun postDismiss(value: EditDialogState): Boolean {
        if (value.text.isEmpty()) return false
        return true
    }

    override fun postChangedValue(value: EditDialogState) {
        println(value.text)
        if (value.text.isEmpty()) {
            value.hint = "This field is required"
        }
        else
            value.hint = null

        (editDialog as GingerEditDialog).setState(value)
    }

}