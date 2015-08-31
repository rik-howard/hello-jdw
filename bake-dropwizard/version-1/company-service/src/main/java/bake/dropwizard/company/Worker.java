
package bake.dropwizard.company;

import bake.dropwizard.common.types.exceptions.dao.ExistenceException;
import bake.dropwizard.common.types.exceptions.dao.NonexistenceException;
import bake.dropwizard.common.types.exceptions.dao.NonuniquenessException;
import bake.dropwizard.common.types.fields.Name;
import bake.dropwizard.common.types.fields.CompanyId;
import bake.dropwizard.common.types.fields.Surname;
import bake.dropwizard.common.types.pojos.Company;

import java.util.List;
import java.util.stream.Collectors;

public class Worker {

    private DAO dao;

    public Worker (DAO dao) {
        this.dao = dao;
    }

    List <Company> selectedCompanys () {
        return dao.selectedCompanys ();
    }

    List <Company> selectedCompanys (CompanyId id) {
        return dao
            .selectedCompanys ()
            .stream ()
            .filter (company -> company.getId ().equals (id))
            .collect (Collectors.toList ());
    }

    List <Company> selectedCompanys (Name name) {
        return dao
            .selectedCompanys ()
            .stream ()
            .filter (company -> company.getName ().equals (name))
            .collect (Collectors.toList ());
    }

    Company insertedCompany (Company company)
    throws ExistenceException {
        List <Company> found = selectedCompanys (company.getName ())
            .stream ()
            .filter (p -> p.getDeleted () == null)
            .collect (Collectors.toList ());
        if (found.size () > 0)
            throw new ExistenceException ();
        dao.insertCompany (company);
        return selectedCompanys (company.getName ()).get (0);
    }

    Company updatedCompany (Company company)
    throws NonexistenceException, NonuniquenessException {
        List <Company> found = selectedCompanys (company.getId ());
        found = found
            .stream ()
            .filter (p -> p.getInserted ().dateTime ().equals (company.getInserted ().dateTime ()))
            .collect (Collectors.toList ());
        if (found.isEmpty ())
            throw new NonexistenceException ();
        if (found.size () > 1)
            throw new NonuniquenessException ();
        dao.updateCompany (company);
        found = selectedCompanys (company.getId ());
        return found.get (0);
    }

    Company deletedCompany (Company company) {
        List <Company> found = selectedCompanys (company.getId ())
            .stream ()
            .filter (p -> p.getDeleted () == null)
            .collect (Collectors.toList ());
        if (found.isEmpty ())
            throw new NonexistenceException ();
        if (found.size () > 1)
            throw new NonuniquenessException ();
        dao.deleteCompany (company);
        found = selectedCompanys (company.getId ());
        return found.get (0);
    }

}
