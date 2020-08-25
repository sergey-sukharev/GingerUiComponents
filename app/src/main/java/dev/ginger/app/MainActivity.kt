package dev.ginger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import dev.ginger.ui.components.dialog.GingerBaseDialog
import dev.ginger.ui.components.utils.setCursorToEnd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
            val dlg = GingerBaseDialog.display(supportFragmentManager)
            dlg.getViews().subscribe {
                it?.let {
                    when (it.id) {
                        R.id.edit_field_label -> (it as TextView).text = "STARGERAS"
                        R.id.edit_field_input -> {
                            (it as EditText).apply {
                                hint = "My hint"
                                setText("My text")
                                setCursorToEnd()
                            }
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }
}