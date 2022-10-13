import { Component, OnInit } from '@angular/core';
import {UserServiceService} from "../user-service/user-service.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  displayedColumns : string[] = [
    'identifier',
    'login',
    'name',
    'surname',
    'phoneNumber',
    'email',
    'birthDate',
    'sex',
    'roles'
  ]


  constructor(protected userServiceService : UserServiceService) { }

  ngOnInit(): void {
    this.userServiceService.refreshUserList()
  }

}
