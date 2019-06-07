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
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.easazade.jesusnote.R

class AddTaskDialog() : DialogFragment() {

  private var onSaveButtonClicked: (() -> Unit)? = null
  private var root: View? = null

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