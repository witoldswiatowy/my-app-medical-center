import {Component, OnInit} from '@angular/core';
import {AddDoctorRequest, Doctor, MedicalSpecialization} from "../model/doctor";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

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
    description: '',
    imgUrl: '',
    clinicId: 0
  }

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  sendAddDoctorRequest(): void {
    this.httpClient.post<Doctor>("http://localhost:8080/api/doctor", this.request)
      .subscribe({
        next: (data) => {
          this.snackBar.open('Add doctor successfully!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })

          this.router.navigate(['/doctors'])
        },
        error: (error) => {
          console.log('Error : ' + error)
          this.snackBar.open('Failed to add doctor!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
        }
      })
  }

}
