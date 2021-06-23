package grails.api

import grails.gorm.services.Service

@Service(Equipe)
interface EquipeService {

    Equipe get(Serializable id)

    List<Equipe> list(Map args)

    Long count()

    void delete(Serializable id)

    Equipe save(Equipe equipe)

}