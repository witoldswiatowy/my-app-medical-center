import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

export enum MedicalSpecialization {
  CARDIOLOGY = "CARDIOLOGY",
  DERMATOLOGY = "DERMATOLOGY",
  ENDOCRINOLOGY = "ENDOCRINOLOGY",
  PEDIATRICS = "PEDIATRICS",
  GYNECOLOGY = "GYNECOLOGY",
  OPHTHALMOLOGY = "OPHTHALMOLOGY",
  ORTHOPEDIC_SURGERY = "ORTHOPEDIC_SURGERY",
  OCCUPATIONAL_MEDICINE = "OCCUPATIONAL_MEDICINE"
}

export type Doctor = {
  id: number,

  name: string,
  surname: string,
  phoneNumber: string,
  email: string,
  specialization: MedicalSpecialization,
  hourlyRate: number,
  clinicId: number
}

@Injectable({
  providedIn: 'root'
})
export class DoctorsService {
  doctorList: Doctor[] = [];

  constructor(private http: HttpClient) { }

  public refreshDoctorList(): void {
    this.http.get('http://localhost:8080/doctors')
      .subscribe((data) => { // promise
        console.log(data)

        let receivedDoctorList = data as Doctor[];
        this.doctorList = receivedDoctorList;
      })
  }
}
