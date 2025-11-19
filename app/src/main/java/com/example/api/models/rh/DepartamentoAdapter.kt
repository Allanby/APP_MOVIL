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

class DepartamentoAdapter(
    private var items: List<EmployeeDepartamentResponse>
) : RecyclerView.Adapter<DepartamentoAdapter.DepartamentoViewHolder>() {
    private val cardColors: List<Int> = listOf(
        Color.parseColor("#303F9F"), // Azul
        Color.parseColor("#00796B"), // Verde azulado
        Color.parseColor("#C2185B"), // Rosa fuerte
        Color.parseColor("#F57C00"), // Naranja
        Color.parseColor("#512DA8"), // Morado
        Color.parseColor("#D32F2F")  // Rojo
    )

    class DepartamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val cardView: CardView = itemView.findViewById(R.id.cardTotalEmpleados)
        val nombreDepartamento: TextView = itemView.findViewById(R.id.textViewdepartamento)
        val TotalEmpleados: TextView = itemView.findViewById(R.id.texviewTotalEmpleadosDepartamento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartamentoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_departamento, parent, false)
        return DepartamentoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DepartamentoViewHolder, position: Int) {
        val item = items[position]

        holder.nombreDepartamento.text = item.department
        holder.TotalEmpleados.text = "${item.total}"

        val color = cardColors[position % cardColors.size]
        holder.cardView.setCardBackgroundColor(color)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateData(newItems: List<EmployeeDepartamentResponse>) {
        items = newItems
        notifyDataSetChanged()
    }
}