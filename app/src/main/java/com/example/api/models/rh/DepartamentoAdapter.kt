package com.example.api.models.rh

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.adminrh.R

// El adaptador recibe una lista de EmployeeDepartamentResponse
class DepartamentoAdapter(
    private var items: List<EmployeeDepartamentResponse>
) : RecyclerView.Adapter<DepartamentoAdapter.DepartamentoViewHolder>() {
    private val cardColors: List<Int> = listOf(
        Color.parseColor("#303F9F"), // Azul oscuro (el que ya tienes)
        Color.parseColor("#00796B"), // Verde azulado
        Color.parseColor("#C2185B"), // Rosa fuerte
        Color.parseColor("#F57C00"), // Naranja
        Color.parseColor("#512DA8"), // Morado
        Color.parseColor("#D32F2F")  // Rojo
    )

    // El ViewHolder contiene las referencias a las vistas de cada elemento de la lista.
    // Debes crear un layout (ej. item_departamento.xml) con estos TextViews.
    class DepartamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Asume que tu layout item_departamento.xml tiene estos IDs
       val cardView: CardView = itemView.findViewById(R.id.cardTotalEmpleados)
        val nombreDepartamento: TextView = itemView.findViewById(R.id.textViewdepartamento)
        val TotalEmpleados: TextView = itemView.findViewById(R.id.texviewTotalEmpleadosDepartamento)
    }

    // Se llama cuando RecyclerView necesita un nuevo ViewHolder.
    // Infla el layout XML para cada elemento de la lista.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartamentoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_departamento, parent, false) // Asegúrate de crear este layout
        return DepartamentoViewHolder(view)
    }

    // Se llama para mostrar los datos en la posición especificada.
    // Vincula los datos del objeto EmployeeDepartamentResponse con las vistas del ViewHolder.
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DepartamentoViewHolder, position: Int) {
        val item = items[position]

        // Asigna los datos del objeto 'item' a los TextViews del ViewHolder
        holder.nombreDepartamento.text = item.department
        holder.TotalEmpleados.text = "${item.total}"

        // Añade más campos si los necesitas (ej. item.id, etc.)
        val color = cardColors[position % cardColors.size]
        holder.cardView.setCardBackgroundColor(color)
    }

    // Devuelve el número total de elementos en la lista.
    override fun getItemCount(): Int {
        return items.size
    }

    // Función para actualizar la lista de datos en el adaptador de forma segura.
    fun updateData(newItems: List<EmployeeDepartamentResponse>) {
        items = newItems
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }
}