package m.tech.gapotest.business.interactors

object UseCaseEx {
    @Synchronized
    fun <T> removeFromList(list: MutableList<T>, item: T) {
        list.remove(item)
    }
}