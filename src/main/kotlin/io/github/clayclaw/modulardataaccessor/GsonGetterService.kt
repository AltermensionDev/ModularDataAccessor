package io.github.clayclaw.modulardataaccessor

import com.google.gson.Gson
import dev.reactant.reactant.core.component.Component
import dev.reactant.reactant.core.component.lifecycle.LifeCycleHook
import dev.reactant.reactant.core.dependency.layers.SystemLevel
import dev.reactant.reactant.extra.parser.GsonJsonParserService
import kotlin.reflect.KClass

@Component
internal class GsonGetterService(
    private val gsonJsonParserService: GsonJsonParserService
) : LifeCycleHook, SystemLevel {

    override fun onEnable() {
        gson = gsonJsonParserService.gson
    }

    companion object {
        @JvmStatic
        lateinit var gson: Gson
        @JvmStatic
        fun <T: Any> decodeJson(encoded: String, modelClass: KClass<T>): T = gson.fromJson(encoded, modelClass.java)
    }
}