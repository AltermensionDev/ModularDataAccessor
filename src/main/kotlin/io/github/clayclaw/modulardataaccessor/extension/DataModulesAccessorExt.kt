package io.github.clayclaw.modulardataaccessor.extension

import dev.reactant.modulardata.DataModulesAccessor
import dev.reactant.modulardata.getModuleKey
import io.github.clayclaw.modulardataaccessor.GsonGetterService
import org.bukkit.persistence.PersistentDataType
import kotlin.reflect.KClass

fun DataModulesAccessor.commitModule(data: Any) {
    this.upsertModule(data).commitChanges
}

fun <T : Any> DataModulesAccessor.get(dataClass: KClass<T>): T? {
    return this.dataContainerGetter().get(getModuleKey(dataClass), PersistentDataType.STRING)
        ?.let { GsonGetterService.decodeJson(it, dataClass) }
}

fun <T : Any> DataModulesAccessor.getOrDefault(dataClass: KClass<T>, moduleFactory: () -> T): T {
    val key = getModuleKey(dataClass)
    return if (this.dataContainerGetter().has(key, PersistentDataType.STRING)) moduleFactory() else get(dataClass)!!
}

fun <T : Any> DataModulesAccessor.getOrPut(dataClass: KClass<T>, moduleFactory: () -> T): T {
    createIfNotExist(dataClass, moduleFactory)
    return get(dataClass)!!
}

fun <T : Any> DataModulesAccessor.createIfNotExist(dataClass: KClass<T>, moduleFactory: () -> T) {
    val key = getModuleKey(dataClass)
    if (!this.dataContainerGetter().has(key, PersistentDataType.STRING)) {
        commitModule(moduleFactory())
        this.commitChanges()
    }
}

fun <T : Any> DataModulesAccessor.removeModule(dataClass: KClass<T>) {
    this.dataContainerGetter().remove(getModuleKey(dataClass))
    this.commitChanges()
}

fun <T : Any> DataModulesAccessor.mutateModule(dataClass: KClass<T>, mutation: (T) -> Any) {
    this.get(dataClass)?.also {
        mutation(it)
    }?.let {
        commitModule(it)
    }
}