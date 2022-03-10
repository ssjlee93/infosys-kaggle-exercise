import { Timestamp } from "rxjs";

export interface Datum {
    id: number,
    admmin2: string,
    provinceState: string,
    countryRegion: string,
    lastUpdate: string,
    confirmed: number,
    deaths: number,
    recovered: number,
    active: number
}
