# CleaningDuty

This application fetches users dynamically from a CSV and exposes them via a endpoint ("/scheme").
You can add any class and loader to the application as long as it follows the same structure as the existing ones.
The CSV files that are to be parsed has to have a header with the fields to be used in your class.

In order for the application to read the CSV, please specify the path in `application.properties`.
You can add multiple paths with different names.
