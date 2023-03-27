import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CitiesComponent} from "./components/cities/cities.component";
import {CitiesEditComponent} from "./components/cities-edit/cities-edit.component";

const routes: Routes = [
  {path: "", component: CitiesComponent},
  {path: "cities", component: CitiesComponent},
  {path: "cities/:id/edit", component: CitiesEditComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
