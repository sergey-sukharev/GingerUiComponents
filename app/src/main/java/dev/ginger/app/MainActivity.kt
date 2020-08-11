package dev.ginger.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myItem = my_item.getActionView()

        if (myItem is CheckBox) {
            myItem.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
                    buttonView, isChecked ->
                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            })
        }

        if (myItem is Switch) {
            myItem.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener {
                    buttonView, isChecked ->
                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            })
        }
    }
}