# Package Structure

- controller
  - ExcerciseController
- models
  - COVID19
- repository
  - COVID19DataRepository
- App.java

## Crawling

Reading and loading to DB is on an api. 

## Previous version 

### DAO 

To access CSV file. 
- DAO
  - DatumDao (interface)
  - DatumDaoFileImpl

Conclusion:
Created an API for reading CSV data from a particular date.  
Files are read and saved using JPA repository  
No need for DAO for files  

### models

- models
  - CSV Datum
  - DB Datum

CSV Datum to read CSV data
DB Datum to read/write to DB.

Conclusion:
Merged the two models.  
