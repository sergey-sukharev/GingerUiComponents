package dev.ginger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.fragment.app.DialogFragment
import dev.ginger.ui.components.dialog.*
import dev.ginger.ui.components.utils.setCursorToEnd
import dev.ginger.ui.components.utils.showSoftKeyboard
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), EditDialogProvider {

    private val valueSubject = ReplaySubject.create<EditDialogState>()
    private val dialogSubject = ReplaySubject.create<DialogFragment>()
    private val toolbarStateSubject = ReplaySubject.create<DialogToolbarState>()

    val dialogState = EditDialogState()

    var editDialog : GingerEditDialogFragment? = null
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

        val ttt = "dsadasd"

        my_item3.setOnClickListener {
            editDialog = GingerEditDialogFragment.display(supportFragmentManager, this)
            editDialog?.show(supportFragmentManager, null)

            dialogState.text = "Elon"
            dialogState.helperText = "Write ur name please"
            dialogState.hint = "John Doe"

            valueSubject.onNext(dialogState)
            toolbarStateSubject.onNext(toolbarState)

            Thread {
                while (true) {
                    toolbarState.title = "Updating..."
                    toolbarStateSubject.onNext(toolbarState)
                    Thread.sleep(2000)
                    toolbarState.title = "Hello"
                    toolbarStateSubject.onNext(toolbarState)
                    Thread.sleep(2000)
                }
            }.start()
        }


    }

    override fun observeOnState(): Observable<EditDialogState> = valueSubject

    override fun postSave(value: String): Boolean {
        valueSubject.onNext(dialogState.apply { text = "SAVEEE" })
        if (value.isEmpty()) return false
        return true
    }

    override fun postDismiss(value: String): Boolean {
        if (value.isEmpty()) return false
        return true
    }

    override fun observeOnDialog(): Observable<DialogFragment> = dialogSubject

    override fun observeOnToolbar(): Observable<DialogToolbarState> = toolbarStateSubject
}