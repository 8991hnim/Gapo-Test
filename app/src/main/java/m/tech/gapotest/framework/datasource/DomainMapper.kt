package m.tech.gapotest.framework.datasource

interface DomainMapper<T, DomainModel> {

    fun toDomain(model: T): DomainModel

    fun fromDomain(domainModel: DomainModel): T

}