import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateUserRequest } from '../model/user';

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

  constructor(private httpClient: HttpClient) { }

  public refreshUserList(): void {
    this.httpClient.get('http://localhost:8080/users')
      .subscribe((data) => {
        console.log(data);

        let receivedUserList = data as User[];
        this.userList = receivedUserList;
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
