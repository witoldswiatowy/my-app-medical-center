import {Component, Input, OnInit} from '@angular/core';
import {Duty} from "../model/duty";
import {Doctor} from "../model/doctor";
import {HttpClient} from "@angular/common/http";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {DutiesComponent} from "../duties/duties.component";

@Component({
  selector: 'app-duties-list',
  templateUrl: './duties-list.component.html',
  styleUrls: ['./duties-list.component.css']
})
export class DutiesListComponent implements OnInit {
  @Input() duties: Duty[] = []

  displayedColumns: string[] = [
    'duty-id',
    'duty-from',
    'duty-to',
    'doctor-id',
    'doctor-first-name',
    'doctor-last-name',
    'delete-button'
  ]

  constructor(
    public dutiesComponent: DutiesComponent,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  deleteDuty(id: number): void {
    this.httpClient.delete<Doctor>("http://localhost:8080/api/duty/" + id)
      .subscribe({
        next: (_) => {
          this.snackBar.open('Duty has been deleted', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
          this.dutiesComponent.refreshDutiesList()
          this.router.navigate(['/duties'])
        },
        error: (error) => {
          console.log('Error : ' + error)
          this.snackBar.open('Failed to delete duty!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
        }
      })
  }

}
