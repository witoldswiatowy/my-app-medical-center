import {Component, Input, OnInit} from '@angular/core';
import {Duty} from "../model/duty";

@Component({
  selector: 'app-duties-list',
  templateUrl: './duties-list.component.html',
  styleUrls: ['./duties-list.component.css']
})
export class DutiesListComponent implements OnInit {
  @Input() duties: Duty[] = []
  @Input() inputDisplayedColumns: string[] = []

  displayedColumns: string[] = [
    'duty-id',
    'duty-from',
    'duty-to',
    'doctor-id'
  ]

  constructor() { }

  getDisplayedColumns(): string[] {
    const visibleColumns = []
    for (let i = 0; i < this.displayedColumns.length; i++) {
      if(this.inputDisplayedColumns.includes(this.displayedColumns[i])){
        continue
      }

      visibleColumns.push(this.displayedColumns[i]);
    }

    return visibleColumns
  }

  ngOnInit(): void {
  }

}
