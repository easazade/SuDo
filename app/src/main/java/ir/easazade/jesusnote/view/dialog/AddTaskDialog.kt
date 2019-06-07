package ir.easazade.jesusnote.view.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.utils.currentDateTime

class AddTaskDialog() : DialogFragment() {

  private var onSaveButtonClicked: (() -> Unit)? = null
  private var root: View? = null
  private var dateTime = currentDateTime()

  companion object {
    const val TAG = "AddTaskDialog"

    fun newInstance(): DialogFragment {
      val frag = AddTaskDialog()
      return frag
    }
  }

  fun setOnSaveButtonClieckListener(listener: () -> Unit) {
    this.onSaveButtonClicked = listener
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val builder = AlertDialog.Builder(context!!)
    val inflater = LayoutInflater.from(context)
    if (root == null)
      root = inflater.inflate(R.layout.dialog_add_task, null)
    val saveBtn = root!!.findViewById<FloatingActionButton>(R.id.addTaskDialog_save)
    val inputDescription = root!!.findViewById<EditText>(R.id.addTaskDialog_description)
    val status = root!!.findViewById<TextView>(R.id.addTaskDialog_status)
    val changeStatus = root!!.findViewById<SwitchCompat>(R.id.addTaskDialog_changeStatus)
    val time = root!!.findViewById<TextView>(R.id.addTaskDialog_time)
    val date = root!!.findViewById<TextView>(R.id.addTaskDialog_date)

    time.text = dateTime.getTimeAsString()
    date.text = dateTime.getDateAsString()
    changeStatus.isChecked = false
    status.setTextColor(ContextCompat.getColor(activity!!, R.color.text_light))
    status.setOnClickListener { changeStatus.performClick() }
    changeStatus.setOnCheckedChangeListener { buttonView, isChecked ->
      if (isChecked)
        status.setTextColor(ContextCompat.getColor(activity!!, R.color.colorAccent))
      else
        status.setTextColor(ContextCompat.getColor(activity!!, R.color.text_light))
    }
    inputDescription.setText("")
    saveBtn.setOnClickListener {
      if (onSaveButtonClicked != null)
        onSaveButtonClicked?.invoke()
      if (dialog.isShowing)
        dialog.dismiss()
    }
    builder.setView(root)


    return builder.create().apply {
      window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
  }

  override fun onDismiss(dialog: DialogInterface?) {
    super.onDismiss(dialog)
    (root!!.parent as ViewGroup).removeView(root)
  }
}