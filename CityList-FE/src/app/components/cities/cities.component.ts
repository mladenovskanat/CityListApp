import {Component, OnInit} from '@angular/core';
import {CitiesService} from "../../services/cities.service";
import {FormBuilder, FormControl} from "@angular/forms";
import {City} from "../../models/City";
import {CityPage} from "../../models/CityPage";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {
  citiesFound: City[] = []
  city: any
  error: boolean = false
  pages: number[] = []
  constructor(private cityService: CitiesService,
              private formBuilder: FormBuilder,
              private router: Router) {
    this.city = this.formBuilder.group({
      name: new FormControl(null),
    })
    this.cityService.findCityByName(0).subscribe(data => {this.citiesFound = data.content
      this.pages = Array.from({ length: data.totalPages }).map((currentElement, i) => i)
    })
  }

  ngOnInit(): void {
  }

  filter(page?:number) {
    this.cityService.findCityByName(page?page:0, this.city.get("name")?.value).subscribe(data => {
      this.citiesFound = data.content
    },() => {
      this.error = true
    })
  }

  edit(id: number) {
    this.router.navigate(['cities/'+ id +'/edit'])
  }
}
