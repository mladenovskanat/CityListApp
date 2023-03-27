import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {CityPage} from "../models/CityPage";
import {Observable} from "rxjs";
import {City} from "../models/City";

@Injectable({
  providedIn: 'root'
})
export class CitiesService {
  citiesUrl: string = environment.citiesUrl
  constructor(private http: HttpClient) { }

  findCityByName(page :number, name?: string) : Observable<CityPage>{
    let params = new HttpParams()
    if (name) {
      params = params.append("name", name)
    }
      params = params.append("page", page)
    
    return this.http.get<CityPage>(this.citiesUrl + "/search?&size=50", {params:params})
  }

  getCity(id: number) : Observable<City>{
    return this.http.get<City>(this.citiesUrl + "/" + id)
  }

  editCity(id: number, city: any) {
    return this.http.put(this.citiesUrl + "/" + id, city)
  }
}
