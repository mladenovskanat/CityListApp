import { Component, OnInit } from '@angular/core';
import {CitiesService} from "../../services/cities.service";
import {FormBuilder, FormControl} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-cities-edit',
  templateUrl: './cities-edit.component.html',
  styleUrls: ['./cities-edit.component.css']
})
export class CitiesEditComponent implements OnInit {

  city: any
  img: string=""
  constructor(private cityService: CitiesService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute) {
    this.city = this.formBuilder.group({
      name: new FormControl(null),
      photo: new FormControl(null)
    })
  }

  ngOnInit(): void {
    this.cityService.getCity(this.route.snapshot.params['id']).subscribe(data => {this.city.patchValue(data)
      this.img = data.photo
    })
  }

  edit() {
    this.cityService.editCity(this.route.snapshot.params['id'], this.city.value).subscribe(data => this.city.patchValue(data))

  }
}
