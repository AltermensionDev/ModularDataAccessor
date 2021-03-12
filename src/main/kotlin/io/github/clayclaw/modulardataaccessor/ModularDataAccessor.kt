package io.github.clayclaw.modulardataaccessor

import dev.reactant.reactant.core.ReactantPlugin
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.bukkit.plugin.java.JavaPlugin

@ReactantPlugin(["io.github.clayclaw.modulardataaccessor"])
class ModularDataAccessor : JavaPlugin() {

    companion object {
        @JvmStatic
        val log: Logger = LogManager.getLogger("ModularDataAccessor")
    }

}
