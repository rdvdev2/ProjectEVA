package com.rdvdev2.projecteva

import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier

object Mod : ModInitializer {

    const val MODID = "project-eva"

    fun identifier(path: String) = Identifier(MODID, path)

    override fun onInitialize() {
        println("Project EVA is in stage 0!")
    }

}

