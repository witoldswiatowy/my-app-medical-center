import {Component, Input, OnInit} from '@angular/core';
import {Doctor} from "../model/doctor";
import {AuthenticationServiceService} from "../authentication-service/authentication-service.service";

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  @Input() doctors: Doctor[] = []

  displayedColumns : string[] = [
    'identifier',
    'name',
    'surname',
    'phoneNumber',
    'email',
    'specialization',
    'hourlyRate',
    'clinicId',
    'clinicName'
  ]

  constructor(public authService: AuthenticationServiceService) { }

  ngOnInit(): void {
  }

  showDiv = {
    gallery : true,
    table: false
  }

}
