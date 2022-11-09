import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {CreateUserRequest, Sex, UpdateUserRequest, UserDetails} from '../../model/user';
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {BACKEND_BASE_URL} from "../../model/constants";

export type User = {
  id: number,
  login: string,

  name: string,
  surname: string,
  phoneNumber: string,
  email: string,
  birthDate: string,
  sex: Sex,
  roles: string[],
  createDate: string,
  updateDate: string
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

  public getDefaultUserDetails(): UserDetails {
    return {
      id: 0,
      login: "",

      name: "",
      surname: "",
      phoneNumber: "",
      email: "",
      birthDate: "",
      sex: Sex.MALE,
      roles: [],
      createDate: "",
      updateDate: "",
      doctorId: 0
    }
  }

  public getDefaultUpdateUserRequest(): UpdateUserRequest {
    return {
      id: 0,
      name: "",
      surname: "",
      phoneNumber: "",
      email: "",
      birthDate: "",
      sex: Sex.MALE,
      roles: [],
      doctorId: 0
    }
  }

  public getUserDetails(userId: number): Observable<UserDetails> {
    return this.httpClient.get<UserDetails>(BACKEND_BASE_URL + "user/" + userId);
  }

  public registerUser(createUserRequest: CreateUserRequest) : Observable<Object>{
    return this.httpClient.post("http://localhost:8080/api/user", createUserRequest);
  }

  public updateUser(updateUserRequest: UpdateUserRequest) : Observable<Object>{
    return this.httpClient.put("http://localhost:8080/api/user", updateUserRequest);
  }

  userDetails(id: number): void {
    this.router.navigate(['/user/details/' + id])
  }

  editUser(id: number): void {
    this.router.navigate(['/user/editor/' + id])
  }
}
