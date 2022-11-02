import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateUserRequest } from '../model/user';
import {Doctor} from "../model/doctor";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

export enum Sex {
FEMALE = "FEMALE",
MALE = "MALE",
OTHER = "OTHER"
}

export type User = {
  id: number,
  login: string,

  name: string,
  surname: string,
  phoneNumber: string,
  email: string,
  birthDate: string,
  sex: Sex,
  roles: string[]
}

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  userList: User[] = []

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
    ) { }

  public refreshUserList(): void {
        console.log("refreshUserList1")
    this.httpClient.get('http://localhost:8080/api/user')
      .subscribe((data) => {
        console.log("refreshUserList2")
        console.log(data);

        let receivedUserList = data as User[];
        this.userList = receivedUserList;
      })
  }

  public deleteUser(id: number): void {
    console.log("delete user")
    this.httpClient.delete<User>("http://localhost:8080/api/user/" + id)
      .subscribe({
        next: (_) => {
          this.snackBar.open('User has been deleted', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
          this.refreshUserList()
          this.router.navigate(['/users'])
        },
        error: (error) => {
          console.log('Error : ' + error)
          this.snackBar.open('Failed to delete user!', undefined, {
            verticalPosition: 'top',
            horizontalPosition: 'start',
            duration: 2000
          })
        }
      })
  }

  public getDefautUserRequest(): CreateUserRequest {
    return {
      login: "",
      pass: "",
      name: "",
      surname: ""
    }
  }

  public registerUser(createUserRequest: CreateUserRequest) : Observable<Object>{
    return this.httpClient.post("http://localhost:8080/api/user", createUserRequest);
  }
}
