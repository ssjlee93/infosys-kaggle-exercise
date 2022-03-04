# Data Modeling

## kaggle databases  

- Uses CLI to download .csv data
- kaggle CLI required

List of datasets I looked into: 
- [COVID-19 Open Research Dataset Challenge (CORD-19)](https://www.kaggle.com/allen-institute-for-ai/CORD-19-research-challenge)  
- [COVID-19 Dataset](https://www.kaggle.com/imdevskp/corona-virus-report)  

## CSSEGI

[GitHub](https://github.com/CSSEGISandData/COVID-19)  

This is the sand data from Johns Hopkins Research  

Decided to use this data  

Reasons to use this data:
- many datasets in kaggle already depend on this data  
- focussing on the output product, source of data may be trivial
- more trustworthy than scraped data on kaggle

- Requires GitHub API to automate cloning repo every day and reading/ updating data

For the purpose of this project, I will use locally downloaded data  

## Model

### Header and sample

```csv
FIPS,Admin2,Province_State,Country_Region,Last_Update,Lat,Long_,Confirmed,Deaths,Recovered,Active,Combined_Key,Incident_Rate,Case_Fatality_Ratio
,,,Afghanistan,2021-01-02 05:22:33,33.93911,67.709953,52513,2201,41727,8585,Afghanistan,134.89657830525067,4.191343095995277
```

From the list of columns, I will use:  
- Province_State
- Country_Region
- Confirmed
- Deaths
- Recovered
- Active
Optional:
- Incident_Rate
- Case_Fatality_Ratio
