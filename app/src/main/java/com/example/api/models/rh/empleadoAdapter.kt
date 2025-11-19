package com.example.api.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.adminrh.R // CAMBIA ESTO AL R de tu módulo de app (Admin_RH.app.main)
import com.example.api.models.rh.EmployeeTotalResponse

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.textView_employee_name)

    @SuppressLint("SetTextI18n")
    fun bind(employee: EmployeeTotalResponse) {
        nameTextView.text = "Total de empleados: ${employee.total}"
    }

    companion object {
        fun create(parent: ViewGroup): EmployeeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_employee, parent, false)
            return EmployeeViewHolder(view)
        }
    }

}

class EmployeeAdapter : ListAdapter<EmployeeTotalResponse, EmployeeViewHolder>(EmployeeTotalResponseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employeeTotal = getItem(position)
        holder.bind(employeeTotal)
    }
}

class EmployeeTotalResponseDiffCallback : DiffUtil.ItemCallback<EmployeeTotalResponse>() {
    override fun areItemsTheSame(oldItem: EmployeeTotalResponse, newItem: EmployeeTotalResponse): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: EmployeeTotalResponse, newItem: EmployeeTotalResponse): Boolean {
        return oldItem == newItem
    }
}

