// Copia y pega TODO este bloque en tu archivo EmpleadosRH.kt

package com.example.adminrh

import TipoContrato
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
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
import com.example.api.AgeViewModel
import com.example.api.GenderViewModel
import com.example.api.departamentosViewModel
import com.example.api.models.rh.AgeRangeCount
import com.example.api.models.rh.empleadosViewModel
import com.example.api.models.rh.DepartamentoAdapter
import com.example.api.models.rh.GenderCount
import com.example.api.viewmodels.ContractTypeViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class EmpleadosRH : Fragment() {

    // INSTANCIA DE LOS VIEWMODEL
    private val contractTypeViewModel: ContractTypeViewModel by viewModels()
    private val ageRangeViewModel: AgeViewModel by viewModels()
    private val viewModel: empleadosViewModel by viewModels()
    private val viewModelDepartamentos: departamentosViewModel by viewModels()
    private val genderViewModel: GenderViewModel by viewModels()

    // VISTAS
    private lateinit var pieChartContractType: PieChart
    private lateinit var progressBar: ProgressBar
    private lateinit var totalEmpleadosTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var pieChart: PieChart
    private lateinit var barchar: BarChart
    private lateinit var adapter: DepartamentoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_empleados_r_h, container, false)

        // Inicialización de todas las vistas
        pieChart = view.findViewById(R.id.pieChartGender)
        barchar = view.findViewById(R.id.barcharRango)
        progressBar = view.findViewById(R.id.progress)
        pieChartContractType = view.findViewById(R.id.pieChartContractType)
        totalEmpleadosTextView = view.findViewById(R.id.totalEmpleados)
        recyclerView = view.findViewById(R.id.recyclerDepartamentos)

        // Las funciones de SETUP solo configuran la apariencia, no los datos.
        setupGenderPieChart() // Se cambió el nombre para mayor claridad
        setupContractPieChart()
        setupbarchar()
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        // Inicia la carga de datos y configura los observadores
        observeContractTypeViewModel()
        observeGenderViewModel()
        observeAgeViewModel()
        observeTotalEmployees()
        observeDepartments()

        // Llama a la carga de datos
        contractTypeViewModel.loadContractData()
        genderViewModel.loadGenderData()
        ageRangeViewModel.loadAgeData()
        viewModel.load()
        viewModelDepartamentos.load()
    }


    private fun observeContractTypeViewModel() {
        contractTypeViewModel.contractData.observe(viewLifecycleOwner) { contractList ->
            if (contractList.isNotEmpty()) {
                updateContractTypePieChart(contractList)
            }
        }
        contractTypeViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            pieChartContractType.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        }
        contractTypeViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun observeAgeViewModel() {
        ageRangeViewModel.ageData.observe(viewLifecycleOwner) { ageList ->
            if (!ageList.isNullOrEmpty()) {
                updateBarChart(ageList)
            }
        }
        ageRangeViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            barchar.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        }
        ageRangeViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
        }
    }

    private fun observeGenderViewModel() {
        genderViewModel.genderData.observe(viewLifecycleOwner) { genderList ->
            if (genderList.isNotEmpty()) {
                updateGenderPieChart(genderList)
            }
        }
        genderViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            pieChart.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        }
        genderViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { Toast.makeText(context, it, Toast.LENGTH_LONG).show() }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeTotalEmployees() {
        viewModel.totalEmpleadosActivos.observe(viewLifecycleOwner, Observer { totalEmpleados ->
            totalEmpleadosTextView.text = "Total de empleados: $totalEmpleados"
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun observeDepartments() {
        viewModelDepartamentos.totalEmpleadosDepartamento.observe(viewLifecycleOwner, Observer { departamentos ->
            adapter.updateData(departamentos)
        })
        viewModelDepartamentos.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }


    private fun setupGenderPieChart() {
        pieChart.apply {
            description.isEnabled = false
            isDrawHoleEnabled = true
            setDrawCenterText(true)
            centerText = "Género"
            legend.isEnabled = true
        }
    }

    private fun setupContractPieChart() {
        pieChartContractType.apply {
            description.isEnabled = false
            isDrawHoleEnabled = true
            setDrawCenterText(true)
            centerText = "Contratos"
            legend.isEnabled = true
        }
    }

    private fun setupbarchar() {
        barchar.apply {
            description.isEnabled = false
            legend.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            axisRight.isEnabled = false
        }
    }


    private fun updateContractTypePieChart(contractList: List<TipoContrato>) {
        val entries = ArrayList<PieEntry>()
        for (contract in contractList) {
            entries.add(PieEntry(contract.total_empleados.toFloat(), contract.tipoContrato))
        }

        val dataSet = PieDataSet(entries, "Distribución de Contrato")
        dataSet.colors = ColorTemplate.PASTEL_COLORS.toList()

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.BLACK)

        pieChartContractType.data = pieData
        // Activa el modo porcentaje en el gráfico
        pieChartContractType.setUsePercentValues(true)


        pieChartContractType.invalidate()

    }


    private fun updateBarChart(data: List<AgeRangeCount>) {
        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        data.forEachIndexed { index, ageRange ->
            entries.add(BarEntry(index.toFloat(), ageRange.total.toFloat()))
            labels.add(ageRange.rango)
        }

        barchar.xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        val dataSet = BarDataSet(entries, "Empleados por Rango de Edad")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        dataSet.setDrawValues(true)

        val barData = BarData(dataSet)
        barData.barWidth = 0.5f

        barchar.data = barData
        barchar.invalidate()
    }

    private fun updateGenderPieChart(data: List<GenderCount>) {
        val entries = ArrayList<PieEntry>()
        val colors = ArrayList<Int>()
        val colorMap = mapOf("M" to Color.BLUE, "F" to Color.rgb(255, 105, 180))

        for (item in data) {
            entries.add(PieEntry(item.total.toFloat(), item.gender))
            colors.add(colorMap[item.gender] ?: Color.GRAY)
        }

        val dataSet = PieDataSet(entries, "Distribución de Género")
        dataSet.colors = colors

        val pieData = PieData(dataSet)
        pieData.setValueFormatter(PercentFormatter())
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.BLACK)

        // Asigna los datos al gráfico
        pieChart.data = pieData
        // Activa el modo porcentaje en el gráfico
        pieChart.setUsePercentValues(true)



    }


    private fun setupRecyclerView() {
        adapter = DepartamentoAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

}
