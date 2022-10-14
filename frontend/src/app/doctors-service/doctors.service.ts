import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Doctor} from "../model/doctor";

@Injectable({
  providedIn: 'root'
})
export class DoctorsService {
  doctorList: Doctor[] = [];

  constructor(private http: HttpClient) { }

  public refreshDoctorList(): void {
    console.log("refreshList1 on start methode from doctors service")
    this.http.get<Doctor[]>('http://localhost:8080/doctor')
      .subscribe({
        next: (data) => {
          this.doctorList = data;
        },
        error: (error) => {
          console.log('Error: ' + error)
        }
      })
      // .subscribe((data) => { // promise
      //   console.log("refreshList2 on end methode from doctors service")
      //   console.log(data)
      //
      //   let receivedDoctorList = data as Doctor[];
      //   this.doctorList = receivedDoctorList;
      // })
  }
}
