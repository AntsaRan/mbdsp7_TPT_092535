package grails.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EquipeController {

    EquipeService equipeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond equipeService.list(params), model:[equipeCount: equipeService.count()]
    }

    def show(Long id) {
        respond equipeService.get(id)
    }

    def create() {
        respond new Equipe(params)
    }

    def save(Equipe equipe) {
        if (equipe == null) {
            notFound()
            return
        }

        try {
            equipeService.save(equipe)
        } catch (ValidationException e) {
            respond equipe.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'equipe.label', default: 'Equipe'), equipe.id])
                redirect equipe
            }
            '*' { respond equipe, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond equipeService.get(id)
    }

    def update(Equipe equipe) {
        if (equipe == null) {
            notFound()
            return
        }

        try {
            equipeService.save(equipe)
        } catch (ValidationException e) {
            respond equipe.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'equipe.label', default: 'Equipe'), equipe.id])
                redirect equipe
            }
            '*'{ respond equipe, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        equipeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'equipe.label', default: 'Equipe'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'equipe.label', default: 'Equipe'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
