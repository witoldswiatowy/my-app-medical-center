import {Component, Input, OnInit} from '@angular/core';
import {Doctor} from "../model/doctor";
import {AuthenticationServiceService} from "../authentication-service/authentication-service.service";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {DoctorsComponent} from "../doctors/doctors.component";

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  @Input() doctors: Doctor[] = []

  displayedColumns: string[] = [
    'identifier',
    'name',
    'surname',
    'phoneNumber',
    'email',
    'specialization',
    'hourlyRate',
    'clinicId',
    'clinicName',
    'delete-button'
  ]

  constructor(
    public authService: AuthenticationServiceService,
    public doctorsComponent: DoctorsComponent,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  showDiv = {
    gallery: true,
    table: false
  }

  deleteDoctor(id: number): void {
    this.httpClient.delete<Doctor>("http://localhost:8080/api/doctor/" + id)
      .subscribe({
        next: (_) => {
          this.snackBar.open('Doctor has been deleted', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
          this.doctorsComponent.refreshDoctorList()
          this.router.navigate(['/doctors'])
        },
        error: (error) => {
          console.log('Error : ' + error)
          this.snackBar.open('Failed to delete doctor!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
        }
      })
  }

}
