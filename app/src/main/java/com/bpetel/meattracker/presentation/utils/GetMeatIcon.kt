package com.bpetel.meattracker.presentation.utils

import com.bpetel.meattracker.R

object GetMeatIcon {

    fun getMeatIcon(type: String): Int {
        return when (type) {
            "boeuf" -> R.drawable.ic_beef
            "veau" -> R.drawable.ic_beef
            "porc" -> R.drawable.ic_pork
            "sanglier" -> R.drawable.ic_boar
            "mouton" -> R.drawable.ic_sheep
            "agneau" -> R.drawable.ic_sheep
            "cheval" -> R.drawable.ic_horse
            "cerf" -> R.drawable.ic_deer
            "poulet" -> R.drawable.ic_chicken
            "dinde" -> R.drawable.ic_turkey
            "cail" -> R.drawable.ic_poultry
            "pintade" -> R.drawable.ic_poultry
            "canard" -> R.drawable.ic_duck
            "lapin" -> R.drawable.ic_rabbit

            else -> R.drawable.ic_turkey // Icône par défaut si le type n'est pas reconnu
        }
    }
}