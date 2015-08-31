
package bake.dropwizard.company;

import bake.dropwizard.common.types.exceptions.dao.ExistenceException;
import bake.dropwizard.common.types.exceptions.dao.NonexistenceException;
import bake.dropwizard.common.types.exceptions.dao.NonuniquenessException;
import bake.dropwizard.common.types.fields.Name;
import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.pojos.Company;

import java.util.List;

public class Manager {

    private Worker worker;

    public Manager (Worker worker) {
        this.worker = worker;
    }

    List <Company> accessedCompanys (CompanyId companyId, Name name, Surname surname) {
        Company query = new Company (companyId, name, null, null, null);
        return
            query.queriesById ()?
                worker.selectedCompanys (companyId):
            query.queriesByName ()?
                worker.selectedCompanys (name):
            worker.selectedCompanys ();
    }

    Company createdCompany (Company company)
    throws ExistenceException {
        return worker.insertedCompany (company);
    }

    Company recreatedCompany (Company company)
    throws NonexistenceException, NonuniquenessException {
        return worker.updatedCompany (company);
    }

    Company decreatedCompany (Company company)
    throws NonexistenceException, NonuniquenessException {
        return worker.deletedCompany (company);
    }

}
