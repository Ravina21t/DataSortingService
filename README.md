```# DataSortingService
This service returns takes a JSON as an input and returns a ordered CSV format of it. 
URL Endpoint : http://localhost:8080/DataSortingService/
Example input:
{
    "Items": [
        {
            "Name": "Alice",
            "Age": 32,
            "Average": 14.998,
            "Active": true
        },
        {
            "Name": "Frank",
            "Age": 27,
            "Average": 6.333,
            "Active": false
        },
        {
            "Name": "Frank",
            "Age": 63,
            "Average": 1.009,
            "Active": false
        },
        {
            "Name": "Beth",
            "Age": 32,
            "Average": 5.998,
            "Active": true
        }
    ]
}```

CURL command to fetch the output :
curl -H "Content-Type: application/json" -d @input.json -X POST http://localhost:8080/DataSortingService/
input.json is the input file stored in the current directory

Example output:
Name,Age,Average,Active
Alice,32,14.998,true
Beth,32,5.998,true
Frank,27,6.333,false
Frank,63,1.009,false

