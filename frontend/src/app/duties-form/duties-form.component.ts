import {Component, OnInit} from '@angular/core';
import {AddDutyRequest, Duty} from "../model/duty";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {AuthenticationServiceService} from "../authentication-service/authentication-service.service";

@Component({
  selector: 'app-duties-form',
  templateUrl: './duties-form.component.html',
  styleUrls: ['./duties-form.component.css']
})
export class DutiesFormComponent implements OnInit {

  request: AddDutyRequest = {
    dutyFrom: "",
    dutyTo: "",
    doctorId: 0
  }

  constructor(
    public authService : AuthenticationServiceService,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  sendAddDutyRequest(): void {
    this.httpClient.post<Duty>("http://localhost:8080/api/duty/" + this.authService.loggedInUser?.id!, this.request)
      .subscribe({
        next: () => {
          this.snackBar.open('Add duty successfully!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })

          this.router.navigate(['/duties'])
        },
        error: (error) => {
          this.snackBar.open('Failed to add duty!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
        }
      })
  }
}
