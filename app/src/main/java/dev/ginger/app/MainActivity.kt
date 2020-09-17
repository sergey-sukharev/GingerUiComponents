package dev.ginger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import dev.ginger.ui.components.dialog.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var dialogState = EditDialogState("no_state",inputType = InputType.TYPE_CLASS_NUMBER)

    var editDialog : GingerDialog? = null
    var toolbarState : DialogToolbarState = DialogToolbarState("State 1")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.frame_container, UserFragment())
            .commit()


    }

}