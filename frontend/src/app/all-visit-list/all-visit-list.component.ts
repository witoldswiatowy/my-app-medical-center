import {HttpClient} from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import {Visit} from "../model/visit";

@Component({
  selector: 'app-all-visit-list',
  templateUrl: './all-visit-list.component.html',
  styleUrls: ['./all-visit-list.component.css']
})
export class AllVisitListComponent implements OnInit {
  visits: Visit[] = []

  constructor(private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.httpClient.get<Visit[]>("http://localhost:8080/api/visit")
      .subscribe({
        next: (data) =>{
          this.visits = data;
        },
        error: (error) => {
          console.log('Error: ' + error)
        }
      })
  }

  getHiddenColumnNames(): string[] {
    return []
  }
}
