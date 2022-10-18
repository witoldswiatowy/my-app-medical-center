import {HttpClient} from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import {Duty} from "../model/duty";

@Component({
  selector: 'app-duties',
  templateUrl: './duties.component.html',
  styleUrls: ['./duties.component.css']
})
export class DutiesComponent implements OnInit {
  duties: Duty[] = []

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get<Duty[]>("http://localhost:8080/api/duty")
      .subscribe({
        next: (data) =>{
          this.duties = data;
        },
        error: error => {
          console.log('Error: ' + error)
        }
      })
  }
}
