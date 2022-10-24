import {HttpClient} from "@angular/common/http";
import { Component, OnInit } from '@angular/core';
import {Doctor} from "../model/doctor";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {
  doctorList: Doctor[] = []

  constructor(private http: HttpClient) { }

  public refreshDoctorList(): void {
    console.log("refreshList1 on start methode from doctors component")
    this.http.get<Doctor[]>('http://localhost:8080/api/doctor')
      .subscribe({
        next: (data) => {
          this.doctorList = data;
        },
        error: (error) => {
          console.log('Error: ' + error)
        }
      })
  }
  ngOnInit(): void {
    this.refreshDoctorList()
  }

}
