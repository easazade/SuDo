package ir.easazade.jesusnote.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.easazade.jesusnote.R
import ir.easazade.jesusnote.mvp.model.Task
import ir.easazade.jesusnote.view.adapters.TasksRecyclerAdapter.TaskVH

class TasksRecyclerAdapter(
  private val tasks: MutableList<Task>
) : RecyclerView.Adapter<TaskVH>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH =
    TaskVH(LayoutInflater.from(parent.context).inflate(R.layout.list_item_task, parent, false))

  override fun getItemCount(): Int = tasks.size

  override fun onBindViewHolder(holder: TaskVH, position: Int) {
    val item = tasks[position]
    holder.description.text = item.description
    holder.time.text = item.dateTime.getTimeAsString()
    holder.status.isChecked = item.status
  }

  class TaskVH(root: View) : RecyclerView.ViewHolder(root) {
    val time = root.findViewById<TextView>(R.id.listItemTask_time)
    val description = root.findViewById<TextView>(R.id.listItemTask_description)
    val status = root.findViewById<CheckBox>(R.id.listItemTask_status)
  }
}