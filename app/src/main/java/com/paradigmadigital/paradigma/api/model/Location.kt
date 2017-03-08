package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("city")
    val city: String? = null

    @SerializedName("country_iso3166")
    val country: String? = null

}

//"location": {
//    "type": "CITY",
//    "country": "US",
//    "country_iso3166": "US",
//    "country_name": "USA",
//    "state": "CA",
//    "city": "San Francisco",
//    "tz_short": "PDT",
//    "tz_long": "America/Los_Angeles",
//    "lat": "37.776289",
//    "lon": "-122.395234",
//    "zip": "94107",
//    "magic": "1",
//    "wmo": "99999",
//    "l": "/q/zmw:94107.1.99999",
//    "requesturl": "US/CA/San_Francisco.html",
//    "wuiurl": "https://www.wunderground.com/US/CA/San_Francisco.html",
//    "nearby_weather_stations": {
//    "airport": {
//    "station": [{
//    "city": "Oakland",
//    "state": "CA",
//    "country": "US",
//    "icao": "KOAK",
//    "lat": "37.72000122",
//    "lon": "-122.22000122"
//}, {
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "icao": "KSFO",
//    "lat": "37.61999893",
//    "lon": "-122.37000275"
//}, {
//    "city": "Hayward",
//    "state": "CA",
//    "country": "US",
//    "icao": "KHWD",
//    "lat": "37.65999985",
//    "lon": "-122.12000275"
//}, {
//    "city": "Half Moon Bay",
//    "state": "CA",
//    "country": "US",
//    "icao": "KHAF",
//    "lat": "37.50999832",
//    "lon": "-122.50000000"
//}]
//},
//    "pws": {
//    "station": [{
//    "neighborhood": "Mission Bay - My weather is better than yours.",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR53",
//    "distance_km": 0,
//    "distance_mi": 0
//}, {
//    "neighborhood": "Potrero Hill",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR108",
//    "distance_km": 1,
//    "distance_mi": 0
//}, {
//    "neighborhood": "China Basin Landing",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR14",
//    "distance_km": 0,
//    "distance_mi": 0
//}, {
//    "neighborhood": "SoMa",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR113",
//    "distance_km": 0,
//    "distance_mi": 0
//}, {
//    "neighborhood": "SOMA",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR131",
//    "distance_km": 1,
//    "distance_mi": 0
//}, {
//    "neighborhood": "SOMA - Near Van Ness",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR58",
//    "distance_km": 2,
//    "distance_mi": 1
//}, {
//    "neighborhood": "The Mission: Even the weather is hip",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR79",
//    "distance_km": 2,
//    "distance_mi": 1
//}, {
//    "neighborhood": "North Mission (Valencia & Market)",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR49",
//    "distance_km": 2,
//    "distance_mi": 1
//}, {
//    "neighborhood": "Financial District",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR102",
//    "distance_km": 1,
//    "distance_mi": 1
//}, {
//    "neighborhood": "MesoWest San Fran Sewage Treatment Plant CA US CARB",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "MCQ147",
//    "distance_km": 4,
//    "distance_mi": 2
//}, {
//    "neighborhood": "Castro/San Francisco",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KPCASANF2",
//    "distance_km": 3,
//    "distance_mi": 2
//}, {
//    "neighborhood": "The Castro",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR114",
//    "distance_km": 3,
//    "distance_mi": 2
//}, {
//    "neighborhood": "HADS SAN FRANCISCO DOWNTOWN CA US",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "MSFOC1",
//    "distance_km": 3,
//    "distance_mi": 2
//}, {
//    "neighborhood": "APRSWXNET San Francisco CA US",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "MD9646",
//    "distance_km": 2,
//    "distance_mi": 1
//}, {
//    "neighborhood": "Noe Valley",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR3",
//    "distance_km": 4,
//    "distance_mi": 2
//}, {
//    "neighborhood": "Noe Valley",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR86",
//    "distance_km": 4,
//    "distance_mi": 2
//}, {
//    "neighborhood": "Castro/Eureka Valley",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR138",
//    "distance_km": 4,
//    "distance_mi": 2
//}, {
//    "neighborhood": "Noe Valley (South)",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR98",
//    "distance_km": 4,
//    "distance_mi": 2
//}, {
//    "neighborhood": "Billy Goat Hill",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR73",
//    "distance_km": 5,
//    "distance_mi": 3
//}, {
//    "neighborhood": "Diamond Heights",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR76",
//    "distance_km": 4,
//    "distance_mi": 2
//}, {
//    "neighborhood": "Portola",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR135",
//    "distance_km": 5,
//    "distance_mi": 3
//}, {
//    "neighborhood": "APRSWXNET San Francisco CA US",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "MC9629",
//    "distance_km": 6,
//    "distance_mi": 3
//}, {
//    "neighborhood": "Twin Peaks",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR34",
//    "distance_km": 5,
//    "distance_mi": 3
//}, {
//    "neighborhood": "Sunnyside",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR70",
//    "distance_km": 6,
//    "distance_mi": 3
//}, {
//    "neighborhood": "Golden Gate Heights",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR46",
//    "distance_km": 6,
//    "distance_mi": 4
//}, {
//    "neighborhood": "Forest Hill",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR72",
//    "distance_km": 6,
//    "distance_mi": 4
//}, {
//    "neighborhood": "West Portal",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR130",
//    "distance_km": 7,
//    "distance_mi": 4
//}, {
//    "neighborhood": "Sherwood Forest",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR129",
//    "distance_km": 7,
//    "distance_mi": 4
//}, {
//    "neighborhood": "Forest Hill",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR109",
//    "distance_km": 7,
//    "distance_mi": 4
//}, {
//    "neighborhood": "Forest Hill",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR48",
//    "distance_km": 7,
//    "distance_mi": 4
//}, {
//    "neighborhood": "Mid Richmond",
//    "city": "San Francisco",
//    "state": "CA",
//    "country": "US",
//    "id": "KCASANFR128",
//    "distance_km": 6,
//    "distance_mi": 4
//}]
//}
//}
//}
//}


