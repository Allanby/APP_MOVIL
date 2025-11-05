// Define aquí las clases de datos que coincidan con la respuesta JSON de tus APIs
data class DatosNomina(
    val mes: String,
    val anio: Int,
    val proyeccion: Double,
    val deducciones: Double,
    val beneficios: Double,
    val distribucion: List<DistribucionDepartamental>
)

data class DistribucionDepartamental(
    val departamento: String,
    val monto: Double
)
