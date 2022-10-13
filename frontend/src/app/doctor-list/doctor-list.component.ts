import { Component, OnInit } from '@angular/core';
import {DoctorsService} from "../doctors-service/doctors.service";

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  displayedColumns : string[] = [
    'identifier',
    'name',
    'surname',
    'phoneNumber',
    'email',
    'specialization',
    'hourlyRate',
    'clinicId'
  ]

  constructor(protected doctorService : DoctorsService) { }

  ngOnInit(): void {
    this.doctorService.refreshDoctorList()
  }

}
