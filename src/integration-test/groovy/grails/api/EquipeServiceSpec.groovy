package grails.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EquipeServiceSpec extends Specification {

    EquipeService equipeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Equipe(...).save(flush: true, failOnError: true)
        //new Equipe(...).save(flush: true, failOnError: true)
        //Equipe equipe = new Equipe(...).save(flush: true, failOnError: true)
        //new Equipe(...).save(flush: true, failOnError: true)
        //new Equipe(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //equipe.id
    }

    void "test get"() {
        setupData()

        expect:
        equipeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Equipe> equipeList = equipeService.list(max: 2, offset: 2)

        then:
        equipeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        equipeService.count() == 5
    }

    void "test delete"() {
        Long equipeId = setupData()

        expect:
        equipeService.count() == 5

        when:
        equipeService.delete(equipeId)
        sessionFactory.currentSession.flush()

        then:
        equipeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Equipe equipe = new Equipe()
        equipeService.save(equipe)

        then:
        equipe.id != null
    }
}
