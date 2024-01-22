# DV01 backend challenge

## Running the app

Run this using [sbt](http://www.scala-sbt.org/).
Use at least `4G` of heap to avoid OutOfMemoryErrors.

```bash
sbt run -J-Xms4G
```

And then go to <http://localhost:9000> to see the running web application.

## Controllers

- `HomeController.scala`:

  Shows how to load a csv file


## Submit Query

Run below `curl` command to `POST` `JSON` queries. It supports retrieval by fields, filtering for
numeric fields using `AND`, aggregate functions for numeric fields as well as grouping.
As further enhancements we can also add filtering on aggregate result, sorting, limit as well as 
filtering using combination of `AND` and `OR`.
The field names match those in the `CSV` file.

```bash
curl --location 'http://localhost:9000/insights' \
--header 'Content-Type: text/plain' \
--data '{
"fields": [
{"name": "emp_title"},
{"name": "addr_state"},
{"name": "total_pymnt"},
{"name": "loan_amnt", "aggregate": "Sum"},
{"name": "loan_amnt", "aggregate": "Avg"},
{"name": "loan_amnt", "aggregate": "Count"},
{"name": "loan_amnt", "aggregate": "Min"},
{"name": "loan_amnt", "aggregate": "Max"}
],
"conditions": [
{"field": "total_pymnt", "operator": ">", "value": 2000},
{"field": "total_pymnt", "operator": "<=", "value": 5600}
],
"groupBy": ["emp_title"]
}'
```

Above query returns below result that's been grouped by `emp_title` and aggregated on `loan_amnt`.

```bash
[
    [
        "parts manager", "NC", "5085.6", "16000.0", "8000.0", "2.0", "6000.0", "10000.0"
    ],
    [
        "Firefighter ", "NY", "2999.24", "27025.0", "5405.0", "5.0", "3025.0", "10000.0"
    ],
    [
        "Sales Person", "TN", "4792.52", "24250.0", "6062.5", "4.0", "5000.0", "8650.0"
    ],
    [
        "Information Systems Analyst", "CA", "4361.5719447472", "4200.0", "4200.0", "1.0", "4200.0", "4200.0"
    ],
    ...
]    
```
