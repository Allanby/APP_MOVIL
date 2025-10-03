// com/example/api/adapters/EmployeeAdapter.kt (o tu ubicación)
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
import com.example.api.models.rh.Employee // Importa tu modelo Employee
import com.example.api.models.rh.EmployeeDepartamentResponse
import com.example.api.models.rh.EmployeeTotalResponse

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Asegúrate que los IDs coincidan con tu item_employee.xml
    private val nameTextView: TextView = itemView.findViewById(R.id.textView_employee_name)

    @SuppressLint("SetTextI18n")
    fun bind(employee: EmployeeTotalResponse) {
        // Usa los nombres de campo de tu modelo Employee
        nameTextView.text = "Total de empleados: ${employee.total}"
    }

    companion object {
        fun create(parent: ViewGroup): EmployeeViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_employee, parent, false) // Referencia a item_employee.xml
            return EmployeeViewHolder(view)
        }
    }

}

class EmployeeAdapter : ListAdapter<EmployeeTotalResponse, EmployeeViewHolder>(EmployeeTotalResponseDiffCallback()) { // Cambiado aquí

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employeeTotal = getItem(position) // getItem() devolverá EmployeeTotalResponse
        holder.bind(employeeTotal) // Pasar EmployeeTotalResponse a bind
    }
}




// Cambiado a EmployeeTotalResponseDiffCallback y DiffUtil.ItemCallback<EmployeeTotalResponse>
class EmployeeTotalResponseDiffCallback : DiffUtil.ItemCallback<EmployeeTotalResponse>() {
    override fun areItemsTheSame(oldItem: EmployeeTotalResponse, newItem: EmployeeTotalResponse): Boolean {
        // Debes decidir cómo identificar si dos EmployeeTotalResponse son "el mismo item".
        // Si solo hay un objeto EmployeeTotalResponse que se actualiza, esto podría ser simple:
        return true // O compara por algún ID si EmployeeTotalResponse tuviera uno.
        // Si la lista siempre va a tener 0 o 1 item de este tipo, 'true' puede ser suficiente
        // si lo que importa es si el contenido cambió.
    }

    override fun areContentsTheSame(oldItem: EmployeeTotalResponse, newItem: EmployeeTotalResponse): Boolean {
        // Compara si el contenido de los objetos es el mismo.
        return oldItem == newItem // Si EmployeeTotalResponse es una data class, esto funciona bien.
        // Principalmente comparará el campo 'total'.
    }
}

