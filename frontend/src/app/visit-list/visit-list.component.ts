import {Component, Input, OnInit} from '@angular/core';
import {Visit} from "../model/visit";

@Component({
  selector: 'app-visit-list',
  templateUrl: './visit-list.component.html',
  styleUrls: ['./visit-list.component.css']
})
export class VisitListComponent implements OnInit {
  @Input() visits: Visit[] = []
  @Input() hiddenColumnNames: string[] = []

  visibleColumnNames: string[] = [
    'visit-id',
    'status',
    'time-in-duty',
    'price',
    'doctor-id',
    'duty-id',
    'visits-person-first-name',
    'visits-person-last-name'
  ]

  constructor() {
  }

  getVisibleColumnNames(): string[] {
    const visibleColumns = []
    for (let i = 0; i < this.visibleColumnNames.length; i++) {
      if(this.hiddenColumnNames.includes(this.visibleColumnNames[i])){
        continue
      }

      visibleColumns.push(this.visibleColumnNames[i]);
    }

    return visibleColumns
  }

  ngOnInit(): void {
  }

}
