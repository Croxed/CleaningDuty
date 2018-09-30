# CleaningScheme

This application fetches users dynamically from a CSV and exposes them via a endpoint ("/scheme").

The CSV has to have a header with the fields "firstname", "lastname" and "login".

In order for the application to read the CSV, please specify the path in `application.properties`.