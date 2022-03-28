package com.example.hackernews.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getTimeElapsed(date:String): String? {
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val dateFormatInit = getDate(date)
            val dateFormated = (input).parse(dateFormatInit)
            val dateNow = Date()
           return getDiferencia(dateFormated,dateNow )

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun getDiferencia(fechaInicial: Date, fechaFinal: Date): String? {
        var diferencia = Math.abs(fechaFinal.time - fechaInicial.time)
        val segsMilli: Long = 1000
        val minsMilli = segsMilli * 60
        val horasMilli = minsMilli * 60
        val diasMilli = horasMilli * 24
        val diasTranscurridos = diferencia / diasMilli
        diferencia = diferencia % diasMilli
        val horasTranscurridos = diferencia / horasMilli
        diferencia = diferencia % horasMilli
        val minutosTranscurridos = diferencia / minsMilli
        diferencia = diferencia % minsMilli
        val segsTranscurridos = diferencia / segsMilli

        if ( segsTranscurridos> 0 && minutosTranscurridos == 0L && horasTranscurridos == 0L && diasTranscurridos == 0L) {
            return segsTranscurridos.toString() + "s"
        }else if(minutosTranscurridos >= 0 && horasTranscurridos == 0L && diasTranscurridos == 0L){
            return minutosTranscurridos.toString() + "min"
        } else
        if ( horasTranscurridos > 0 && diasTranscurridos == 0L){
            return horasTranscurridos.toString() + "h";
        }else if (diasTranscurridos > 0) {
            if( diasTranscurridos == 1L) {
                return "Yesterday"
            }else {
                return diasTranscurridos.toString() + "d"
            }

        }

        return ""
    }

    private fun getDate(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        try {
            calendar.time = sdf.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val output = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return output.format(calendar.time)
    }
}