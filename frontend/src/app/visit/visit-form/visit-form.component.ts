import { Component, OnInit } from '@angular/core';
import {BookingVisitRequest, Visit} from "../../model/visit";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Component({
  selector: 'app-visit-form',
  templateUrl: './visit-form.component.html',
  styleUrls: ['./visit-form.component.css']
})
export class VisitFormComponent implements OnInit {

  request: BookingVisitRequest = {
    timeInDuty: "",
    doctorId: 0,
    dutyId: 0,
    userId: 0,
  }

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  sendBookingVisitRequest(): void {
    this.httpClient.post<Visit>("http://localhost:8080/api/visit", this.request)
      .subscribe({
        next: (data) => {
          this.snackBar.open('Booking visit successfully!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })

          this.router.navigate(['/home'])
        },
        error: (error) => {
          console.log('Error : ' + error)
          this.snackBar.open('Failed to booking visits!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
        }
      })
  }

}
