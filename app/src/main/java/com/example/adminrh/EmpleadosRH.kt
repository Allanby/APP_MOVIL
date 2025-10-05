// com/example/adminrh/EmpleadosRH.kt (o tu ubicación)
package com.example.adminrh

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.GenderViewModel
import com.example.api.adapters.EmployeeAdapter // Importa tu EmployeeAdapter
import com.example.api.departamentosViewModel
import com.example.api.models.rh.empleadosViewModel // Importa tu ViewModel
import com.example.api.models.rh.DepartamentoAdapter
import com.example.api.models.rh.EmployeeDepartamentResponse
import com.example.api.models.rh.EmployeeTotalResponse
import com.example.api.models.rh.GenderCount
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class EmpleadosRH : Fragment() {

    private val viewModel: empleadosViewModel by viewModels()
    private val viewModelDepartamentos: departamentosViewModel by viewModels()
    private val genderViewModel: GenderViewModel by viewModels()
    //private lateinit var employeeAdapter: EmployeeAdapter // Adapter para el RecyclerView
   // private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var totalEmpleadosTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pieChart: PieChart
    private lateinit var adapter: DepartamentoAdapter // Adapter para el RecyclerView
    // Para mostrar cuando la lista está vacía

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_empleados_r_h, container, false) // Tu layout con RecyclerView
        pieChart = view.findViewById(R.id.pieChartGender)// se inicializa la variable pieChart
       // recyclerView = view.findViewById(R.id.recyclerView_empleados) // ID del RecyclerView en tu XML
        progressBar = view.findViewById(R.id.progress) // ID del ProgressBar en tu XML
        // ID del TextView de lista vacía

        totalEmpleadosTextView = view.findViewById(R.id.totalEmpleados)
        recyclerView = view.findViewById(R.id.recyclerDepartamentos)
        // Configurar el pie chart
        setupPieChart()
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel() // Observar cambios en el ViewModel de generoViewModel
        //cargar los datos del viewModel
        genderViewModel.loadGenderData()
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

    private fun observeViewModel() {
        // Observador para los datos del gráfico
        genderViewModel.genderData.observe(viewLifecycleOwner) { genderList ->
            if (genderList.isNotEmpty()) {
                updatePieChart(genderList)
            }
        }

        // Observador para el estado de carga
        genderViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            pieChart.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        }

        // Observador para los errores
        genderViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }
    //configuración del gráfico
    private fun setupPieChart() {
        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            holeRadius = 58f
            transparentCircleRadius = 61f
            setDrawCenterText(true)
            centerText = "Género"
            setCenterTextSize(16f)
            rotationAngle = 0f
            isRotationEnabled = true
            isHighlightPerTapEnabled = true
            legend.isEnabled = true
        }
    }

    // Actualiza el gráfico con los datos proporcionados
    private fun updatePieChart(data: List<GenderCount>) {
        val entries = ArrayList<PieEntry>()
        val colors = ArrayList<Int>()
        val colorMap = mapOf(
            "M" to Color.BLUE,
            "F" to Color.rgb(255,105,180)
        )

        // Asumiendo que la respuesta es una lista, donde cada item tiene 'genero' y 'cantidad'
        for (item in data) {

            //estos datos salen de la dataclass de gender count
            entries.add(PieEntry(item.total.toFloat(), item.gender))
            // agregar colores
            colors.add(colorMap[item.gender] ?: Color.GRAY)
        }

        val dataSet = PieDataSet(entries, "Distribución de Género")

        // Asignar colores. Puedes usar colores personalizados.
        dataSet.colors = colors

        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter(pieChart))
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.BLACK)

        pieChart.data = pieData

        // Refrescar el gráfico
        pieChart.invalidate()
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
