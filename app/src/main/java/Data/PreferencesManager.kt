package Data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE)

    fun saveStudentData(matricula: String, nombre: String, carrera: String, turno: String, estatus: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putString("matricula", matricula)
        editor.putString("nombre", nombre)
        editor.putString("carrera", carrera)
        editor.putString("turno", turno)
        editor.putBoolean("estatus", estatus)
        editor.apply()
    }

    fun getMatricula(): String = sharedPreferences.getString("matricula", "") ?: ""
    fun getNombre(): String = sharedPreferences.getString("nombre", "") ?: ""
    fun getCarrera(): String = sharedPreferences.getString("carrera", "Ingeniería de Software") ?: "Ingeniería de Software"
    fun getTurno(): String = sharedPreferences.getString("turno", "Matutino") ?: "Matutino"
    fun getEstatus(): Boolean = sharedPreferences.getBoolean("estatus", true)
}