package io.github.clayclaw.modulardataaccessor

import dev.reactant.modulardata.modules
import io.github.clayclaw.modulardataaccessor.extension.*
import org.bukkit.persistence.PersistentDataHolder

class PersistentDataAccessor<T: Any>(
    private val dataHolder: PersistentDataHolder,
    private val dataClass: Class<T>
) {

    private val dataKClass = dataClass.kotlin

    fun get() = dataHolder.modules.get(dataKClass)

    fun getOrDefault(moduleFactory: () -> T) = dataHolder.modules.getOrDefault(dataKClass, moduleFactory)

    fun getOrPut(moduleFactory: () -> T) = dataHolder.modules.getOrPut(dataKClass, moduleFactory)

    fun createIfNotExist(moduleFactory: () -> T) = dataHolder.modules.createIfNotExist(dataKClass, moduleFactory)

    fun remove() = dataHolder.modules.removeModule(dataKClass)

    fun mutate(mutation: (T) -> Any) = dataHolder.modules.mutateModule(dataKClass, mutation)

}