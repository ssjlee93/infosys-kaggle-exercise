import { Timestamp } from "rxjs";

export interface USDatum {
    id: number,
    provinceState: string,
    countryRegion: string,
    lastUpdate: string,
    confirmed: number,
    deaths: number,
    recovered: number,
    active: number

    /*
     int id;
     String provinceState;
     String countryRegion;
     Timestamp lastUpdate;
     BigDecimal lat;
     BigDecimal long_;
     Integer confirmed;
     Integer deaths;
     Integer recovered;
     Integer active;
     String FIPS;
     BigDecimal incidentRate;
     BigDecimal totalTestResults;
     Integer peopleHospitalized;
     BigDecimal caseFatalityRatio;
     BigDecimal UID;
     String ISO3;
     BigDecimal testingRate;
     BigDecimal hospitalizationRate;
    */
}
