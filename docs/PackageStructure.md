# Package Structure

- controller
  - ExcerciseController
- dao
  - COVID19Dao
  - COVID19DaoFileImpl
- models
  - CSVDatum
  - DBDatum
- repository
  - DBRepository
- App.java

## DAO

Spring boot allows us to skip DAO; hwoever, to access local file, I added DAO for CSV files.  

## Models 

CSV Datum reads data from CSV
DB Datum reads and writes data from DB
