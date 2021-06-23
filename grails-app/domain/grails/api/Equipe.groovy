package grails.api

import org.bson.types.ObjectId

import javax.persistence.Id

class Equipe {
    ObjectId id
    String nom
    String logo
    static constraints = {

    }
}
