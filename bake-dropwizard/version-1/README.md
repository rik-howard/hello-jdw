
# Bake Dropwizard
Version 1

Building

    common-types$ mvn clean install
    company-service$ mvn clean verify
    person-service$ mvn clean verify
    company-person-service$ mvn clean verify

Running the DBMS

    $ startterm () { xterm -geometry 128x32 -title "$1" -e "$2" & }
    $ startterm Cassandra "cassandra -f"
    $ startterm "CQL Shell" "cqlsh"

Paste the relevant statements from *setup.sql* into the CQL shell.

Checking the web service

    company-service$ java -jar target/company-service-0.0.jar check

Running the web service

    company-service$ java -jar target/company-service-0.0.jar server application.yaml

Load *postman.json* into Postman.

N. B. that the company service has been changed to work with Cassandra 3.  The company-person service was not finished; the person service was but now needs to be changed to work with Cassandra 3.

---
