// com/example/adminrh/EmpleadosRH.kt (o tu ubicación)
package com.example.adminrh

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.adapters.EmployeeAdapter // Importa tu EmployeeAdapter
import com.example.api.departamentosViewModel
import com.example.api.empleadosViewModel // Importa tu ViewModel
import com.example.api.models.DepartamentoAdapter
import com.example.api.models.rh.EmployeeDepartamentResponse
import com.example.api.models.rh.EmployeeTotalResponse

class EmpleadosRH : Fragment() {

    private val viewModel: empleadosViewModel by viewModels()
    private val viewModelDepartamentos: departamentosViewModel by viewModels()
    //private lateinit var employeeAdapter: EmployeeAdapter // Adapter para el RecyclerView
   // private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var totalEmpleadosTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DepartamentoAdapter // Adapter para el RecyclerView
    // Para mostrar cuando la lista está vacía

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_empleados_r_h, container, false) // Tu layout con RecyclerView

       // recyclerView = view.findViewById(R.id.recyclerView_empleados) // ID del RecyclerView en tu XML
        progressBar = view.findViewById(R.id.progress) // ID del ProgressBar en tu XML
        // ID del TextView de lista vacía
        totalEmpleadosTextView = view.findViewById(R.id.totalEmpleados)
        recyclerView = view.findViewById(R.id.recyclerDepartamentos)

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.load()
        // ASIGNAR el valor del LiveData al TextView
        viewModel.totalEmpleadosActivos.observe(viewLifecycleOwner, Observer { totalEmpleados ->
            totalEmpleadosTextView.text = "Total de empleados: $totalEmpleados"
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        // Cargar departamentos
        viewModelDepartamentos.load()
        // Observar cambios en la lista de empleados// Cargar departamentos
        viewModelDepartamentos.totalEmpleadosDepartamento.observe(viewLifecycleOwner, Observer { departamentos ->
            // asignar departamentos a adapter
            adapter.updateData(departamentos)
            // Mostrar mensaje si la lista está vacía
            if (departamentos.isEmpty()) {
                println("Lista de departamentos está vacía")
            } else {
                println("Lista de departamentos no está vacía")
            }
        }
        )
        viewModelDepartamentos.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        )
    }
    private fun setupRecyclerView() {
        // Inicializa el adaptador con una lista vacía al principio
        adapter = DepartamentoAdapter(emptyList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            // Solución: Asigna la variable 'adapter' de la clase al 'adapter' del RecyclerView.
            // 'this@EmpleadosRH.adapter' se refiere a la variable de la clase.
            // 'adapter = ...' se refiere a la propiedad del RecyclerView.
            adapter = this@EmpleadosRH.adapter
        }
    }
}
