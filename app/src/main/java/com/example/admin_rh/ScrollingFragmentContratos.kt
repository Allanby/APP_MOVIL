package com.example.admin_rh
import com.example.adminrh.R
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch
import android.widget.TextView
import com.anychart.AnyChartView
import com.anychart.AnyChart
import com.example.api.models.rh.retrofitPrueba
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.example.api.models.rh.ContratoGeneroResponse

class ScrollingFragmentContratos : Fragment(R.layout.fragment_scrolling_contratos) {
    private lateinit var textContratosPromedio: TextView
    private lateinit var textContratosNumero: TextView
    private lateinit var chartContratosGenero: AnyChartView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textContratosPromedio = view.findViewById(R.id.textContratosPromedio)
        textContratosNumero = view.findViewById(R.id.textContratosNumero)
        chartContratosGenero = view.findViewById(R.id.chartContratosGenero)

        fetchContratosPorGenero()
        fetchPromedioMeses()
        fetchContratosVigentes()
    }

    private fun fetchPromedioMeses() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getPromedioMeses()

                withContext(Dispatchers.Main) {
                    textContratosPromedio.text = "Meses: ${response.meses_promedio}"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textContratosPromedio.text = getString(R.string.errormensaje)
                }
            }
        }
    }

    //contratos vigentes

    private fun fetchContratosVigentes() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getContratosVigentes()
                withContext(Dispatchers.Main) {
                    textContratosNumero.text = response.contratos_vigentes.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    textContratosNumero.text = getString(R.string.errormensaje)
                }
            }
        }
    }


    private fun fetchContratosPorGenero() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitPrueba.api.getContratosPorGenero()
                withContext(Dispatchers.Main) {
                    mostrarGraficoContratosGenero(response)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun mostrarGraficoContratosGenero(data: List<ContratoGeneroResponse>) {
        val cartesian = AnyChart.column()


        val colores = listOf("#0057B8", "#C2185B")


        val entries = data.mapIndexed { index, it ->
            val genero = if (it.genero == "M") "Masculino" else "Femenino"
            object : ValueDataEntry(genero, it.total_contratos) {
                init {
                    setValue("fill", colores[index % colores.size])
                }
            }
        }

        val series = cartesian.column(entries)

        series.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("Contratos: {%Value}")


        cartesian.animation(true)
        cartesian.title("Contratos por Género")
        cartesian.title().fontColor("#000000")
        cartesian.background().fill("#E0C097")
        cartesian.xAxis(0).labels().fontColor("#000000")
        cartesian.yAxis(0).labels().fontColor("#000000")
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        chartContratosGenero.setChart(cartesian)
    }
}