# sql-safe-annotation-example
This is a Sample Project to show the usage of a Custom annotation for validating if field-values or parameter-values are SQL-Injection safe

A usage could look something like this
`private @SQLInjectionSafe String id;`

This is basically a spring boot mvc project.

You can run the Application.java

and test by hitting these urls

* [http://localhost:3000//api/data/getById?id=123](http://localhost:3000//api/data/getById?id=123)

* [http://localhost:3000//api/data/getById?id=create table abcd](http://localhost:3000//api/data/getById?id=create table abcd)
