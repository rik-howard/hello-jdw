
package bake.dropwizard.company;

import bake.dropwizard.common.exceptions.dao.ExistenceException;
import bake.dropwizard.common.exceptions.dao.NonexistenceException;
import bake.dropwizard.common.exceptions.dao.NonuniquenessException;
import bake.dropwizard.common.types.fields.Name;
import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.pojos.Company;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import java.util.List;

public class Director {

    private Manager manager;

    public Director (Manager manager) {
        this.manager = manager;
    }

    List <Company> gottenCompanys (
    CompanyId id,
    Name name,
    Surname surname
    ) {
        return manager.accessedCompanys (
            id,
            name,
            surname
        );
    }

    Company postedCompany (Company company) {
        if (company.canBeCreated ())
            try {
                return manager.createdCompany (company);
            }
            catch (ExistenceException e) {
                throw new WebApplicationException (Status.CONFLICT);
            }
            catch (Exception e) {
                System.out.println (e.toString ());
                throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
            }
        throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    Company putCompany (Company company) {
        if (company.canBeRecreated ())
            try {
                return manager.recreatedCompany (company);
            }
            catch (NonexistenceException e) {
                throw new WebApplicationException (Status.EXPECTATION_FAILED);
            }
            catch (NonuniquenessException e) {
                throw new WebApplicationException (Status.CONFLICT);
            }
            catch (Exception e) {
                System.out.println (e.toString ());
                throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
            }
        throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

    Company deletedCompany (CompanyId id) {
        Company deletee = new Company (id, null, null, null, null);
        if (deletee.canBeDecreated ())
            try {
                return manager.decreatedCompany (deletee);
            }
            catch (NonexistenceException e) {
                throw new WebApplicationException (Status.EXPECTATION_FAILED);
            }
            catch (NonuniquenessException e) {
                throw new WebApplicationException (Status.CONFLICT);
            }
            catch (Exception e) {
                System.out.println (e.toString ());
                throw new WebApplicationException (Status.INTERNAL_SERVER_ERROR);
            }
        throw new WebApplicationException (Status.NOT_ACCEPTABLE);
    }

}
