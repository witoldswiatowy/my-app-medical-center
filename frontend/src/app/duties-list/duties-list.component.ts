import {Component, Input, OnInit} from '@angular/core';
import {Duty} from "../model/duty";

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
    'doctor-last-name'
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
