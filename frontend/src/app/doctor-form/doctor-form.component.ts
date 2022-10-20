import {Component, OnInit} from '@angular/core';
import {AddDoctorRequest, MedicalSpecialization} from "../model/doctor";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.css']
})
export class DoctorFormComponent implements OnInit {

  request: AddDoctorRequest = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    specialization: MedicalSpecialization.EMPTY,
    hourlyRate: 1.0,
    clinicId: 0
  }

  constructor(
    private httpClient: HttpClient
  ) { }

  ngOnInit(): void {
  }

  sendAddDoctorRequest(): void {
  }

}
